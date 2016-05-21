package cn.elife.fragments;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.elife.adapters.homeShowAllAdapter;
import cn.elife.adapters.homeShowBannerAdapter;
import cn.elife.adapters.homeShowTypeAdapter;
import cn.elife.bean.Goods;
import cn.elife.elife.MainActivity;
import cn.elife.elife.R;
import cn.elife.elife.SnacksActivity;

/**
 * TODO- 在页面没有获取焦点的时候关闭自动轮播
 * Created by Bill on 2016/5/2.
 */
public class HomeFragment extends Fragment {
//    这个fragment链接的布局文件
    View mView;

    public static final int RECYCLERVIEWCOLUMNCOUNT = 2;//一行有几个商品
    public static int mCurrentItem = Integer.MAX_VALUE / 2;//设置viewpager显示在最大的值的中间，可以左右进行滑动
    public static final int BANNER_IMAGE_UPDATE = 1;//轮播图应该更新了
    public static final int BANNER_IMAGE_CHANGED = 2;//轮播图改变了状态，被用户拖动到了某页
    public static final int BANNER_REFRESH_TIME = 5 * 1000;//轮播图刷新的时间

    GridView mGridView;//分类展示的组件GridView
//    RecyclerView mRecyclerView;//替代listview//展示首页所有内容的listview
    PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;//替代listview//展示首页所有内容的listview
    ViewPager mViewPager;//轮播图的ViewPager

    List<Goods> mgoodslist;//商品展示的数据集合
    List<Type> mTypeList;//上面的分类
    List<View> mViewList;//轮播图的数据集合

