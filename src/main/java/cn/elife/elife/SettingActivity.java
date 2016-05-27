package cn.elife.elife;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.left_box)
    ImageView mLeftBox;
    @Bind(R.id.anquan)
    TextView mAnquan;
    @Bind(R.id.setting01)
    ImageView mSetting01;
    @Bind(R.id.zhanghuanquan)
    LinearLayout mZhanghuanquan;
    @Bind(R.id.clearcache)
    TextView mClearcache;
    @Bind(R.id.setting02)
    TextView mSetting02;
    @Bind(R.id.qingchuhuancun)
    LinearLayout mQingchuhuancun;
    @Bind(R.id.updataversion)
    TextView mUpdataversion;
    @Bind(R.id.setting03)
    ImageView mSetting03;
    @Bind(R.id.banbengengxin)
    LinearLayout mBanbengengxin;
    @Bind(R.id.servicecenter)
    TextView mServicecenter;
    @Bind(R.id.settong04)
    TextView mSettong04;
    @Bind(R.id.kefuzhongxin)
    LinearLayout mKefuzhongxin;
    @Bind(R.id.opinion)
    TextView mOpinion;
    @Bind(R.id.setting05)
    ImageView mSetting05;
    @Bind(R.id.yijianfankui)
    LinearLayout mYijianfankui;
    @Bind(R.id.about)
    TextView mAbout;
    @Bind(R.id.center_rightpic06)
    ImageView mCenterRightpic06;
    @Bind(R.id.guanyu)
    LinearLayout mGuanyu;
    @Bind(R.id.setting_exit)
    TextView mSettingExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        ButterKnife.bind(this);
        initListener();

    }

    private void initListener() {
        mZhanghuanquan.setOnClickListener(this);
        mQingchuhuancun.setOnClickListener(this);
        mBanbengengxin.setOnClickListener(this);
        mKefuzhongxin.setOnClickListener(this);
        mYijianfankui.setOnClickListener(this);
        mGuanyu.setOnClickListener(this);
    }


    @OnClick({R.id.zhanghuanquan, R.id.qingchuhuancun, R.id.banbengengxin, R.id.kefuzhongxin, R.id.yijianfankui, R.id.guanyu,R.id.setting_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhanghuanquan:
                Toast.makeText(SettingActivity.this, "账户与安全", Toast.LENGTH_SHORT).show();
                break;
            case R.id.qingchuhuancun:
                showClearMessage();
                break;
            case R.id.banbengengxin:
                Toast.makeText(SettingActivity.this, "已是最新版本", Toast.LENGTH_SHORT).show();
                break;
            case R.id.kefuzhongxin:
                showPhone();
                break;
            case R.id.yijianfankui:
                Intent intent=new Intent();
                intent.setClass(SettingActivity.this,FeedBackActivity.class);
                startActivity(intent);
                break;
            case R.id.guanyu:
                Toast.makeText(SettingActivity.this, "关于", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting_exit:
                Toast.makeText(SettingActivity.this, "退出账户", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showPhone() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        builder.setTitle("客服电话").setMessage("123-456-789")
                .setPositiveButton("拨打电话", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SettingActivity.this, "拨打电话", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SettingActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                });
        // 通过builder创建一个alertdialog对象
        AlertDialog dialog = builder.create();
        //显示对话框
        dialog.show();
    }

    private void showClearMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        builder.setTitle("清除缓存").setMessage("是否清除所有缓存数据？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SettingActivity.this, "缓存已经清空", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SettingActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                });
        // 通过builder创建一个alertdialog对象
        AlertDialog dialog = builder.create();
        //显示对话框
        dialog.show();
    }
}
