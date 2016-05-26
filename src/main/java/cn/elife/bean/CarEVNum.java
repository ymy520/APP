package cn.elife.bean;

import java.io.Serializable;

/**
 * Author：张凯  on 2016/5/26 12:43
 * Blog: bukevin@github.io
 */
public class CarEVNum implements Serializable {
    private int carEVNum;

    public CarEVNum(int carEVNum) {
        this.carEVNum = carEVNum;
    }

    public int getCarEVNum() {
        return carEVNum;
    }

    public void setCarEVNum(int carEVNum) {
        this.carEVNum = carEVNum;
    }
}
