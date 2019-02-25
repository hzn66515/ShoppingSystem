package com.hzn.sales.model.goods;

import java.util.Date;

public class ShopCar {

    private int id;

    private int uid;

    private Good good;

    private int num;

    private int status;

    private Date createTime;

    public ShopCar() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ShopCar{" +
                "id=" + id +
                ", uid=" + uid +
                ", good=" + good +
                ", num=" + num +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
