package com.hzn.sales.web.controller;

import com.hzn.sales.model.EasyShopCar;
import com.hzn.sales.model.goods.Good;
import com.hzn.sales.model.goods.ShopCar;
import com.hzn.sales.model.user.User;
import com.hzn.sales.service.IGoodService;
import com.hzn.sales.service.IOrderService;
import com.hzn.sales.service.IShopCarService;
import com.hzn.sales.service.IUserService;
import com.hzn.sales.utils.GeneralResponse;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("api")
public class ApiController {

    private final Logger logger= LoggerFactory.getLogger(ApiController.class);

    @Autowired
    IUserService userService;
    @Autowired
    IGoodService goodService;
    @Autowired
    IOrderService orderService;
    @Autowired
    IShopCarService shopCarService;

    @RequestMapping(value="login",method = RequestMethod.POST)
    @ResponseBody
    public GeneralResponse<String> Login(HttpServletRequest request){
        String username=request.getParameter("userName");
        String password=request.getParameter("password");
        User user=userService.userExist(username,password);
        GeneralResponse<String> generalResponse=new GeneralResponse<String>();
        if(user==null){
            generalResponse.setCode(500);
            generalResponse.setMessage("用户密码错误");
        }else {
            HttpSession session=request.getSession();
            session.setAttribute("uid",user.getUserId());
            //卖家
            if(user.getIsSeller()==1) {
                session.setAttribute("loginStatus","sellerUser");
                session.setAttribute("sellerUser",user.getUsername());
                session.removeAttribute("buyerUser");
            } else {
                session.setAttribute("loginStatus","buyerUser");
                session.setAttribute("buyerUser",user.getUsername());
                session.removeAttribute("sellerUser");
            }
            generalResponse.setCode(200);
            generalResponse.setMessage("true");
        }
        return generalResponse;
    }
    @RequestMapping("buy")
    @ResponseBody
    public GeneralResponse<Integer> Buy(HttpServletRequest request, @RequestBody EasyShopCar[] easyShopCars) {
        //修改订单表 购物车表  商品表
        GeneralResponse<Integer> generalResponse=new GeneralResponse<Integer>();
        if(easyShopCars.length==0||((Integer)request.getSession().getAttribute("uid"))==null) {
            generalResponse.setCode(500);
            return generalResponse;
        } else {
            for (EasyShopCar easy:easyShopCars) {
                shopCarService.updateStatusAndNumShopCar(Integer.parseInt(easy.getId()),Integer.parseInt(easy.getNum()),1);
                goodService.updateGoodStatus(Integer.parseInt(easy.getGoodsId()),1);
                orderService.addOneOrder((Integer)request.getSession().getAttribute("uid")
                ,Integer.parseInt(easy.getGoodsId()),Integer.parseInt(easy.getNum()),Double.parseDouble(easy.getPrice()));
            }
            generalResponse.setCode(200);
        }
        return generalResponse;
    }
    @RequestMapping("addShopCar")
    @ResponseBody
    public GeneralResponse<Integer> addShopCar(HttpServletRequest request,@RequestBody Map<String,Object> map) {
        HttpSession session=request.getSession();
        Integer goodsId=Integer.parseInt((String)map.get("goodsId"));
        Integer num=Integer.parseInt(((String)map.get("num")).trim());
        Integer uid=(Integer) session.getAttribute("uid");
        System.out.println("goodsID:"+goodsId+"   num:"+num);
        GeneralResponse<Integer> generalResponse=new GeneralResponse<Integer>();
        if(goodsId==null||uid==null) {
            generalResponse.setCode(401);
            generalResponse.setMessage("商品ID或者用户未登录");
            return generalResponse;
        }
        shopCarService.addToShopCar(uid,goodsId,num);
        generalResponse.setCode(200);
        return generalResponse;
    }
    @RequestMapping("upload")
    @ResponseBody
    public GeneralResponse<Integer> upload(@RequestParam("file") CommonsMultipartFile file) {
//        String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());
//        String path="/"+new Date().getTime()+file.getOriginalFilename();
//
//        File newFile=new File(path);
//        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
//        file.transferTo(newFile);
//        long  endTime=System.currentTimeMillis();
//        System.out.println("采用file.Transto的运行时间："+String.valueOf(endTime-startTime)+"ms");
        GeneralResponse<Integer> result=new GeneralResponse<Integer>();
        return result;
    }
    @RequestMapping("delete")
    @ResponseBody
    public GeneralResponse<Integer> delete(HttpServletRequest request,@RequestParam("id") String id) {
        GeneralResponse<Integer> generalResponse=new GeneralResponse<Integer>();
        if(request.getSession().getAttribute("uid")==null||!NumberUtils.isNumber(id)) {
            generalResponse.setCode(500);
            return generalResponse;
        }
        Good good=goodService.getGoodByGid(Integer.parseInt(id));
        int uid=(Integer) request.getSession().getAttribute("uid");
        if(good.getSellerUid()!=uid) {
            generalResponse.setCode(500);
            return generalResponse;
        }
        goodService.deleteGood(good.getId());
        generalResponse.setCode(200);
        return generalResponse;
    }
}
