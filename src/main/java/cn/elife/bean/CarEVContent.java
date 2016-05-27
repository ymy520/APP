package cn.elife.bean;

import java.io.Serializable;

/**
 * Author：张凯  on 2016/5/26 12:45
 * Blog: bukevin@github.io
 */
public class CarEVContent implements Serializable {
    private String carEVUsername;
    private String carEVTime;
    private String carEVContent;
    private int carServerCode;

    public CarEVContent(String carEVUsername, String carEVTime, String carEVContent, int carServerCode) {
        this.carEVUsername = carEVUsername;
        this.carEVTime = carEVTime;
        this.carEVContent = carEVContent;
        this.carServerCode = carServerCode;
    }

    public String getCarEVUsername() {
        return carEVUsername;
    }

    public void setCarEVUsername(String carEVUsername) {
        this.carEVUsername = carEVUsername;
    }

    public String getCarEVTime() {
        return carEVTime;
    }

    public void setCarEVTime(String carEVTime) {
        this.carEVTime = carEVTime;
    }

    public String getCarEVContent() {
        return carEVContent;
    }

    public void setCarEVContent(String carEVContent) {
        this.carEVContent = carEVContent;
    }

    public int getCarServerCode() {
        return carServerCode;
    }

    public void setCarServerCode(int carServerCode) {
        this.carServerCode = carServerCode;
    }
}
