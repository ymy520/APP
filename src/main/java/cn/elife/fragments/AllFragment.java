package cn.elife.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.List;

import cn.elife.adapters.AllGridAdapter;
import cn.elife.bean.AllItems;
import cn.elife.elife.R;

/**
 * Author：张凯  on 2016/5/23 14:20
 * Blog: bukevin@github.io
 */
public class AllFragment extends Fragment implements AdapterView.OnItemClickListener {
    private List<AllItems> mList;
    private AllGridAdapter gridAdapter;
    private PullToRefreshGridView gridView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View allView = inflater.inflate(R.layout.activity_tab_all,container,false);
        gridView= (PullToRefreshGridView) allView.findViewById(R.id.all_grid);
        initViews();
        initData();
        init();

        gridAdapter=new AllGridAdapter(getContext(),mList);
        gridView.setAdapter(gridAdapter);

        gridView.setMode(PullToRefreshBase.Mode.BOTH);
        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(
                    PullToRefreshBase<GridView> refreshView) {
                // TODO Auto-generated method stub
                AllItems allItems=new AllItems();
                allItems.setImageid(R.drawable.cart_test);
                allItems.setDesc("天津包子铺，上拉刷新");
                allItems.setNum("100");
                allItems.setPrice("2");
                mList.add(allItems);
                new FinishRefresh().execute();
                gridAdapter.notifyDataSetChanged();
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
                    gridView.onRefreshComplete();
                }
            }

            @Override
            public void onPullUpToRefresh(
                    PullToRefreshBase<GridView> refreshView) {
                // TODO Auto-generated method stub
                AllItems allItems=new AllItems();
                allItems.setImageid(R.drawable.details);
                allItems.setDesc("天津包子铺,下拉加载");
                allItems.setNum("100");
                allItems.setPrice("2");
                mList.add(allItems);
                new FinishRefresh().execute();
                gridAdapter.notifyDataSetChanged();
            }
        });

        gridView.setOnItemClickListener(this);

        return allView;
    }


    private void init() {
        ILoadingLayout startLabels = gridView
                .getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉加载...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("正在载入...");// 刷新时
        startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

        ILoadingLayout endLabels = gridView.getLoadingLayoutProxy(
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
        for (int i=0;i<10;i++){
            AllItems allItems=new AllItems(R.drawable.cart_test,"最后找到这个台湾老牌子，轻松秒掉以前吃惯的两个大品牌。","99","1"+i);
            mList.add(allItems);
        }
    }

    private void initViews() {
        mList=new ArrayList<>();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(),mList.get(position).getDesc(), Toast.LENGTH_SHORT).show();
    }
}
