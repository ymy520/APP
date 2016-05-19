package cn.elife.elife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.elife.adapters.AddressActivityAdapter;
import cn.elife.bean.Address;

public class AddressActivity extends AppCompatActivity {

    @Bind(R.id.ftb_iv_back)
    ImageView mFtbIvBack;
    @Bind(R.id.ftb_tv_title)
    TextView mFtbTvTitle;
    @Bind(R.id.ftb_iv_right)
    ImageView mFtbIvRight;
    @Bind(R.id.aa_lv_addresslist)
    ListView mAaLvAddresslist;
    @Bind(R.id.aa_tv_add)
    TextView mAaTvAdd;
    //列表数据
    private List<Address> mAddressList;
    private BaseAdapter mBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        initView();
        initData();
        initAdapter();
    }

    private void initView() {
        mFtbTvTitle.setText("管理收货地址");
    }

    private void initData() {
        mAddressList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Address address = new Address();
            address.setName("王高远" + i);
            address.setPhone("131409459" + i);
            address.setAddress("江苏省苏州市吴中区开发区江苏省苏州市吴中区仁爱路" + i + "号");
            if (i == 5) address.setDefaultAddresss(true);
            mAddressList.add(address);
        }
    }

    private void initAdapter() {
        mBaseAdapter = new AddressActivityAdapter(this, mAddressList);
        mAaLvAddresslist.setAdapter(mBaseAdapter);
        mAaLvAddresslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Address address = mAddressList.get(position);
                Toast.makeText(AddressActivity.this, "点击了" + address, Toast.LENGTH_SHORT).show();
                //以下开始处理编辑操作
                editAddess(address);
            }
        });
    }


    @OnClick({R.id.ftb_iv_back, R.id.ftb_tv_title, R.id.ftb_iv_right, R.id.aa_tv_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ftb_iv_back:
                break;
            case R.id.ftb_tv_title:
                break;
            case R.id.ftb_iv_right:
                break;
            case R.id.aa_tv_add:
                addAddress();//添加新地址
                break;
        }
    }
    //添加新的收货地址
    private void addAddress() {
        Intent intent=new Intent(AddressActivity.this,UpdateAddressActivity.class);
        startActivity(intent);
    }

    //编辑收货地址,核心逻辑
    private void editAddess(Address address) {
        Intent intent=new Intent(AddressActivity.this,UpdateAddressActivity.class);
        intent.putExtra("address",address);
        startActivity(intent);
    }

}
