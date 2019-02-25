package com.hzn.sales.service.impl;

import com.hzn.sales.dao.IGoodDao;
import com.hzn.sales.dao.IOrderDao;
import com.hzn.sales.model.goods.Good;
import com.hzn.sales.model.goods.Order;
import com.hzn.sales.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class GoodServiceImpl implements IGoodService {
    @Autowired
    IGoodDao goodDao;
    @Autowired
    IOrderDao orderDao;
    public int addOneGood(Good good) {
        return goodDao.insetOneGood(good);
    }

    public int updateGoodStatus(int goodsId, int status) {
        return goodDao.updateGoodStatus(goodsId,status);
    }

    public int updateGood(Good good) {
        return goodDao.updateGood(good);
    }

    public int deleteGood(int id) {
        return goodDao.deleteGoodById(id);
    }

    public Good getGoodById(int id) {
        return goodDao.selectGoodById(id);
    }

    public Good getGoodByGid(int goodsId) {
        return goodDao.selectGoodByGid(goodsId);
    }

    public List<Good> getForUnBuyByUid(int uid) {
        List<Order> list=orderDao.selectOrderByUid(uid);
        List<Integer> idList=new ArrayList<Integer>();
        if(list.size()==0) {
            return goodDao.selectAllGood();
        } else {
            for (int i = 0; i < list.size(); i++) {
                idList.add(list.get(i).getGood().getGoodsId());
            }
            return goodDao.selectForUnBuyByUid(uid, idList);
        }
    }

    public List<Good> getAllGood() {
        return goodDao.selectAllGood();
    }

    public List<Good> getAllGoodBySellerId(int sellerId) {
        return goodDao.selectAllGoodBySellerId(sellerId);
    }

    public List<Good> getAllGoodBySellerIdAndStatus(int sellerId, int status) {
        return goodDao.selectAllGoodBySellerIdAndStatus(sellerId,status);
    }
}
