package cn.elife.elife;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.elife.bean.Address;

public class UpdateAddressActivity extends AppCompatActivity {

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
    EditText mAuaEtAddress;
    @Bind(R.id.aua_et_street)
    EditText mAuaEtStreet;
    @Bind(R.id.aua_et_detail)
    EditText mAuaEtDetail;
    @Bind(R.id.aua_cb_defalut)
    CheckBox mAuaCbDefalut;
    @Bind(R.id.aua_tv_save)
    TextView mAuaTvSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address);
        ButterKnife.bind(this);
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
        if (TextUtils.isEmpty(mAuaEtName.getText().toString())||TextUtils.isEmpty(mAuaEtPhone.getText().toString()) || TextUtils.isEmpty(mAuaEtAddress.getText())|| TextUtils.isEmpty(mAuaEtStreet.getText().toString())) {
            Toast.makeText(UpdateAddressActivity.this, "请先完善信息再保存！", Toast.LENGTH_SHORT).show();
        } else {
            address.setName(mAuaEtName.getText().toString());
            address.setPhone(mAuaEtPhone.getText().toString());
            address.setAddress(mAuaEtAddress.getText() + "," + mAuaEtStreet.getText().toString());
            address.setDefaultAddresss(mAuaCbDefalut.isChecked());

            ///核心逻辑在这
            Toast.makeText(UpdateAddressActivity.this, "开始保存！", Toast.LENGTH_SHORT).show();
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
        }else{
            //back
            Intent intent = new Intent(UpdateAddressActivity.this, AddressActivity.class);
            startActivity(intent);
        }
    }
}
