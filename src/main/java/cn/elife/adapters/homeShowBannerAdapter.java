package cn.elife.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Bill on 2016/5/11.
 */
public class homeShowBannerAdapter extends PagerAdapter {

    List<View> mViewList;
    Context mContext;

    public homeShowBannerAdapter(List<View> viewList,Context mContext) {
        mViewList = viewList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;//设置到最大值，是为了保证用户可以一直循环滑动
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //此处的ViewGroup就是装载图片布局的容器
        position %= mViewList.size();
        if(position < 0){
            //这种情况什么时候能够出现？？？？？
            position += mViewList.size();
        }
        View view  = mViewList.get(position);
        ViewParent parent = view.getParent();

        if (parent != null){
            ViewGroup vg = (ViewGroup) parent;
            vg.removeView(view);
        }
        container.addView(view);

        final int finalPosition = position;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                show("我现在点击的是" + finalPosition + "位置的图片");

            }
        });

        return view;
    }

    private void show(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(mViewList.get(position%mViewList.size()));
    }

}
