package com.hzn.sales.model.goods;

import java.util.Date;

public class Good {
    private int id;

    private int goodsId;

    private String title;

    private double price;

    private String goodsImg;

    private String goodsAbstract;

    private String goodsText;

    private int isSold;

    private int goodsNum;

    private int soldNum;

    private int sellerUid;

    private int haveBuy;

    private Date createTime;

    private Date updateTime;

    public Good() {

    }

    public Good(int goodsId, String title, double price, String goodsImg, String goodsAbstract, String goodsText) {
        this.goodsId = goodsId;
        this.title = title;
        this.price = price;
        this.goodsImg = goodsImg;
        this.goodsAbstract = goodsAbstract;
        this.goodsText = goodsText;
        this.haveBuy=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsAbstract() {
        return goodsAbstract;
    }

    public void setGoodsAbstract(String goodsAbstract) {
        this.goodsAbstract = goodsAbstract;
    }

    public String getGoodsText() {
        return goodsText;
    }

    public void setGoodsText(String goodsText) {
        this.goodsText = goodsText;
    }

    public int getIsSold() {
        return isSold;
    }

    public void setIsSold(int isSold) {
        this.isSold = isSold;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public int getSoldNum() {
        return soldNum;
    }

    public void setSoldNum(int soldNum) {
        this.soldNum = soldNum;
    }

    public int getSellerUid() {
        return sellerUid;
    }

    public void setSellerUid(int sellerUid) {
        this.sellerUid = sellerUid;
    }

    public int getHaveBuy() {
        return haveBuy;
    }

    public void setHaveBuy(int haveBuy) {
        this.haveBuy = haveBuy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", goodsImg='" + goodsImg + '\'' +
                ", goodsAbstract='" + goodsAbstract + '\'' +
                ", goodsText='" + goodsText + '\'' +
                ", isSold=" + isSold +
                ", goodsNum=" + goodsNum +
                ", soldNum=" + soldNum +
                ", sellerUid=" + sellerUid +
                ", haveBuy=" + haveBuy +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
