package com.hzn.sales.dao;

import com.hzn.sales.model.goods.Good;
import com.hzn.sales.model.goods.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IGoodDao {
    int insetOneGood(Good good);

    int deleteGoodById(int id);

    int updateGoodStatus(@Param("goodsId") int goodsId,@Param("status")  int status);

    int updateGood(Good good);

    Good selectGoodById(int id);

    Good selectGoodByGid(int goodsId);

    //查询买家未购买的商品
    List<Good> selectForUnBuyByUid(@Param("uid") int uid,@Param("list") List<Integer> list);

    List<Good> selectAllGood();

    List<Good> selectAllGoodBySellerId(int sellerId);

    List<Good> selectAllGoodBySellerIdAndStatus(@Param("sellerId")int sellerId,@Param("status")int status);

}
