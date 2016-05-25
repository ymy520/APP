package cn.elife.bean;

import java.io.Serializable;

/**
 * Author：张凯  on 2016/5/24 08:36
 * Blog: bukevin@github.io
 */
public class DetailsHead2 implements Serializable{
    private String merchantName;
    private String merchantaddr;
    private String merchantCont;

    public DetailsHead2(String merchantName, String merchantaddr,String merchantCont) {
        this.merchantCont = merchantCont;
        this.merchantName = merchantName;
        this.merchantaddr = merchantaddr;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantaddr() {
        return merchantaddr;
    }

    public void setMerchantaddr(String merchantaddr) {
        this.merchantaddr = merchantaddr;
    }

    public String getMerchantCont() {
        return merchantCont;
    }

    public void setMerchantCont(String merchantCont) {
        this.merchantCont = merchantCont;
    }
}
