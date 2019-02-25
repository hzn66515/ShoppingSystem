package com.hzn.sales.service;

import com.hzn.sales.model.goods.ShopCar;

import java.util.List;
import java.util.Map;

public interface IShopCarService {
    int addToShopCar(int uid,int goodsId,int num);

    int updateNum(int id,int num);

    int updateStatusShopCar(int id,int status);

    int updateStatusAndNumShopCar(int id,int num,int status);

    ShopCar getShopCarById(int id);

    List<ShopCar> getShopCarByUserId(int uid);

    List<ShopCar> getAllShopCar();
}
