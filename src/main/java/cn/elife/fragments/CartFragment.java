package cn.elife.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.elife.adapters.CartAdapter;
import cn.elife.bean.CartGoods;
import cn.elife.elife.R;

/**
 * Created by Bill on 2016/5/2.
 */
public class CartFragment extends Fragment {
    private View mView;
    private List<CartGoods> mList;
    private CartAdapter mCartAdapter;
    private ListView mListView;
    private CartGoods cartGoods;
    private CheckBox mChooseCheckBox;
    private TextView totalTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_cart,null);
        initViews();
        initData();
        mCartAdapter=new CartAdapter(getContext(),mList);
        mListView.setAdapter(mCartAdapter);
        return mView;
    }

    private void initViews() {
        mListView= (ListView) mView.findViewById(R.id.cart_listView);
        mChooseCheckBox= (CheckBox) mView.findViewById(R.id.cart_chooseCheckbox);
        totalTextView= (TextView) mView.findViewById(R.id.cart_Total);
    }

    private void initData() {
        mList=new ArrayList<>();
        for (int i=1;i<10;i++){
            cartGoods=new CartGoods(R.drawable.cart_test,"山湖美景","20",i+"");
            mList.add(cartGoods);
        }

        // 全选按钮的回调接口
        mChooseCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    // 遍历list的长度
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setChecked(true);
                    }
                    // 刷新listview和TextView的显示
                    dataChanged();
                }else{
                    // 遍历list的长度
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setChecked(false);
                    }
                    // 刷新listview和TextView的显示
                    dataChanged();
                }
            }
        });
    }
    // 刷新listview和TextView的显示
    private void dataChanged() {
        totalTextView.setText(mCartAdapter.getTotal()+"");
        // 通知listView刷新
        mCartAdapter.notifyDataSetChanged();
    }


}
