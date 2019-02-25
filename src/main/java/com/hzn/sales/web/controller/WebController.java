package com.hzn.sales.web.controller;

import com.alibaba.fastjson.JSON;
import com.hzn.sales.model.goods.Good;
import com.hzn.sales.model.goods.Order;
import com.hzn.sales.model.goods.ShopCar;
import com.hzn.sales.service.IGoodService;
import com.hzn.sales.service.IOrderService;
import com.hzn.sales.service.IShopCarService;
import com.hzn.sales.utils.GeneralResponse;
import com.hzn.sales.utils.IdGeneral;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    @Autowired
    IGoodService goodService;
    @Autowired
    IOrderService orderService;
    @Autowired
    IShopCarService shopCarService;

    @RequestMapping(value={"","/","/index"})
    public ModelAndView getAll(HttpServletRequest request, @RequestParam(value = "type", defaultValue = "0") String type) {
        HttpSession session = request.getSession();
        ModelAndView model=loginStatus(request);
        String loginStatus = (String) session.getAttribute("loginStatus");
        GeneralResponse<List<Good>> list = new GeneralResponse<List<Good>>();
        List<Good> goodList = new ArrayList<Good>();
        if (loginStatus.equals("visitor")||!NumberUtils.isNumber(type)) {
            goodList = goodService.getAllGood();
        } else if (loginStatus.equals("sellerUser")) {
            model.addObject("userName", session.getAttribute("sellerUser"));
            goodList = goodService.getAllGoodBySellerId(
                    (Integer) session.getAttribute("uid"));
        } else if (loginStatus.equals("buyerUser")&&type.equals("1")) {
            model.addObject("userName", session.getAttribute("buyerUser"));
            model.addObject("indextype", 1);
            goodList = goodService.getForUnBuyByUid(
                    (Integer) session.getAttribute("uid"));
            for(int i=0;i<goodList.size();i++) {
                Good goodtemp=goodList.get(i);
                goodtemp.setHaveBuy(1);
                goodList.set(i,goodtemp);
            }
        } else {
            model.addObject("userName", session.getAttribute("buyerUser"));
            List<Good> noBuy = goodService.getForUnBuyByUid(
                    (Integer) session.getAttribute("uid"));
            goodList = goodService.getAllGood();
            for (int i = 0; i < noBuy.size(); i++) {
                for (int j = 0; j < goodList.size(); j++) {
                    if (noBuy.get(i).getId() == goodList.get(j).getId()) {
                        Good good = goodList.get(j);
                        good.setHaveBuy(1);
                        goodList.set(j, good);
                    }
                }
            }
        }
        list.setValue(goodList);
        model.addObject("loginStatus", loginStatus);
        model.addObject("result", list);
        model.setViewName("forward:/index.jsp");
        return model;
    }

    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("forward:/login.jsp");
        HttpSession session = request.getSession();
        session.invalidate();
        return model;
    }

    @RequestMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @RequestMapping("account")
    public ModelAndView account(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView model=loginStatus(request);
        String loginStatus = (String) session.getAttribute("loginStatus");
        if (StringUtils.isEmpty(session.getAttribute("uid").toString())) {
            model.setViewName("404");
            return model;
        }
        int uid = (Integer) session.getAttribute("uid");
        List<Order> orderList = orderService.getOrderByUid(uid);
        GeneralResponse<List<Order>> list = new GeneralResponse<List<Order>>();
        list.setValue(orderList);
        double sumPrice = 0;
        for (int i = 0; i < orderList.size(); i++) {
            sumPrice+=orderList.get(i).getPayPrice();
        }
        model.addObject("result", list);
        model.addObject("sumPrice", sumPrice);
        model.setViewName("account");
        return model;
    }

    @RequestMapping("settleAccount")
    public ModelAndView settleAccount(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ModelAndView model = loginStatus(request);
        if (session.getAttribute("loginStatus").equals("visitor"))
            return new ModelAndView("404");
        model.setViewName("settleAccount");
        //设置cookie以供前端显示
        int uid = (Integer) session.getAttribute("uid");
        List<ShopCar> shopCarList = shopCarService.getShopCarByUserId(uid);
        model.addObject("shopCarList",shopCarList);
//        String jcookie = JSON.toJSONString(shopCarList);
//        Cookie cookie=new Cookie("test","eeeeeee");
//        response.addCookie(cookie);
        return model;
    }

    @RequestMapping("show")
    public ModelAndView Show(HttpServletRequest request, @RequestParam(value = "id") String id) {
        HttpSession session = request.getSession();
        ModelAndView model = loginStatus(request);
        Good good = new Good();
        GeneralResponse<Good> result = new GeneralResponse<Good>();
        if (NumberUtils.isNumber(id)) {
            good = goodService.getGoodByGid(Integer.parseInt(id));
        }
        String loginStatus = (String) session.getAttribute("loginStatus");
        if (session.getAttribute("loginStatus").equals("buyerUser") && good != null) {
            List<Order> orderList = orderService.getOrderByUid((Integer) session.getAttribute("uid"));
            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).getGood().getGoodsId() == Integer.parseInt(id)) {
                    good.setHaveBuy(1);
                    model.addObject("oldPrice", orderList.get(i).getGoodsPrice());
                    model.addObject("oldNum", orderList.get(i).getGoodsNum());
                    break;
                }
            }

        }
        result.setValue(good);
        model.addObject("loginStatus", loginStatus);
        model.setViewName("show");
        model.addObject("result", result);
        return model;
    }

    @RequestMapping("public")
    public ModelAndView goodpublic(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView model = loginStatus(request);
        model.setViewName("public");
        return model;
    }

    @RequestMapping("publicSubmit")
    public ModelAndView publicSubmit(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView model = loginStatus(request);
        String title=request.getParameter("title");
        String goodsAbstract=request.getParameter("summary");
        String goodsImg=request.getParameter("image");
        String goodsText=request.getParameter("detail");
        Double price=Double.parseDouble(request.getParameter("price"));
        int goodsId= IdGeneral.getGoodId();
        Good good=new Good(goodsId,title,price,goodsImg,goodsAbstract,goodsText);
        good.setSellerUid((Integer)session.getAttribute("uid"));
        int k=goodService.addOneGood(good);
        model.addObject("goodsId",goodsId);
        model.setViewName("publicSuccess");
        //修改商品表
        return model;
    }

    @RequestMapping("edit")
    public ModelAndView edit(HttpServletRequest request,@RequestParam(value = "id") String id) {
        HttpSession session=request.getSession();
        ModelAndView model=loginStatus(request);
        int uid=(Integer)session.getAttribute("uid");
        Good good = new Good();
        GeneralResponse<Good> result = new GeneralResponse<Good>();
        if (NumberUtils.isNumber(id)) {
            good = goodService.getGoodByGid(Integer.parseInt(id));
        }
        if(uid!=good.getSellerUid()) {
            model.setViewName("foward:/show?id="+good.getGoodsId());
        } else {
            model.addObject("good",good);
            model.setViewName("edit");
        }
        return model;
    }

    @RequestMapping("goodUpdate")
    public ModelAndView goodUpdate(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView model = loginStatus(request);
        String title=request.getParameter("title");
        String goodsAbstract=request.getParameter("summary");
        String goodsImg=request.getParameter("image");
        String goodsText=request.getParameter("detail");
        Double price=Double.parseDouble(request.getParameter("price"));
        int goodsId= Integer.parseInt(request.getParameter("gid"));
        int id= Integer.parseInt(request.getParameter("id"));
        Good good=new Good(goodsId,title,price,goodsImg,goodsAbstract,goodsText);
        good.setSellerUid((Integer)session.getAttribute("uid"));
        good.setId(id);
        good.setGoodsId(goodsId);
        int k=goodService.updateGood(good);
        model.addObject("goodsId",goodsId);
        model.setViewName("editSubmit");
        //修改商品表
        return model;
    }

    public ModelAndView loginStatus(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView model = new ModelAndView();
        String loginStatus = (String) session.getAttribute("loginStatus");
        if (StringUtils.isEmpty(loginStatus)) {
            //游客
            session.setAttribute("loginStatus", "visitor");
            loginStatus = (String) session.getAttribute("loginStatus");
        }
        if (session.getAttribute("loginStatus").equals("sellerUser")) {
            model.addObject("userName", session.getAttribute("sellerUser"));
            model.addObject("uid", ((Integer) session.getAttribute("uid")));
        } else if (session.getAttribute("loginStatus").equals("buyerUser")) {
            model.addObject("userName", session.getAttribute("buyerUser"));
            model.addObject("uid", ((Integer) session.getAttribute("uid")));
        }
        model.addObject("loginStatus", loginStatus);
        return model;
    }
}
