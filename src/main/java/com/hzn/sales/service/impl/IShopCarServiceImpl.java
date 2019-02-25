package com.hzn.sales.service.impl;

import com.hzn.sales.dao.IShopCarDao;
import com.hzn.sales.model.goods.ShopCar;
import com.hzn.sales.service.IShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class IShopCarServiceImpl implements IShopCarService {
    @Autowired
    IShopCarDao shopCarDao;

    public int addToShopCar(int uid, int goodsId, int num) {
        ShopCar shopCar=shopCarDao.selectShopCarByUidAndGid(uid,goodsId);
        System.out.println(shopCar);
        if(shopCar!=null)
        {
            return this.updateNum(shopCar.getId(),num);
        }
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("uid",uid);
        map.put("goodsId",goodsId);
        map.put("num",num);
        return shopCarDao.insertOneToShopCar(map);
    }

    public int updateNum(int id, int num) {
        ShopCar shopCar=shopCarDao.selectShopCarById(id);
        System.out.println("update enter"+id+"::::"+num+" shopcar:"+shopCar);
        if(shopCar==null)
            return -1;
        if(num<0&&(shopCar.getNum()+num)<0) {
            return -1;
        }
        if((shopCar.getNum()+num)==0)
            return this.updateStatusShopCar(id,3);
        return shopCarDao.updateNum(id,num);
    }

    public int updateStatusShopCar(int id, int status) {
        return shopCarDao.updateStatusShopCar(id,status);
    }

    public int updateStatusAndNumShopCar(int id, int num, int status) {
        return shopCarDao.updateStatusAndNumShopCar(id,num,status);
    }

    public ShopCar getShopCarById(int id) {
        return shopCarDao.selectShopCarById(id);
    }

    public List<ShopCar> getShopCarByUserId(int uid) {
        return shopCarDao.selectShopCarByUserId(uid);
    }

    public List<ShopCar> getAllShopCar() {
        return shopCarDao.selectAllShopCar();
    }
}
