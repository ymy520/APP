package cn.elife.bean;

import java.io.Serializable;

/**
 * Created by 叶梦雅 on 2016/5/18.
 */
public class SnacksListData implements Serializable {
    private String name;//零食详情
    private double price;//零食价格
    private int imgaddress;//零食图片地址
    private int sale;//销售量

    public SnacksListData(String name, double price, int imgaddress, int sale) {
        this.name = name;
        this.price = price;
        this.imgaddress = imgaddress;
        this.sale = sale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImgaddress() {
        return imgaddress;
    }

    public void setImgaddress(int imgaddress) {
        this.imgaddress = imgaddress;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "SnacksListData{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", imgaddress=" + imgaddress +
                ", sale=" + sale +
                '}'+"\n";
    }
}
