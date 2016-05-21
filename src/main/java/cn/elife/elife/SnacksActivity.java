package cn.elife.elife;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.elife.adapters.MySnacksListAdapter;
import cn.elife.adapters.SnacksArrayAdapter;
import cn.elife.adapters.SnacksBannerAdapter;
import cn.elife.bean.SnacksBanner;
import cn.elife.bean.SnacksListData;
import cn.elife.interfaces.OnRecyclerViewItemClickListener;
import cn.elife.utils.DividerGridItemDecoration;
import cn.elife.utils.DividerItemDecoration;

import static android.graphics.Color.RED;
import static cn.elife.elife.R.layout.activity_snacks;

public class SnacksActivity extends Activity {


    public static String IMAGE_CACHE_PATH = "imageloader/Cache"; // 图片缓存路径

    @Bind(R.id.snacksclassify)
    Spinner mSnacksclassify;
    @Bind(R.id.snackshot)
    Spinner mSnackshot;
    @Bind(R.id.snacksplase)
    Spinner mSnacksplase;

    private ViewPager adViewPager;
    private List<ImageView> imageViews;// 滑动的图片集合

    private List<View> dots; // 图片标题正文的那些点
    private List<View> dotList;

    private int currentItem = 0; // 当前图片的索引号
    // 定义的五个指示点
    private View dot0;
    private View dot1;
    private View dot2;
    private View dot3;
    private View dot4;

    private ScheduledExecutorService scheduledExecutorService;

    // 异步加载图片
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;

