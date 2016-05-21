package cn.elife.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.elife.adapters.MerchantFragmentAdapter;
import cn.elife.adapters.MerchantFragmentSortAdapter;
import cn.elife.bean.Merchant;
import cn.elife.elife.R;

/**
 * Created by Bill on 2016/5/2.
 */
public class MerchantFragment extends Fragment implements AbsListView.OnScrollListener {
    public static final int LOAD_LOGIC = 1;//上拉逻辑
    public static final int LOAD_DATA = 2;//加载更多数据
    public static final int UPDATE_DATA = 3;//更新数据
    //商家列表相关
    @Bind(R.id.fm_lv_main)
    ListView mFmLvMain;
    @Bind(R.id.fm_srl_main)
    SwipeRefreshLayout mFmsrlMain;
    ProgressBar mProgressBar; //尾部刷新视图
    //下面是分类标头的相关参数
    @Bind(R.id.fm_sp_sp1)
    Spinner mFmSpSp1;
    @Bind(R.id.fm_sp_sp2)
    Spinner mFmSpSp2;
    @Bind(R.id.fm_sp_sp3)
    Spinner mFmSpSp3;
    int result1;
    int result2;
    private MerchantFragmentAdapter mBaseAdapter;//商家列表信息核心数据适配器
    private List<String> data_list1;
    private List<String> data_list2;
    private List<String> data_list3;
    private BaseAdapter arr_adapter;//分类适配器
    private List<Merchant> mMerchantList;
    //这里进行刷新、上拉加载更多逻辑、上拉加载更多操作
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOAD_LOGIC:
                    update();
                    break;
                case LOAD_DATA:
                    break;
                case UPDATE_DATA:
                    break;
            }

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView;
        mView = inflater.inflate(R.layout.fragment_meichant, null);
        ButterKnife.bind(this, mView);
        initView();
        initData();//这个地方将来需要从网络获取数据
        //初始化listview
        initListView();
        initListener();
        return mView;
    }

    private void initView() {
        //设置刷新时动画的颜色，可以设置4个
        mFmsrlMain.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        LayoutInflater view = LayoutInflater.from(getContext());
        mProgressBar = (ProgressBar) view.inflate(R.layout.footer, null).findViewById(R.id.pb);
    }

    private void initListener() {
        //监听滑动
        mFmLvMain.setOnScrollListener(this);
        //监听商品列表
        mFmLvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "点击了" + mMerchantList.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        //监听分类标题
        mFmSpSp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                    Toast.makeText(getContext(), "点击了" + data_list1.get(position), Toast.LENGTH_SHORT).show();
                //被选中
                TextView v1 = (TextView) view.findViewById(R.id.ifms_tv_title);
                v1.setTextColor(Color.WHITE); //可以随意设置自己要的颜色值
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mFmSpSp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                    Toast.makeText(getContext(), "点击了" + data_list2.get(position), Toast.LENGTH_SHORT).show();
                //被选中
                TextView v1 = (TextView) view.findViewById(R.id.ifms_tv_title);
                v1.setTextColor(Color.WHITE); //可以随意设置自己要的颜色值


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mFmSpSp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                    Toast.makeText(getContext(), "点击了" + data_list3.get(position), Toast.LENGTH_SHORT).show();
                //被选中
                TextView v1 = (TextView) view.findViewById(R.id.ifms_tv_title);
                v1.setTextColor(Color.WHITE); //可以随意设置自己要的颜色值
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mFmsrlMain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 3; i++) {
                            Merchant merchant = new Merchant(10 + i, i + "", R.drawable.mart + "", "新的可乐超市", 3.0f, "12元起送");
                            mMerchantList.add(0, merchant);
                        }
                        ;
                        mBaseAdapter.notifyDataSetChanged();
                        mFmsrlMain.setRefreshing(false);
                    }
                }, 3000);
            }
        });

    }

    private void initListView() {
        //加载分类
        //适配器
        arr_adapter = new MerchantFragmentSortAdapter(getContext(), data_list1);
        mFmSpSp1.setAdapter(arr_adapter);
        //适配器
        arr_adapter = new MerchantFragmentSortAdapter(getContext(), data_list2);
        mFmSpSp2.setAdapter(arr_adapter);
        //适配器
        arr_adapter = new MerchantFragmentSortAdapter(getContext(), data_list3);
        mFmSpSp3.setAdapter(arr_adapter);

        //加载核心数据
        mFmLvMain.addFooterView(mProgressBar);
        mBaseAdapter = new MerchantFragmentAdapter(getContext(), mMerchantList);
        mFmLvMain.setAdapter(mBaseAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //监听状态改变
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_IDLE:
                //滑动结束
                break;
            case SCROLL_STATE_FLING:
                //手指离开屏幕
                if (result1 >= result2) {
                    //只发送一次,防止多次加载数据
                    if (!mHandler.hasMessages(LOAD_LOGIC))
                        mHandler.sendEmptyMessageDelayed(LOAD_LOGIC, 6000);
                }
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                //滑动中
                break;
        }
    }

    //滑动结束之后执行
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        result1 = firstVisibleItem + visibleItemCount;
        result2 = totalItemCount;
    }

    ///////////***********上面代码不用改动***********
////******************注意一下这个地方***********---------------
    private void update() {
        for (int i = 0; i < 3; i++) {
            Merchant merchant = new Merchant(10 + i, i + "", R.drawable.mart + "", "新2的可乐超市+" + mMerchantList.size() + "---" + i, 3.0f, "12元起送");
            mMerchantList.add(merchant);
        }
        getCoreData();//实际应用中注释掉上面代码，使用这一行即可
        mBaseAdapter.notifyDataSetChanged();
    }


    //******************这里是程序入口*****************---------------------
    //核心数据，从网络获取数据
    private void initData() {
        //加载分类列表
        //数据
        data_list1 = new ArrayList<>();
        data_list1.add("全部分类");
        data_list1.add("零食");
        data_list1.add("水果");
        data_list1.add("洗衣");
        data_list1.add("洗车");
        data_list1.add("茶饮");

        //数据
        data_list2 = new ArrayList<>();
        data_list2.add("商圈");
        data_list2.add("郑州航院");
        data_list2.add("华北水利水电大学");
        data_list2.add("河南中医大学");
        data_list2.add("河南财经大学");

        //数据
        data_list3 = new ArrayList<>();
        data_list3.add("智能排序");
        data_list3.add("好评优先");
        data_list3.add("离我最近");
        data_list3.add("价格最低");
        //加载核心数据
        getCoreData();
    }

    //********************这里获取核心数据***************----------------------
    private void getCoreData() {
        //加载核心数据
        mMerchantList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Merchant merchant = new Merchant(10 + i, i + "", R.drawable.mart + "", "可乐超市", 3.0f, "12元起送");
            mMerchantList.add(merchant);
        }
    }

}
