package com.hzn.sales.service;

import com.hzn.sales.model.goods.Good;

import java.util.List;

public interface IGoodService {
    int addOneGood(Good good);

    int updateGoodStatus(int goodsId,int status);

    int updateGood(Good good);

    int deleteGood(int id);

    Good getGoodById(int id);

    Good getGoodByGid(int goodsId);

    //查询买家未购买的商品
    List<Good> getForUnBuyByUid(int uid);

    List<Good> getAllGood();

    List<Good> getAllGoodBySellerId(int sellerId);

    List<Good> getAllGoodBySellerIdAndStatus(int sellerId,int status);
}
