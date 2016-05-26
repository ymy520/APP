package cn.elife.bean;

import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Author：张凯  on 2016/5/23 20:09
 * Blog: bukevin@github.io
 */
public class MerchantDetails implements Serializable {
    private String name;

    public MerchantDetails(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
