package com.hzn.sales.dao;

import com.hzn.sales.model.goods.ShopCar;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IShopCarDao {
    int insertOneToShopCar(Map<String,Object> param);

    int updateNum(@Param("id") int id, @Param("num") int num);

    int updateStatusShopCar(@Param("id") int id, @Param("status") int status);

    int updateStatusAndNumShopCar(@Param("id") int id,@Param("num") int num,@Param("status") int status);

    ShopCar selectShopCarById(int id);

    ShopCar selectShopCarByGid(int gid);

    ShopCar selectShopCarByUidAndGid(@Param("uid")int uid,@Param("gid") int gid);

    List<ShopCar> selectShopCarByUserId(int uid);

    List<ShopCar> selectAllShopCar();
}
