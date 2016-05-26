package cn.elife.bean;

import java.io.Serializable;

/**
 * Author：张凯  on 2016/5/26 12:38
 * Blog: bukevin@github.io
 *
 * 未完成
 */
public class CarMerchantHead implements Serializable {
    private int imageid1;
    private int imageid2;
    private int imageid3;
    private String address;
    private String phone;

    public CarMerchantHead(int imageid1, int imageid2, int imageid3, String address, String phone) {
        this.imageid1 = imageid1;
        this.imageid2 = imageid2;
        this.imageid3 = imageid3;
        this.address = address;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImageid1() {
        return imageid1;
    }

    public void setImageid1(int imageid1) {
        this.imageid1 = imageid1;
    }

    public int getImageid2() {
        return imageid2;
    }

    public void setImageid2(int imageid2) {
        this.imageid2 = imageid2;
    }

    public int getImageid3() {
        return imageid3;
    }

    public void setImageid3(int imageid3) {
        this.imageid3 = imageid3;
    }
}
