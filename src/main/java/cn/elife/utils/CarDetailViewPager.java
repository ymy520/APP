package cn.elife.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Author：张凯  on 2016/5/25 21:30
 * Blog: bukevin@github.io
 *
 * 此类用于洗车商家详情页的轮播图播放功能
 */
public class CarDetailViewPager extends ViewPager{
    public CarDetailViewPager(Context context) {
        super(context);
    }

    public CarDetailViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //播放时间
    private int showTime=3*1000;
    //滚动方向
    private Direction direction=Direction.LEFT;

    /**
     * 设置播放时间，默认3秒
     * @param showTimeMillis
     */
    public void setShowTime(int showTimeMillis){
        this.showTime=showTime;
    }

    /**
     * 设置滚动方向，默认向左
     * @param direction
     */
    public void setDirection(Direction direction){
        this.direction=direction;
    }

    public void start(){
        //开始播放
    }

    public void stop(){
        //停止播放
    }

    public void previous(){
        //播放上一个
    }

    public void next(){
        //播放下一个
    }

    /**
     * 用于指定轮播图播放方向
     */
    public enum Direction{
        LEFT,RIGHT
    }
}


