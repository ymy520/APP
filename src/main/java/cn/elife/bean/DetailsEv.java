package cn.elife.bean;

import java.io.Serializable;

/**
 * Author：张凯  on 2016/5/24 09:00
 * Blog: bukevin@github.io
 */
public class DetailsEv implements Serializable {
    private String userName;
    private String evContent;
    private String evTime;

    public DetailsEv() {
    }

    public DetailsEv(String userName, String evContent, String evTime) {
        this.userName = userName;
        this.evContent = evContent;
        this.evTime = evTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEvContent() {
        return evContent;
    }

    public void setEvContent(String evContent) {
        this.evContent = evContent;
    }

    public String getEvTime() {
        return evTime;
    }

    public void setEvTime(String evTime) {
        this.evTime = evTime;
    }
}
