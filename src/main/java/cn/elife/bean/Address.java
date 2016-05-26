package cn.elife.bean;

import java.io.Serializable;

/**
 * Created by wgyscsf on 2016/5/18.
 */
public class Address implements Serializable{
    private String name;
    private String phone;
    private String address;
    private boolean defaultAddresss;

    public Address() {
    }

    public Address(String name, String phone, String address, boolean defaultAddresss) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.defaultAddresss = defaultAddresss;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDefaultAddresss() {
        return defaultAddresss;
    }

    public void setDefaultAddresss(boolean defaultAddresss) {
        this.defaultAddresss = defaultAddresss;
    }

    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", defaultAddresss=" + defaultAddresss +
                '}';
    }
}
