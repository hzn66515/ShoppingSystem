package com.hzn.sales.dao;

import com.hzn.sales.model.goods.Order;
import org.apache.ibatis.annotations.Param;

import java.util.*;

public interface IOrderDao {
    int insertOneOrder(Map<String,Object> param);

    int deleteOrderById(int id);

    int deleteOrderByOrderNumber(long orderNumber);

    Order selectOrderById(int id);

    Order selectOrderByOrderNumber(long orderNumber);

    Order selectOrderByUidAndGid(@Param("uid") int uid,@Param("goodsId") int goodsId);

    List<Order> selectOrderByUid(int uid);

    List<Order> selectAllOrder();
}
