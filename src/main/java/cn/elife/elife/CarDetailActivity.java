package cn.elife.elife;

import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import cn.elife.adapters.CarMerchAdapter;
import cn.elife.bean.CarEVContent;
import cn.elife.bean.CarEVNum;
import cn.elife.bean.CarMerchantHead;

public class CarDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView carBack;
    private ListView carMerchanListView;
    private List<Object> mList;
    private PercentRelativeLayout carWriteOrder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);
        initViews();
        initData();
        CarMerchAdapter carMerchAdapter=new CarMerchAdapter(CarDetailActivity.this,mList);
        carMerchanListView.setAdapter(carMerchAdapter);
        carWriteOrder.setOnClickListener(this);
    }

    private void initData() {
        CarMerchantHead merchantHead=new CarMerchantHead(R.drawable.car1,R.drawable.car2,R.drawable.car3,"江苏省","18825486923");
        mList.add(merchantHead);
        CarEVNum carEVNum=new CarEVNum(33);
        mList.add(carEVNum);
        for(int i=1;i<=20;i++) {
            CarEVContent carEVContent = new CarEVContent("悠悠", "2016-05-"+i,"服务非常好",1);
            mList.add(carEVContent);
        }
    }

    private void initViews() {
        carBack = (ImageView) findViewById(R.id.car_back);
        carMerchanListView = (ListView) findViewById(R.id.car_merchan_listView);
        mList=new ArrayList<>();
        carWriteOrder = (PercentRelativeLayout) findViewById(R.id.car_write_order);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(CarDetailActivity.this, "跳转到订单页面", Toast.LENGTH_SHORT).show();
    }
}
