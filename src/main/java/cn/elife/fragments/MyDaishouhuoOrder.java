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
public class MyDaishouhuoOrder extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View daishouhuo=inflater.inflate(R.layout.frament_tab_daishouhuo,container,false);
        return daishouhuo;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
