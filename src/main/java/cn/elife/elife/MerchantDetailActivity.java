package cn.elife.elife;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.elife.adapters.FragmentAdapter;
import cn.elife.fragments.AllFragment;
import cn.elife.fragments.DetailsFragment;

public class MerchantDetailActivity extends FragmentActivity implements View.OnClickListener {

    private List<Fragment> fragmentList=new ArrayList<>();
    private FragmentAdapter mFragmentAdapter;
    private LinearLayout mDetails,mAll;

    private ViewPager mPageVp;
    /**
     * Tab显示内容TextView
     */
    private TextView mTabdetailsTv, mTabAllTv;
    /**
     * Tab的那个引导线
     */
    private ImageView mTabLineIv;
    /**
     * Fragment
     */
    private DetailsFragment mDetailsFragment;
    private AllFragment mAllFragment;
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
        setContentView(R.layout.activity_mechant_deta);
        initViews();
        initData();
        initTabLineWidth();
    }

    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                .getLayoutParams();
        lp.width = screenWidth / 2;
        mTabLineIv.setLayoutParams(lp);
    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        mTabdetailsTv.setTextColor(Color.WHITE);
        mTabAllTv.setTextColor(Color.WHITE);
    }

    private void initData() {
        mDetailsFragment=new DetailsFragment();
        mAllFragment=new AllFragment();
        fragmentList.add(mDetailsFragment);
        fragmentList.add(mAllFragment);
        mFragmentAdapter=new FragmentAdapter(this.getSupportFragmentManager(),fragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mTabdetailsTv.setTextColor(Color.BLUE);
        mPageVp.setCurrentItem(0);

        mDetails.setOnClickListener(this);
        mAll.setOnClickListener(this);

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
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 2) + currentIndex
                            * (screenWidth / 2));

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 2) + currentIndex
                            * (screenWidth / 2));

                }
                mTabLineIv.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        mTabdetailsTv.setTextColor(Color.BLUE);
                        break;
                    case 1:
                        mTabAllTv.setTextColor(Color.BLUE);
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
        mTabdetailsTv= (TextView) findViewById(R.id.id_details_tv);
        mTabAllTv= (TextView) findViewById(R.id.id_all_tv);

        mTabLineIv= (ImageView) findViewById(R.id.id_tab_line_iv);
        mPageVp= (ViewPager) findViewById(R.id.id_page_vp);

        mDetails= (LinearLayout) findViewById(R.id.id_tab_details_ll);
        mAll= (LinearLayout) findViewById(R.id.id_tab_all_ll);
    }

    @Override
    public void onClick(View v) {
        resetTextView();
        switch(v.getId()){
            case R.id.id_tab_details_ll:
                mPageVp.setCurrentItem(0);
                mTabdetailsTv.setTextColor(Color.BLUE);
                break;
            case R.id.id_tab_all_ll:
                mPageVp.setCurrentItem(1);
                mTabAllTv.setTextColor(Color.BLUE);
                break;
        }
    }
}
