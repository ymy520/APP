package cn.elife.elife;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import cn.elife.fragments.CartFragment;
import cn.elife.fragments.HomeFragment;
import cn.elife.fragments.MerchantFragment;
import cn.elife.fragments.MyFragment;
import cn.elife.fragments.SocialFragment;

public class MainActivity extends AppCompatActivity {
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    HomeFragment mHomeFragment;
    MerchantFragment mMerchantFragment;
    SocialFragment mSocialFragment;
    CartFragment mCartFragment;
    MyFragment mMyFragment;

    RadioGroup mRadioGroup;
    RadioButton bottom_br_home,bottom_br_merchant,bottom_br_social,bottom_br_cart,bottom_br_my;
    EditText top_et_search;



    //重定义的底部导航栏图标的大小
    private final int bottom_menu_icon_size = 87;
    private final int top_search_icon_size = 64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化各控件
        initViews();
        //重新定义底部菜单图标的大小
        defineIcon();
        //初始化默认界面样式
        initDefault();
        //设置监听事件
        setListener();
        

    }

    private void setListener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //根据点击选项，重新绘制界面
                resetView(checkedId);
            }
        });

    }

    private void resetView(int checkedId) {
        //根据radiobutton的值，重新绘制界面
        mFragmentTransaction = mFragmentManager.beginTransaction();

        //隐藏所有的碎片
        hideAllFragments();
        //显示当前碎片
        switch (checkedId){
            case R.id.bottom_rb_home:
                if(mHomeFragment == null){
                    mHomeFragment = new HomeFragment();
                    mFragmentTransaction.add(R.id.main_body,mHomeFragment);
                }else{
                    mFragmentTransaction.show(mHomeFragment);
                }
                break;
            case R.id.bottom_rb_merchant:
                if(mMerchantFragment == null){
                    mMerchantFragment = new MerchantFragment();
                    mFragmentTransaction.add(R.id.main_body,mMerchantFragment);
                }else{
                    mFragmentTransaction.show(mMerchantFragment);
                }
                break;
            case R.id.bottom_rb_cart:
                if(mCartFragment == null){
                    mCartFragment = new CartFragment();
                    mFragmentTransaction.add(R.id.main_body,mCartFragment);
                }else{
                    mFragmentTransaction.show(mCartFragment);
                }
                break;
            case R.id.bottom_rb_social:
                if(mSocialFragment == null){
                    mSocialFragment = new SocialFragment();
                    mFragmentTransaction.add(R.id.main_body,mSocialFragment);
                }else{
                    mFragmentTransaction.show(mSocialFragment);
                }
                break;
            case R.id.bottom_rb_my:
                if(mMyFragment == null){
                    mMyFragment = new MyFragment();
                    mFragmentTransaction.add(R.id.main_body,mMyFragment);
                }else{
                    mFragmentTransaction.show(mMyFragment);
                }
                break;
        }
        mFragmentTransaction.commit();
    }

    private void hideAllFragments() {
        //隐藏所有的碎片
        if(mHomeFragment != null && mHomeFragment.isAdded()){
            mFragmentTransaction.hide(mHomeFragment);
        }
        if(mMerchantFragment != null && mMerchantFragment.isAdded()){
            mFragmentTransaction.hide(mMerchantFragment);
        }
        if(mCartFragment != null && mCartFragment.isAdded()){
            mFragmentTransaction.hide(mCartFragment);
        }
        if(mSocialFragment != null && mSocialFragment.isAdded()){
            mFragmentTransaction.hide(mSocialFragment);
        }
        if(mMyFragment != null && mMyFragment.isAdded()){
            mFragmentTransaction.hide(mMyFragment);
        }

    }

    private void defineIcon() {
        /*
        设置底部的五大分类的图标的大小
         */
        Drawable home[] = bottom_br_home.getCompoundDrawables();
        home[1].setBounds(0,0,bottom_menu_icon_size,bottom_menu_icon_size);
        bottom_br_home.setCompoundDrawables(null,home[1],null,null);

        Drawable merchant[] = bottom_br_merchant.getCompoundDrawables();
        merchant[1].setBounds(0,0,bottom_menu_icon_size,bottom_menu_icon_size);
        bottom_br_merchant.setCompoundDrawables(null,merchant[1],null,null);

        Drawable social[] = bottom_br_social.getCompoundDrawables();
        social[1].setBounds(0,0,bottom_menu_icon_size,bottom_menu_icon_size);
        bottom_br_social.setCompoundDrawables(null,social[1],null,null);

        Drawable cart[] = bottom_br_cart.getCompoundDrawables();
        cart[1].setBounds(0,0,bottom_menu_icon_size,bottom_menu_icon_size);
        bottom_br_cart.setCompoundDrawables(null,cart[1],null,null);

        Drawable my[] = bottom_br_my.getCompoundDrawables();
        my[1].setBounds(0,0,bottom_menu_icon_size,bottom_menu_icon_size);
        bottom_br_my.setCompoundDrawables(null,my[1],null,null);

        /*
        设置头部的搜索框的图标的大小
         */
//        Drawable top_search[] = top_et_search.getCompoundDrawables();
//        top_search[0].setBounds(0,0,top_search_icon_size,top_search_icon_size);
//        top_search[2].setBounds(0,0,top_search_icon_size,top_search_icon_size);
//        top_et_search.setCompoundDrawables(top_search[0],null,top_search[2],null);

    }

    private void initDefault() {

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        mHomeFragment = new HomeFragment();

        mFragmentTransaction.add(R.id.main_body,mHomeFragment);

        mFragmentTransaction.commit();
    }

    private void initViews() {
        mRadioGroup = (RadioGroup) findViewById(R.id.bottom_rg_menu);

        bottom_br_home = (RadioButton) findViewById(R.id.bottom_rb_home);
        bottom_br_merchant = (RadioButton) findViewById(R.id.bottom_rb_merchant);
        bottom_br_social = (RadioButton) findViewById(R.id.bottom_rb_social);
        bottom_br_cart = (RadioButton) findViewById(R.id.bottom_rb_cart);
        bottom_br_my = (RadioButton) findViewById(R.id.bottom_rb_my);

//       top_et_search = (EditText) findViewById(R.id.tps_et_searchcontent);
//       //添加，为了跳转到搜索页面
//        top_et_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,SearchActivity.class);
//                startActivity(intent);
//            }
//        });

    }


}
