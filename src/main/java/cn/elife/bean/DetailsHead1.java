package cn.elife.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Author：张凯  on 2016/5/24 08:35
 * Blog: bukevin@github.io
 */
public class DetailsHead1 implements Serializable{
    private int iamgeid;
    private Bitmap bitmap;

    public DetailsHead1(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public DetailsHead1(int iamgeid) {
        this.iamgeid = iamgeid;
    }

    public int getIamgeid() {
        return iamgeid;
    }

    public void setIamgeid(int iamgeid) {
        this.iamgeid = iamgeid;
    }
}
