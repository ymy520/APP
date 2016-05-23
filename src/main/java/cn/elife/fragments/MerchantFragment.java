package cn.elife.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
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
public class MerchantFragment extends Fragment {
    @Bind(R.id.fm_lv_main)
    ListView mFmLvMain;//商家列表信息核心数据
    private BaseAdapter mBaseAdapter;//商家列表信息核心数据适配器
    private View mView;
    private List<Merchant> mMerchantList;

    //下面是分类标头的相关参数
    @Bind(R.id.fm_sp_sp1)
    Spinner mFmSpSp1;
    @Bind(R.id.fm_sp_sp2)
    Spinner mFmSpSp2;
    @Bind(R.id.fm_sp_sp3)
    Spinner mFmSpSp3;
    private List<String> data_list1;
    private List<String> data_list2;
    private List<String> data_list3;
    private BaseAdapter arr_adapter;//适配器


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_meichant, null);
        ButterKnife.bind(this, mView);
        initData();//这个地方将来需要从网络获取数据
        //初始化listview
        initListView();
        initListener();
        return mView;
    }

    private void initListener() {
        //监听商品列表
        mFmLvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "点击了"+mMerchantList.get(position), Toast.LENGTH_SHORT).show();
            }
        });


        //监听分类标题
        mFmSpSp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "点击了"+data_list1.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mFmSpSp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "点击了"+data_list2.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mFmSpSp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "点击了"+data_list3.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

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
        mMerchantList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Merchant merchant = new Merchant(10 + i, i + "", R.drawable.mart + "", "可乐超市", 3.0f, "12元起送");
            mMerchantList.add(merchant);
        }
    }

    private void initListView() {
        //加载分类
        //适配器
        arr_adapter = new MerchantFragmentSortAdapter(getContext(),data_list1);
        mFmSpSp1.setAdapter(arr_adapter);
        //适配器
        arr_adapter = new MerchantFragmentSortAdapter(getContext(),data_list2);
        mFmSpSp2.setAdapter(arr_adapter);
        //适配器
        arr_adapter = new MerchantFragmentSortAdapter(getContext(),data_list3);
        mFmSpSp3.setAdapter(arr_adapter);



        //加载核心数据
        mBaseAdapter = new MerchantFragmentAdapter(getContext(), mMerchantList);
        mFmLvMain.setAdapter(mBaseAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
