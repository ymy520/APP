package cn.elife.bean;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Author：张凯  on 2016/5/25 11:13
 * Blog: bukevin@github.io
 */
public class CarMerchant implements Serializable {
    private int imageId;
    private String merchantName;
    private String merchantAddr;
    private int evNum;
    private int orderNum;
    private int distanceNum;

    public CarMerchant(int imageId, String merchantName, String merchantAddr, int evNum, int orderNum, int distanceNum) {
        this.imageId = imageId;
        this.merchantName = merchantName;
        this.merchantAddr = merchantAddr;
        this.evNum = evNum;
        this.orderNum = orderNum;
        this.distanceNum = distanceNum;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantAddr() {
        return merchantAddr;
    }

    public void setMerchantAddr(String merchantAddr) {
        this.merchantAddr = merchantAddr;
    }

    public int getEvNum() {
        return evNum;
    }

    public void setEvNum(int evNum) {
        this.evNum = evNum;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getDistanceNum() {
        return distanceNum;
    }

    public void setDistanceNum(int distanceNum) {
        this.distanceNum = distanceNum;
    }

    @Override
    public String toString() {
        return "CarMerchant{" +
                "evNum=" + evNum +
                ", orderNum=" + orderNum +
                ", distanceNum=" + distanceNum +
                '}';
    }
}
