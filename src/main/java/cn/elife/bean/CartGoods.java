package cn.elife.bean;

import java.io.Serializable;

/**
 * Author：张凯  on 2016/5/18 09:50
 * Blog: bukevin@github.io
 */
public class CartGoods implements Serializable{
    private int Url;
    private String desc;
    private String price;
    private String num;
    private boolean checked = false;//用来标志当前行数据中的checkbox是否被选中
    public boolean isChecked() {
        return checked;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public CartGoods(int url, String desc, String price, String num) {
        Url = url;
        this.num = num;
        this.desc = desc;
        this.price = price;
    }

    public int getUrl() {
        return Url;
    }

    public void setUrl(int url) {
        Url = url;
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
