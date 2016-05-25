package cn.elife.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.elife.elife.MyOrderActivity;
import cn.elife.elife.R;
import cn.elife.elife.SettingActivity;

/**
 * Created by Bill on 2016/5/2.
 */
public class MyFragment extends Fragment implements View.OnClickListener {
    View mView;
    @Bind(R.id.left_box)
    ImageView mLeftBox;
    @Bind(R.id.center_msg)
    ImageView mCenterMsg;
    @Bind(R.id.centeruserhead)
    ImageView mCenteruserhead;
    @Bind(R.id.centerusername)
    TextView mCenterusername;
    @Bind(R.id.myorderwenzi)
    TextView mMyorderwenzi;
    @Bind(R.id.myorderall)
    TextView mMyorderall;
    @Bind(R.id.wodedingdan)
    LinearLayout mWodedingdan;
    @Bind(R.id.mydaifukuan)
    LinearLayout mMydaifukuan;
    @Bind(R.id.mydaifahuo)
    LinearLayout mMydaifahuo;
    @Bind(R.id.mydaishouhuo)
    LinearLayout mMydaishouhuo;
    @Bind(R.id.mydaipingjia)
    LinearLayout mMydaipingjia;
    @Bind(R.id.centeraddress)
    TextView mCenteraddress;
    @Bind(R.id.center_rightpic01)
    ImageView mCenterRightpic01;
    @Bind(R.id.shouhuodizhi)
    LinearLayout mShouhuodizhi;
    @Bind(R.id.centermessage)
    TextView mCentermessage;
    @Bind(R.id.center_rightpic02)
    ImageView mCenterRightpic02;
    @Bind(R.id.wodexiaoxi)
    LinearLayout mWodexiaoxi;
    @Bind(R.id.centerjifen)
    TextView mCenterjifen;
    @Bind(R.id.center_rightpic03)
    ImageView mCenterRightpic03;
    @Bind(R.id.jifenshangcheng)
    LinearLayout mJifenshangcheng;
    @Bind(R.id.centeruser)
    TextView mCenteruser;
    @Bind(R.id.center_rightpic04)
    ImageView mCenterRightpic04;
    @Bind(R.id.gerenziliao)
    LinearLayout mGerenziliao;
    @Bind(R.id.centercollect)
    TextView mCentercollect;
    @Bind(R.id.center_rightpic05)
    ImageView mCenterRightpic05;
    @Bind(R.id.wodeshoucang)
    LinearLayout mWodeshoucang;
    @Bind(R.id.centersetting)
    TextView mCentersetting;
    @Bind(R.id.center_rightpic06)
    ImageView mCenterRightpic06;
    @Bind(R.id.shezhi)
    LinearLayout mShezhi;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.activity_my_center, null);
        ButterKnife.bind(this, view);
        initListener();
        return view;
    }

    private void initListener() {
       mWodedingdan.setOnClickListener(this);
        mShouhuodizhi.setOnClickListener(this);
        mWodexiaoxi.setOnClickListener(this);
        mJifenshangcheng.setOnClickListener(this);
        mGerenziliao.setOnClickListener(this);
        mWodeshoucang.setOnClickListener(this);
        mShezhi.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }
/**
 * 这里写跳转界面
 * */
    @OnClick({R.id.wodedingdan, R.id.mydaifukuan, R.id.mydaifahuo, R.id.mydaishouhuo, R.id.mydaipingjia, R.id.shouhuodizhi, R.id.wodexiaoxi, R.id.jifenshangcheng, R.id.gerenziliao, R.id.wodeshoucang, R.id.shezhi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wodedingdan:
                Intent intent=new Intent();
                intent.setClass(getContext(), MyOrderActivity.class);
                intent.putExtra("position",0);
                startActivity(intent);
                break;
            case R.id.mydaifukuan:
                Intent intentdaifukuan=new Intent();
                intentdaifukuan.setClass(getContext(), MyOrderActivity.class);
                intentdaifukuan.putExtra("position",1);
                startActivity(intentdaifukuan);
                break;
            case R.id.mydaifahuo:
                Intent intentdaifahuo=new Intent();
                intentdaifahuo.setClass(getContext(), MyOrderActivity.class);
                intentdaifahuo.putExtra("position",2);
                startActivity(intentdaifahuo);
                break;
            case R.id.mydaishouhuo:
                Intent intentdaishouhuo=new Intent();
                intentdaishouhuo.setClass(getContext(), MyOrderActivity.class);
                intentdaishouhuo.putExtra("position",3);
                startActivity(intentdaishouhuo);
                break;
            case R.id.mydaipingjia:
                Intent intentdaipingjia=new Intent();
                intentdaipingjia.setClass(getContext(), MyOrderActivity.class);
                intentdaipingjia.putExtra("position",4);
                startActivity(intentdaipingjia);
                break;
            case R.id.shouhuodizhi:
                break;
            case R.id.wodexiaoxi:
                break;
            case R.id.jifenshangcheng:
                break;
            case R.id.gerenziliao:
                break;
            case R.id.wodeshoucang:
                break;
            case R.id.shezhi:
                Intent intentshezhi=new Intent();
                intentshezhi.setClass(getContext(), SettingActivity.class);
                startActivity(intentshezhi);
                break;
        }
    }
}
