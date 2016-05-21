package cn.elife.interfaces;

import android.view.View;

import cn.elife.bean.SnacksListData;

/**
 * Created by 叶梦雅 on 2016/5/20.
 */
public  interface OnRecyclerViewItemClickListener {
    void onItemClick(View view , SnacksListData snacksListData);
}