    // 轮播banner的数据
    private List<SnacksBanner> adList;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            adViewPager.setCurrentItem(currentItem);
        }

        ;
    };

    ArrayAdapter<String> mAdapter;
    String[] mStringArray1, mStringArray2, mStringArray3;

    RecyclerView mRecyclerView;
    MySnacksListAdapter mMySnacksListAdapter;
    List<SnacksListData> mList;

    public static void  actionStart(Context context){
        Intent intent=new Intent(context,SnacksActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_snacks);
        ButterKnife.bind(this);
        // 使用ImageLoader之前初始化
        initImageLoader();

        // 获取图片加载实例
        mImageLoader = ImageLoader.getInstance();

        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.snackbangong)
                .showImageForEmptyUri(R.drawable.snackbangong) // empty URI时显示的图片
                .showImageOnFail(R.drawable.snackbangong) // 不是图片文件 显示图片
                .cacheInMemory(true).cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY).build();

        initData();
        startAd();
        initViews();
        initAdapter();
        setGridManager();
        setSpinner();
    }

    private void initAdapter() {
        mMySnacksListAdapter = new MySnacksListAdapter(mList, SnacksActivity.this);
        GridLayoutManager grid = new GridLayoutManager(this, 2);
       /* //给RecyclerView添加底部分隔线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));*/
        mRecyclerView.setLayoutManager(grid);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mMySnacksListAdapter);

        mMySnacksListAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, SnacksListData snacksListData) {
                Toast.makeText(SnacksActivity.this, "RecyclerView获取到单击事件" + snacksListData, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initImageLoader() {
        File cacheDir = StorageUtils
                .getOwnCacheDirectory(getApplicationContext(),
                        IMAGE_CACHE_PATH);//缓存文件夹路径

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this).defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new LruMemoryCache(12 * 1024 * 1024))//可以通过自己的内存缓存实现
                .memoryCacheSize(12 * 1024 * 1024)// 内存缓存的最大值
                .discCacheSize(32 * 1024 * 1024).discCacheFileCount(100)
                .discCache(new UnlimitedDiscCache(cacheDir))
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();//开始构建
        //  单例ImageLoader类的初始化
        ImageLoader.getInstance().init(config);
    }

    private void initData() {
        // 轮播图数据
        adList = getBannerAd();

        imageViews = new ArrayList<ImageView>();

        // 点
        dots = new ArrayList<View>();
        dotList = new ArrayList<View>();
        dot0 = findViewById(R.id.v_dot0);
        dot1 = findViewById(R.id.v_dot1);
        dot2 = findViewById(R.id.v_dot2);
        dot3 = findViewById(R.id.v_dot3);
        dot4 = findViewById(R.id.v_dot4);
        dots.add(dot0);
        dots.add(dot1);
        dots.add(dot2);
        dots.add(dot3);
        dots.add(dot4);


        adViewPager = (ViewPager) findViewById(R.id.vp);
        adViewPager.setAdapter(new SnacksBannerAdapter(adList, imageViews, SnacksActivity.this));// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        adViewPager.setOnPageChangeListener(new MyPageChangeListener());
        addDynamicView();
    }

    private void addDynamicView() {
        // 动态添加图片和下面指示的圆点
        // 初始化图片资源
        for (int i = 0; i < adList.size(); i++) {
            ImageView imageView = new ImageView(this);
            // 异步加载图片
            mImageLoader.displayImage(adList.get(i).getImgUrl(), imageView,
                    options);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
            dots.get(i).setVisibility(View.VISIBLE);
            dotList.add(dots.get(i));
        }
    }

    //数据来源
    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.snacksRecyclerView);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mList = new ArrayList<>();
        SnacksListData d1 = new SnacksListData("松软甜美QQ糖100g/袋1", 10.5, R.drawable.snacks, 1000);
        SnacksListData d2 = new SnacksListData("松软甜美QQ糖100g/袋2", 10.5, R.drawable.snacks, 1000);
        SnacksListData d3 = new SnacksListData("松软甜美QQ糖100g/袋3", 10.5, R.drawable.snacks, 1000);
        SnacksListData d4 = new SnacksListData("松软甜美QQ糖100g/袋4", 10.5, R.drawable.snacks, 1000);
        SnacksListData d5 = new SnacksListData("松软甜美QQ糖100g/袋5", 10.5, R.drawable.snacks, 1000);
        SnacksListData d6 = new SnacksListData("松软甜美QQ糖100g/袋6", 10.5, R.drawable.snacks, 1000);
        SnacksListData d7 = new SnacksListData("松软甜美QQ糖100g/袋7", 10.5, R.drawable.snacks, 1000);
        SnacksListData d8 = new SnacksListData("松软甜美QQ糖100g/袋8", 10.5, R.drawable.snacks, 1000);
        SnacksListData d9 = new SnacksListData("松软甜美QQ糖100g/袋9", 10.5, R.drawable.snacks, 1000);
        SnacksListData d10 = new SnacksListData("松软甜美QQ糖100g/袋", 10.5, R.drawable.snacks, 1000);
        SnacksListData d11 = new SnacksListData("松软甜美QQ糖100g/袋", 10.5, R.drawable.snacks, 1000);
        mList.add(d1);
        mList.add(d2);
        mList.add(d3);
        mList.add(d4);
        mList.add(d5);
        mList.add(d6);
        mList.add(d7);
        mList.add(d8);
        mList.add(d9);
        mList.add(d10);
        mList.add(d11);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void startAd() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每三秒切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 3,
                TimeUnit.SECONDS);
    }


    public void setSpinner() {

        //绑定适配器和值
        mStringArray1 = getResources().getStringArray(R.array.classify);
        //使用自定义的ArrayAdapter
        mAdapter = new SnacksArrayAdapter(SnacksActivity.this, mStringArray1);
        mSnacksclassify.setAdapter(mAdapter);
        mSnacksclassify.setSelection(0, true); //设置默认选中项，此处为默认选中第1个值


        mStringArray2 = getResources().getStringArray(R.array.hot);
        //使用自定义的ArrayAdapter
        mAdapter = new SnacksArrayAdapter(SnacksActivity.this, mStringArray2);
        mSnackshot.setAdapter(mAdapter);
        mSnackshot.setSelection(0, true); //设置默认选中项，此处为默认选中第1个值
        mSnackshot.setBackgroundColor(RED);

        mStringArray3 = getResources().getStringArray(R.array.place);
        //使用自定义的ArrayAdapter
        mAdapter = new SnacksArrayAdapter(SnacksActivity.this, mStringArray3);
        mSnacksplase.setAdapter(mAdapter);
        mSnacksplase.setSelection(0, true); //设置默认选中项，此处为默认选中第1个值


        mSnacksclassify.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SnacksActivity.this, "选中了：" + mStringArray1[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mSnackshot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SnacksActivity.this, "选中了：" + mStringArray2[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mSnacksplase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SnacksActivity.this, "选中了：" + mStringArray3[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private class ScrollTask implements Runnable {

        @Override
        public void run() {
            synchronized (adViewPager) {
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 当Activity不可见的时候停止切换
        scheduledExecutorService.shutdown();
    }

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        private int oldPosition = 0;

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;

            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }
    }


    //grid布局
    private void setGridManager() {
        GridLayoutManager grid = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(grid);
    }

    public static List<SnacksBanner> getBannerAd() {
        List<SnacksBanner> adList = new ArrayList<SnacksBanner>();

        SnacksBanner adDomain = new SnacksBanner();
        adDomain.setId("108078");
        adDomain.setImgUrl
                ("drawable://" + R.drawable.snackchihuo);

        adDomain.setAd(false);
        adList.add(adDomain);

        SnacksBanner adDomain2 = new SnacksBanner();
        adDomain.setId("108078");
        adDomain2.setImgUrl
                ("drawable://" + R.drawable.snacknvshen);
        adDomain2.setAd(false);
        adList.add(adDomain2);

        SnacksBanner adDomain3 = new SnacksBanner();
        adDomain.setId("108078");
        adDomain3.setImgUrl
                ("drawable://" + R.drawable.snackbangong);
        adDomain3.setAd(false);
        adList.add(adDomain3);

        SnacksBanner adDomain4 = new SnacksBanner();
        adDomain.setId("108078");
        adDomain4.setImgUrl
                ("drawable://" + R.drawable.snackzaocan);
        adDomain4.setAd(false);
        adList.add(adDomain4);

        SnacksBanner adDomain5 = new SnacksBanner();
        adDomain.setId("108078");
        adDomain5.setImgUrl
                ("drawable://" + R.drawable.snackchihuo);
        adDomain5.setAd(false);
        adList.add(adDomain5);
        return adList;
    }
}
