package com.hzn.sales.service;

import com.hzn.sales.model.goods.Order;

import java.util.*;

public interface IOrderService {
    int addOneOrder(int uid,int goodsId,int goodsNum,double goodsPrice);

    Order getOrderById(int id);

    Order getOrderByOrderNumber(long orderNumber);

    List<Order> getOrderByUid(int uid);

    List<Order> getAllOrder();
}