    homeShowAllAdapter mHomeShowAllAdapter;//商品展示的适配器
    homeShowTypeAdapter mHomeShowTypeAdapter;//八个分类的适配器
    homeShowBannerAdapter mHomeShowBannerAdapter;//轮播图的适配器


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home,null);
        //初始化控件
        initViews();
        //初始化适配器的数据
        initData();
        //初始化适配器
        initAdapter();
        //设置适配器相关参数，并绑定
        setAdapter();
        //设置监听事件
        setListener();

        return mView;
    }

    private void setAdapter() {

        /****************设置关于底部商品展示的RecyclerView的适配器的相关参数并绑定**********************/
        //设置瀑布布局
        mPullLoadMoreRecyclerView.setStaggeredGridLayout(RECYCLERVIEWCOLUMNCOUNT);
        //绑定适配器
        mPullLoadMoreRecyclerView.setAdapter(mHomeShowAllAdapter);
        //给RecyclerView添加底部分隔线
//        mPullLoadMoreRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));

        /****************设置关于上边八个分类的的适配器的相关参数并绑定**********************/
        mGridView.setAdapter(mHomeShowTypeAdapter);

        /****************设置关于中间图片轮播的适配器的相关参数并绑定**********************/
        mViewPager.setAdapter(mHomeShowBannerAdapter);
        mViewPager.setCurrentItem(mCurrentItem);
        //开启轮播图的自动播放
        mHandler.sendEmptyMessageDelayed(BANNER_IMAGE_UPDATE, BANNER_REFRESH_TIME);

    }

    private void initAdapter() {
        //实例化商品展示的RecyclerView的适配器
        mHomeShowAllAdapter = new homeShowAllAdapter(mgoodslist,getContext());
        //实例化展示商品分类的适配器
        mHomeShowTypeAdapter = new homeShowTypeAdapter(getContext(),mTypeList);
        //实例化轮播图的适配器
        mHomeShowBannerAdapter = new homeShowBannerAdapter(mViewList,getContext());

    }

    private void setListener() {

        /********为商品展示的RecyclerView设置监听事件*******/
//        mHomeShowAllAdapter.setOnItemClickListener(new homeShowAllAdapter.MyItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                show("我点击了RecyclerView中的" + mgoodslist.get(position).getName());
//            }
//        });

        /********为展示分类的GirdView设置监听事件**********/
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             SnacksActivity.actionStart(getActivity());
            }
        });

        /********为轮播图的viewpager设置监听事件**********/
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mHandler.sendMessage(Message.obtain(mHandler,BANNER_IMAGE_CHANGED,position,0));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /*******为上拉加载，下拉刷新的空间设置监听事件********/
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }

        });





    }

    private void initData() {

        mgoodslist = new ArrayList<Goods>();
        Goods goods1 = new Goods("牛肉干",15.0,R.drawable.goods,98);
        Goods goods2 = new Goods("大豫竹",14.0,R.drawable.goods,98);
        Goods goods3 = new Goods("火腿肠",15.0,R.drawable.goods,98);
        Goods goods4 = new Goods("酸乌梅",18.0,R.drawable.goods,98);
        Goods goods5 = new Goods("洗面奶",56.0,R.drawable.goods,98);
        Goods goods6 = new Goods("卫龙",12.0,R.drawable.goods,98);
        Goods goods7 = new Goods("老干妈",15.0,R.drawable.goods,98);
        Goods goods8 = new Goods("香酥米",16.0,R.drawable.goods,98);
        Goods goods9 = new Goods("冰糖葫芦",17.0,R.drawable.goods,98);
        Goods goods10 = new Goods("鱿鱼",12.0,R.drawable.goods,98);
        Goods goods11 = new Goods("哇哈哈哈",12.0,R.drawable.goods,98);
        mgoodslist.add(goods1);mgoodslist.add(goods2);mgoodslist.add(goods3);
        mgoodslist.add(goods4);mgoodslist.add(goods5);mgoodslist.add(goods6);
        mgoodslist.add(goods7);mgoodslist.add(goods8);mgoodslist.add(goods9);
        mgoodslist.add(goods10);mgoodslist.add(goods11);

        //初始化数据集合
        mTypeList = new ArrayList<Type>();
        Type type1 = new Type("零食",R.mipmap.lingshi);
        Type type2 = new Type("洗衣",R.mipmap.xiyi);
        Type type3 = new Type("洗车",R.mipmap.xiche);
        Type type4 = new Type("航空",R.mipmap.hangkong);
        Type type5 = new Type("水果",R.mipmap.shuiguo);
        Type type6 = new Type("奶茶",R.mipmap.naicha);
        Type type7 = new Type("娱乐",R.mipmap.yule);
        Type type8 = new Type("宝典",R.mipmap.baodian);
        mTypeList.add(type1);mTypeList.add(type2);mTypeList.add(type3);
        mTypeList.add(type4);mTypeList.add(type5);mTypeList.add(type6);
        mTypeList.add(type7);mTypeList.add(type8);


        //初始化轮播图片
        mViewList = new ArrayList<View>();
        int[] imageId = {
                R.drawable.banner1,R.drawable.banner2,R.drawable.banner3,
                R.drawable.banner4,R.drawable.banner5
        };
        for(int i = 0;i < imageId.length;i++) {
            View view = LayoutInflater.from(mView.getContext()).inflate(R.layout.home_banner_item,null);
            ImageView mImageView = (ImageView) view.findViewById(R.id.item_iv_content);
            mImageView.setImageResource(imageId[i]);
            mViewList.add(view);
        }

    }

    private void initViews() {
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) mView.findViewById(R.id.home_plmrv_showAll);
        mGridView = (GridView) mView.findViewById(R.id.home_gv_type);
        mViewPager = (ViewPager) mView.findViewById(R.id.home_vp_bunner);
    }


    public void show(String text){
        Toast.makeText(mView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public class Type{
        String title;
        int image;

        public Type(String title, int image) {
            this.title = title;
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }
    }


    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int action = message.what;

            if(mHandler.hasMessages(BANNER_IMAGE_UPDATE)){
                mHandler.removeMessages(BANNER_IMAGE_UPDATE);
            }
            switch (action){
                case BANNER_IMAGE_UPDATE:
                    //轮播图进行更新
                    mCurrentItem += 1;
                    mViewPager.setCurrentItem(mCurrentItem);
                    mHandler.sendEmptyMessageDelayed(BANNER_IMAGE_UPDATE, BANNER_REFRESH_TIME);
                    break;
                case BANNER_IMAGE_CHANGED:
                    //该位置是手动滑到的，记录当前值，然后再进行自动轮播
                    mCurrentItem = message.arg1;
                    mViewPager.setCurrentItem(mCurrentItem);
                    mHandler.sendEmptyMessageDelayed(BANNER_IMAGE_UPDATE,BANNER_REFRESH_TIME);
                    break;
            }
        }
    };


}