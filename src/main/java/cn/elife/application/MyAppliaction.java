package cn.elife.application;

import android.app.Application;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * Created by wgyscsf on 2016/5/18.
 */
public class MyAppliaction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //下面是科大讯飞相关配置
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=572d6450");
    }
}
