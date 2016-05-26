package cn.elife.elife;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.elife.adapters.CarAdapter;
import cn.elife.bean.CarMerchant;
import cn.elife.bean.DetailsEv;
import cn.elife.utils.SortCarMerch;

public class WashCarActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {

    public static final String TAG = "WashCarActivity";
    private RadioGroup radioGroup;
    private RadioButton distanceButton,evButton,orderButton;
    private List<CarMerchant> mList;
    private PullToRefreshListView mListView;
    CarAdapter carAdapter;
    //声明排序方法
    SortCarMerch<CarMerchant> sortCarMerch=new SortCarMerch<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash_car);
        initViews();
        initData();
        init();
        resetColor();
        distanceButton.setTextColor(Color.BLUE);
        radioGroup.setOnCheckedChangeListener(this);
        sortCarMerch.sortByMethod(mList,"getDistanceNum",false);
        carAdapter=new CarAdapter(WashCarActivity.this,mList);
        mListView.setAdapter(carAdapter);
        mListView.setMode(PullToRefreshBase.Mode.BOTH);

        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>(){
            @Override
            public void onPullDownToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                CarMerchant carMerchant=new CarMerchant(R.drawable.car3,"洗车店","仁爱路一号",105,96,7);
                mList.add(carMerchant);
                new FinishRefresh().execute();
                carAdapter.notifyDataSetChanged();
            }
            class FinishRefresh extends AsyncTask<Void, Void, Void> {
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    return null;
                }
                @Override
                protected void onPostExecute(Void result){
//          adapter.notifyDataSetChanged();
                    mListView.onRefreshComplete();
                }
            }
            @Override
            public void onPullUpToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                CarMerchant carMerchant=new CarMerchant(R.drawable.car1,"洗车店","仁爱路一号",108,90,13);
                mList.add(carMerchant);
                new FinishRefresh().execute();
                carAdapter.notifyDataSetChanged();
            }
        });

        //ListView的点击事件
        mListView.setOnItemClickListener(this);
    }

    private void init() {
        ILoadingLayout startLabels = mListView
                .getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉加载...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("正在载入...");// 刷新时
        startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

        ILoadingLayout endLabels = mListView.getLoadingLayoutProxy(
                false, true);
        endLabels.setPullLabel("上拉刷新...");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("正在载入...");// 刷新时
        endLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示
    }

    private void initData() {
        for(int i=1;i<=20;i++){
            CarMerchant carMerchant=new CarMerchant(R.drawable.car2,"领秀汽车美容","苏州工业园区星湖街荣域路805-104", 100+i,100-i,i);
            mList.add(carMerchant);
        }
    }

    private void resetColor() {
        distanceButton.setTextColor(Color.WHITE);
        evButton.setTextColor(Color.WHITE);
        orderButton.setTextColor(Color.WHITE);
    }

    private void initViews() {
        radioGroup= (RadioGroup) findViewById(R.id.car_radiogroup);
        distanceButton= (RadioButton) findViewById(R.id.car_distance);
        evButton= (RadioButton) findViewById(R.id.car_ev);
        orderButton= (RadioButton) findViewById(R.id.car_order);

        mList=new ArrayList<>();
        mListView= (PullToRefreshListView) findViewById(R.id.car_listView);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        resetColor();
        if(R.id.car_distance == checkedId){
            distanceButton.setTextColor(Color.BLUE);
            //销毁View
            mListView.invalidate();
            //倒序排序
            sortCarMerch.sortByMethod(mList,"getDistanceNum",false);
            CarAdapter carAdapter=new CarAdapter(WashCarActivity.this,mList);
            mListView.setAdapter(carAdapter);
        }else if(R.id.car_ev==checkedId){
            evButton.setTextColor(Color.BLUE);
            //销毁View
            mListView.invalidate();
            sortCarMerch.sortByMethod(mList,"getEvNum",true);
            CarAdapter carAdapter=new CarAdapter(WashCarActivity.this,mList);
            //重绘View
            mListView.setAdapter(carAdapter);
        }else if(R.id.car_order==checkedId){
            orderButton.setTextColor(Color.BLUE);
            //销毁View
            mListView.invalidate();
            sortCarMerch.sortByMethod(mList,"getOrderNum",true);
            CarAdapter carAdapter=new CarAdapter(WashCarActivity.this,mList);
            //重绘View
            mListView.setAdapter(carAdapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(WashCarActivity.this, "距离为"+mList.get(position).getDistanceNum(), Toast.LENGTH_SHORT).show();
    }
}
