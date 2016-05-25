package cn.elife.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import cn.elife.adapters.DetailsAdapter;
import cn.elife.bean.DetailsEv;
import cn.elife.bean.DetailsHead1;
import cn.elife.bean.DetailsHead2;
import cn.elife.bean.MerchantDetails;
import cn.elife.elife.R;

/**
 * Author：张凯  on 2016/5/23 14:17
 * Blog: bukevin@github.io
 */
public class DetailsFragment extends Fragment{
    private List<Object> mList;
    private DetailsAdapter mDetailsAdapter;
    private PullToRefreshListView pullToRefreshView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View detailsView=inflater.inflate(R.layout.activity_tab_details,container,false);
        pullToRefreshView = (PullToRefreshListView) detailsView.findViewById(R.id.detail_list);
        initViews();
        initData();
        init();
        mDetailsAdapter=new DetailsAdapter(mList,getContext());
        pullToRefreshView.setAdapter(mDetailsAdapter);
        pullToRefreshView.setMode(PullToRefreshBase.Mode.BOTH);

        pullToRefreshView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>(){
            @Override
            public void onPullDownToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                DetailsEv detailsEv=new DetailsEv();
                detailsEv.setUserName("daben");
                detailsEv.setEvContent("下拉刷新");
                detailsEv.setEvTime("2016-5-24");
                mList.add(detailsEv);
                new FinishRefresh().execute();
                mDetailsAdapter.notifyDataSetChanged();
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
                    pullToRefreshView.onRefreshComplete();
                }
            }

            @Override
            public void onPullUpToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                // TODO Auto-generated method stub
                DetailsEv detailsEv=new DetailsEv();
                detailsEv.setUserName("daben");
                detailsEv.setEvContent("上拉刷新");
                detailsEv.setEvTime("2016-5-24");
                mList.add(detailsEv);
                new FinishRefresh().execute();
                mDetailsAdapter.notifyDataSetChanged();
            }
        });
        return detailsView;
    }

    private void init() {
        ILoadingLayout startLabels = pullToRefreshView
                .getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉加载...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("正在载入...");// 刷新时
        startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

        ILoadingLayout endLabels = pullToRefreshView.getLoadingLayoutProxy(
                false, true);
        endLabels.setPullLabel("上拉刷新...");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("正在载入...");// 刷新时
        endLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

//      // 设置下拉刷新文本
//      pullToRefresh.getLoadingLayoutProxy(false, true)
//              .setPullLabel("上拉刷新...");
//      pullToRefresh.getLoadingLayoutProxy(false, true).setReleaseLabel(
//              "放开刷新...");
//      pullToRefresh.getLoadingLayoutProxy(false, true).setRefreshingLabel(
//              "正在加载...");
//      // 设置上拉刷新文本
//      pullToRefresh.getLoadingLayoutProxy(true, false)
//              .setPullLabel("下拉刷新...");
//      pullToRefresh.getLoadingLayoutProxy(true, false).setReleaseLabel(
//              "放开刷新...");
//      pullToRefresh.getLoadingLayoutProxy(true, false).setRefreshingLabel(
//              "正在加载...");
    }

    private void initData() {
        DetailsHead1 detailsHead1=new DetailsHead1(R.drawable.details);
        mList.add(detailsHead1);
        DetailsHead2 detailsHead2=new DetailsHead2("苏州包子店","苏州市高教区","high 分长好吃");
        mList.add(detailsHead2);
        MerchantDetails merchantDetails=new MerchantDetails("20");
        mList.add(merchantDetails);
        for (int i=1;i<=10;i++){
            DetailsEv detailsEv=new DetailsEv("baoma","评价 "+i,"2016-5-24");
            mList.add(detailsEv);
        }
    }

    private void initViews() {
        mList = new ArrayList<>();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
