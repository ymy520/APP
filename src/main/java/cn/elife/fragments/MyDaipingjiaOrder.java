package cn.elife.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.elife.elife.R;

/**
 * Created by 叶梦雅 on 2016/5/24.
 */
public class MyDaipingjiaOrder extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View daipingjia=inflater.inflate(R.layout.frament_daipingjai,container,false);
        return daipingjia;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
