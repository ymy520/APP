package cn.elife.bean;

import java.io.Serializable;

/**
 * Author：张凯  on 2016/5/19 16:23
 * Blog: bukevin@github.io
 */
public class CartImage implements Serializable {
    private int imageUrl1;
    private int imageUrl2;
    private int imageUrl3;
    private int imageUrl4;

    public int getImageUrl1() {
        return imageUrl1;
    }

    public void setImageUrl1(int imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public int getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(int imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public int getImageUrl3() {
        return imageUrl3;
    }

    public void setImageUrl3(int imageUrl3) {
        this.imageUrl3 = imageUrl3;
    }

    public int getImageUrl4() {
        return imageUrl4;
    }

    public void setImageUrl4(int imageUrl4) {
        this.imageUrl4 = imageUrl4;
    }
}
