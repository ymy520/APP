package cn.elife.bean;

import java.io.Serializable;

/**
 * Created by Bill on 2016/5/2.
 */
public class Goods implements Serializable {
    /**
     * 此类用于存储在首页上显示的商品的所有信息，目前只包含在界面上显示的信息
     * 可能存在的问题：
     *      可能会存在一些不用再界面上显示的信息，但是有用的信息。如果存在这种情况，后期再做扩展
     */
    private String name;//商品名称
    private double price;//商品价格
    private int imgaddress;//商品图片地址-----------现在本地测试使用整型
    private int sale;//销售量

    public Goods(String name, double price, int imgaddress, int sale) {
        this.name = name;
        this.price = price;
        this.imgaddress = imgaddress;
        this.sale = sale;
    }

    public Goods() {    }

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
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", imgaddress='" + imgaddress + '\'' +
                ", sale=" + sale +
                '}';
    }
}
