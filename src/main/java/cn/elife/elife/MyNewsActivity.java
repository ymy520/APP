package cn.elife.elife;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.elife.adapters.FragmentAdapter;
import cn.elife.fragments.MyNewsAllFragment;
import cn.elife.fragments.MyNewsMerFragment;
import cn.elife.fragments.MyNewsPriFragment;
import cn.elife.fragments.MyNewsSysFragment;

public class MyNewsActivity extends FragmentActivity implements View.OnClickListener {
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentAdapter mFragmentAdapter;

    private LinearLayout mAllLayout, mPriLayout, mMerLayout, mSysLayout;

    private ViewPager mPageVp;
    /**
     * Tab显示内容TextView
     */
    private TextView mTabAllTv, mTabPriTv, mTabMerTv, mTabSysTv;
    /**
     * Tab的那个引导线
     */
    private ImageView mTabLineIv;
    /**
     * Fragment
     */
    private MyNewsAllFragment myNewsAllFragment;
    private MyNewsPriFragment myNewsPriFragment;
    private MyNewsMerFragment myNewsMerFragment;
    private MyNewsSysFragment myNewsSysFragment;
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
        setContentView(R.layout.activity_my_news);
        initViews();
        initData();
        initTabLineWidth();
    }

    /**
     * 设置滑动条的宽度为屏幕的1/4(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                .getLayoutParams();
        lp.width = screenWidth / 4;
        mTabLineIv.setLayoutParams(lp);
    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        mTabAllTv.setTextColor(Color.BLACK);
        mTabPriTv.setTextColor(Color.BLACK);
        mTabMerTv.setTextColor(Color.BLACK);
        mTabSysTv.setTextColor(Color.BLACK);
    }

    private void initData() {
        myNewsAllFragment = new MyNewsAllFragment();
        myNewsPriFragment = new MyNewsPriFragment();
        myNewsMerFragment = new MyNewsMerFragment();
        myNewsSysFragment = new MyNewsSysFragment();

        fragmentList.add(myNewsAllFragment);
        fragmentList.add(myNewsPriFragment);
        fragmentList.add(myNewsMerFragment);
        fragmentList.add(myNewsSysFragment);

        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), fragmentList);
        mPageVp.setAdapter(mFragmentAdapter);

        mTabAllTv.setTextColor(Color.BLUE);
        mPageVp.setCurrentItem(0);

        mAllLayout.setOnClickListener(this);
        mPriLayout.setOnClickListener(this);
        mMerLayout.setOnClickListener(this);
        mSysLayout.setOnClickListener(this);

        mPageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float offset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                        .getLayoutParams();
                /**
                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                 * 设置mTabLineIv的左边距 滑动场景：
                 * 记3个页面,
                 * 从左到右分别为0,1,2
                 * 0->1; 1->2; 2->1; 1->0
                 */

                if (currentIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 4) + currentIndex
                            * (screenWidth / 4));

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 4) + currentIndex
                            * (screenWidth / 4));

                } else if (currentIndex == 1 && position == 1)// 0->1
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 4) + currentIndex
                            * (screenWidth / 4));

                } else if (currentIndex == 2 && position == 1) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 4) + currentIndex
                            * (screenWidth / 4));

                } else if (currentIndex == 2 && position == 2)// 0->1
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 4) + currentIndex
                            * (screenWidth / 4));

                } else if (currentIndex == 3 && position == 2) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 4) + currentIndex
                            * (screenWidth / 4));

                }
                mTabLineIv.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        mTabAllTv.setTextColor(Color.BLUE);
                        break;
                    case 1:
                        mTabPriTv.setTextColor(Color.BLUE);
                        break;
                    case 2:
                        mTabMerTv.setTextColor(Color.BLUE);
                        break;
                    case 3:
                        mTabSysTv.setTextColor(Color.BLUE);
                        break;
                }
                currentIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViews() {
        mTabAllTv = (TextView) findViewById(R.id.mynews_all_text);
        mTabPriTv = (TextView) findViewById(R.id.mynews_pri_text);
        mTabMerTv = (TextView) findViewById(R.id.mynews_merchant_text);
        mTabSysTv = (TextView) findViewById(R.id.mynews_sys_text);

        mTabLineIv= (ImageView) findViewById(R.id.mynews_line_iv);
        mPageVp = (ViewPager) findViewById(R.id.mynews_page_vp);

        mAllLayout = (LinearLayout) findViewById(R.id.mynews_tab_all);
        mPriLayout = (LinearLayout) findViewById(R.id.mynews_tab_pri);
        mMerLayout = (LinearLayout) findViewById(R.id.mynews_tab_merchant);
        mSysLayout = (LinearLayout) findViewById(R.id.mynews_tab_sys);
    }

    @Override
    public void onClick(View v) {
        resetTextView();
        switch (v.getId()) {
            case R.id.mynews_tab_all:
                mPageVp.setCurrentItem(0);
                mTabAllTv.setTextColor(Color.BLUE);
                break;
            case R.id.mynews_tab_pri:
                mPageVp.setCurrentItem(1);
                mTabPriTv.setTextColor(Color.BLUE);
                break;
            case R.id.mynews_tab_merchant:
                mPageVp.setCurrentItem(2);
                mTabMerTv.setTextColor(Color.BLUE);
                break;
            case R.id.mynews_tab_sys:
                mPageVp.setCurrentItem(3);
                mTabSysTv.setTextColor(Color.BLUE);
                break;
        }
    }
}
