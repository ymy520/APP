package cn.elife.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import cn.elife.bean.SnacksBanner;
import cn.elife.elife.SnacksActivity;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by 叶梦雅 on 2016/5/18.
 */
public class SnacksBannerAdapter extends PagerAdapter {

    private List<SnacksBanner> adList;// 轮播banner的数据
    private List<ImageView> imageViews;// 滑动的图片集合
    Context mContext;
    LayoutInflater mInflater;

    public SnacksBannerAdapter(List<SnacksBanner> adList, List<ImageView> imageViews, Context context) {
        this.adList = adList;
        this.imageViews = imageViews;
        mContext = context;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return adList.size();
    }
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView iv = imageViews.get(position);
        ((ViewPager) container).addView(iv);
        final SnacksBanner snacksBanner = adList.get(position);
        // 在这个方法里面设置图片的点击事件
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 处理跳转逻辑
                Toast.makeText(mContext,"点击了"+position,Toast.LENGTH_SHORT).show();
            }
        });
        return iv;
    }
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;
    }
    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {

    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View arg0) {

    }

    @Override
    public void finishUpdate(View arg0) {

    }
}
