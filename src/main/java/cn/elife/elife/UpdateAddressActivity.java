package cn.elife.elife;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.elife.bean.Address;
import cn.elife.utils.BaseActivity;
import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

public class UpdateAddressActivity extends BaseActivity implements OnWheelChangedListener {

    @Bind(R.id.ftb_iv_back)
    ImageView mFtbIvBack;
    @Bind(R.id.ftb_tv_title)
    TextView mFtbTvTitle;
    @Bind(R.id.ftb_iv_right)
    ImageView mFtbIvRight;
    @Bind(R.id.aua_et_name)
    EditText mAuaEtName;
    @Bind(R.id.aua_et_phone)
    EditText mAuaEtPhone;
    @Bind(R.id.aua_et_address)
    TextView mAuaEtAddress;
    @Bind(R.id.aua_et_street)
    EditText mAuaEtStreet;
    @Bind(R.id.aua_et_detail)
    EditText mAuaEtDetail;
    @Bind(R.id.aua_cb_defalut)
    CheckBox mAuaCbDefalut;
    @Bind(R.id.aua_tv_save)
    TextView mAuaTvSave;
    //标记是更新地址还是添加地址
    boolean flag = false;
    View view;
    //以下开始执行选择省市区三级联动逻辑代码控件
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private Button mBtnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address);
        ButterKnife.bind(this);
        initData();//这个方法主要处理编辑联系人操作
    }

    private void initData() {
         mFtbTvTitle.setText("添加收货地址");
        Intent intent = getIntent();
        Address address = (Address) intent.getSerializableExtra("address");
        if (address != null) {
            mFtbTvTitle.setText("更新收货地址");
            //首先设置保存按钮字
            mAuaTvSave.setText("更新收货地址");
            mAuaEtName.setText(address.getName());
            mAuaEtPhone.setText(address.getPhone());
            mAuaEtDetail.setText(address.getAddress());
            mAuaCbDefalut.setChecked(address.isDefaultAddresss());
            flag = true;
        }
    }

    @OnClick({R.id.ftb_iv_back, R.id.ftb_tv_title, R.id.ftb_iv_right, R.id.aua_et_name, R.id.aua_et_phone, R.id.aua_et_address, R.id.aua_et_street, R.id.aua_et_detail, R.id.aua_cb_defalut, R.id.aua_tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ftb_iv_back:
                back();//返回
                break;
            case R.id.ftb_tv_title:
                break;
            case R.id.ftb_iv_right:
                break;
            case R.id.aua_et_name:
                break;
            case R.id.aua_et_phone:
                break;
            case R.id.aua_et_address:
                choseDiolag();
                break;
            case R.id.aua_et_street:
                break;
            case R.id.aua_et_detail:
                break;
            case R.id.aua_cb_defalut:
                break;
            case R.id.aua_tv_save:
                save();//保存用户地址
                break;
        }
    }


    //保存用户地址；核心逻辑
    private void save() {
        Address address = new Address();
        if (TextUtils.isEmpty(mAuaEtName.getText().toString()) || TextUtils.isEmpty(mAuaEtPhone.getText().toString()) || TextUtils.isEmpty(mAuaEtAddress.getText()) || TextUtils.isEmpty(mAuaEtStreet.getText().toString())) {
            Toast.makeText(UpdateAddressActivity.this, "请先完善信息再保存！", Toast.LENGTH_SHORT).show();
        } else {
            address.setName(mAuaEtName.getText().toString());
            address.setPhone(mAuaEtPhone.getText().toString());
            address.setAddress(mAuaEtAddress.getText() + "," + mAuaEtStreet.getText().toString());
            address.setDefaultAddresss(mAuaCbDefalut.isChecked());
            if (flag) {
                ///核心逻辑在这
                Toast.makeText(UpdateAddressActivity.this, "开始执行更新操作！", Toast.LENGTH_SHORT).show();
            } else {
                ///核心逻辑在这
                Toast.makeText(UpdateAddressActivity.this, "开始执行添加操作！", Toast.LENGTH_SHORT).show();
            }

        }

    }

    //返回
    private void back() {
        //特别注意，返回之前判断是否要保存用户信息
        if (!(TextUtils.isEmpty(mAuaEtName.getText().toString()) && TextUtils.isEmpty(mAuaEtPhone.getText().toString()) && TextUtils.isEmpty(mAuaEtAddress.getText()) && TextUtils.isEmpty(mAuaEtStreet.getText().toString()))) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Add the buttons
            builder.setMessage("您的地址还没有保存，确认退出吗？")
                    .setTitle("提醒")
                    .setPositiveButton("确定退出", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //back
                            Intent intent = new Intent(UpdateAddressActivity.this, AddressActivity.class);
                            startActivity(intent);
                            //销毁
                           finish();
                        }
                    });
            builder.setNegativeButton("不退出", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(UpdateAddressActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                }
            });
            // Create the AlertDialog
            AlertDialog dialog = builder.create();
            //show
            dialog.show();
        } else {
            //back
            Intent intent = new Intent(UpdateAddressActivity.this, AddressActivity.class);
            startActivity(intent);
        }
    }


    private void choseDiolag() {
        setUpViews();
        setUpListener();
        setUpData();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // showSelectedResult();
                        mAuaEtAddress.setText(mCurrentProviceName + mCurrentCityName + mCurrentDistrictName);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        AlertDialog dialog = builder.create();
        //show
        dialog.show();

    }

    private void setUpListener() {
        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);

    }

    private void setUpViews() {
        view = LayoutInflater.from(UpdateAddressActivity.this).inflate(R.layout.dialog_activity_update_address, null);
        mViewProvince = (WheelView) view.findViewById(R.id.id_province);
        mViewCity = (WheelView) view.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) view.findViewById(R.id.id_district);
    }


    private void setUpData() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(UpdateAddressActivity.this, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
    }

    //这里处理选择省市区变换逻辑
    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        // TODO Auto-generated method stub
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
        mViewDistrict.setCurrentItem(0);
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

    private void showSelectedResult() {
        Toast.makeText(UpdateAddressActivity.this, "当前选中:" + mCurrentProviceName + "," + mCurrentCityName + ","
                + mCurrentDistrictName + "," + mCurrentZipCode, Toast.LENGTH_SHORT).show();
    }


    /**
     * 监听Back键按下事件,方法2:
     * 注意:
     * 返回值表示:是否能完全处理该事件
     * 在此处返回false,所以会继续传播该事件.
     * 在具体项目中此处的返回值视情况而定.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
              back();
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }

}
