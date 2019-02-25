package com.hzn.sales.service.impl;

import com.hzn.sales.dao.IOrderDao;
import com.hzn.sales.model.goods.Order;
import com.hzn.sales.service.IOrderService;
import com.hzn.sales.utils.IdGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderServiceImpl implements IOrderService {
    @Autowired
    IOrderDao orderDao;

    public int addOneOrder(int uid, int goodsId, int goodsNum, double goodsPrice) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("orderNumber",IdGeneral.getOrderNo());
        map.put("uid",uid);
        map.put("goodsId",goodsId);
        map.put("goodsNum",goodsNum);
        map.put("goodsPrice",goodsPrice);
        map.put("payPrice",goodsNum*goodsPrice);
        return orderDao.insertOneOrder(map);
    }

    public Order getOrderById(int id) {
        return orderDao.selectOrderById(id);
    }

    public Order getOrderByOrderNumber(long orderNumber) {
        return orderDao.selectOrderByOrderNumber(orderNumber);
    }

    public List<Order> getOrderByUid(int uid) {
        return orderDao.selectOrderByUid(uid);
    }

    public List<Order> getAllOrder() {
        return orderDao.selectAllOrder();
    }
}
