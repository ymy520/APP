package cn.elife.elife;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import java.util.ArrayList;
import java.util.List;

import cn.elife.adapters.MyOrderFragmentAdapter;
import cn.elife.fragments.MyAllOrder;
import cn.elife.fragments.MyDaifahuoOrder;
import cn.elife.fragments.MyDaifukuanOrder;
import cn.elife.fragments.MyDaipingjiaOrder;
import cn.elife.fragments.MyDaishouhuoOrder;

public class MyOrderActivity extends FragmentActivity {

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private MyOrderFragmentAdapter mFragmentAdapter;
    private RadioGroup mRadioGroup;

    private ViewPager mPageVp;
    /**
     * Tab的那个引导线
     */
    private ImageView mTabLineIv;
    /**
     * Fragment
     */
    private MyAllOrder mMyAllOrder;
    private MyDaifukuanOrder mMyDaifukuanOrder;
    private MyDaifahuoOrder mMyDaifahuoOrder;
    private MyDaishouhuoOrder mMyDaishouhuoOrder;
    private MyDaipingjiaOrder mMyDaipingjiaOrder;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;
    /**
     * 屏幕的宽度
     */
    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        findById();
        initListeners();
        init();
        initTabLineWidth();
        intentDemo();


    }


    /**
     * 设置滑动条的宽度为屏幕的1/5(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                .getLayoutParams();
        lp.width = screenWidth / 5;
        mTabLineIv.setLayoutParams(lp);
    }


    private void findById() {

        mTabLineIv = (ImageView) this.findViewById(R.id.id_tab_line);

        mPageVp = (ViewPager) this.findViewById(R.id.order_vpa);
        mRadioGroup = (RadioGroup) findViewById(R.id.orders);

    }
    private void intentDemo() {
        Intent intent=getIntent();
        int pon=intent.getIntExtra("position",0);
        if (pon==0){
            mPageVp.setCurrentItem(0);

        }else if (pon==1){
            mPageVp.setCurrentItem(1);

        }else if (pon==2){
            mPageVp.setCurrentItem(2);

        }else if (pon==3){
            mPageVp.setCurrentItem(3);

        }else if(pon==4){
            mPageVp.setCurrentItem(4);
        }
    }

    private void initListeners() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                resetViewPager(checkedId);
            }
        });
    }




    private void init() {
        mMyAllOrder = new MyAllOrder();
        mMyDaifukuanOrder = new MyDaifukuanOrder();
        mMyDaifahuoOrder = new MyDaifahuoOrder();
        mMyDaishouhuoOrder = new MyDaishouhuoOrder();
        mMyDaipingjiaOrder = new MyDaipingjiaOrder();

        mFragmentList.add(mMyAllOrder);
        mFragmentList.add(mMyDaifukuanOrder);
        mFragmentList.add(mMyDaifahuoOrder);
        mFragmentList.add(mMyDaishouhuoOrder);
        mFragmentList.add(mMyDaipingjiaOrder);


        mFragmentAdapter = new MyOrderFragmentAdapter(
                this.getSupportFragmentManager(), mFragmentList);

        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);

        mPageVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }

            /**
             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             * offsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float offset,
                                       int offsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                        .getLayoutParams();

                /**
                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                 * 设置mTabLineIv的左边距 滑动场景：
                 * 记5个页面,
                 * 从左到右分别为0,1,2,3,4
                 * 0->1; 1->2; 2->3;3->4;4->3;3->2;2->1; 1->0
                 */

                if (currentIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 5) + currentIndex
                            * (screenWidth / 5));

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 5) + currentIndex
                            * (screenWidth / 5));

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 5) + currentIndex
                            * (screenWidth / 5));
                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 5) + currentIndex
                            * (screenWidth / 5));
                } else if (currentIndex == 2 && position == 2) // 2->3
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 5) + currentIndex
                            * (screenWidth / 5));
                } else if (currentIndex == 3 && position == 2) // 3->2
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 5) + currentIndex
                            * (screenWidth / 5));
                } else if (currentIndex == 3 && position == 3) // 3->4
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 5) + currentIndex
                            * (screenWidth / 5));
                } else if (currentIndex == 4 && position == 3) // 4->3
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 5) + currentIndex
                            * (screenWidth / 5));
                }else if (currentIndex == 4 && position == 4)
                {
                    lp.leftMargin = (int) (offset
                            * (screenWidth * 1.0 / 5) + currentIndex
                            * (screenWidth / 5));
                }
                mTabLineIv.setLayoutParams(lp);
            }
            @Override
            public void onPageSelected(int position) {
                //根据当前位置设置默认选中单选按钮
                resetRadioButton(position);
            }
        });
    }
    private void resetViewPager(int checkedId) {
        switch (checkedId) {
            case R.id.id_all:
                mPageVp.setCurrentItem(0);
                break;
            case R.id.id_daifukuan:
                mPageVp.setCurrentItem(1);
                break;
            case R.id.id_daifahuo:
                mPageVp.setCurrentItem(2);
                break;
            case R.id.id_daishouhuo:
                mPageVp.setCurrentItem(3);
                break;
            case R.id.id_daipingjia:
                mPageVp.setCurrentItem(4);
                break;
        }
    }
    private void resetRadioButton(int position) {
        //获取position位置处对应的单选按钮
        RadioButton radioButton = (RadioButton) mRadioGroup.getChildAt(position);
        //设置当前单选按钮默认选中
        radioButton.setChecked(true);
        currentIndex=position;
    }
}