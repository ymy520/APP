package cn.elife.bean;

import java.io.Serializable;

/**
 * Author：张凯  on 2016/5/24 17:31
 * Blog: bukevin@github.io
 *
 * 商家详情页 全部商品
 */
public class AllItems implements Serializable {
    private int imageid;
    private String desc;
    private String price;
    private String num;

    public AllItems() {
    }

    public AllItems(int imageid, String desc, String price, String num) {
        this.imageid = imageid;
        this.desc = desc;
        this.price = price;
        this.num = num;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
