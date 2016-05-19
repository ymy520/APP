package cn.elife.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.elife.elife.R;

/**
 * Created by wgyscsf on 2016/5/17.
 */
public class MerchantFragmentSortAdapter extends BaseAdapter{

    private LayoutInflater mLayoutInflater;
    private List<String> mDataList;
    private Context mContext;

  public  MerchantFragmentSortAdapter(Context context,List<String> dataList) {
        this.mContext=context;
        this.mDataList = dataList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //数据不太多，没有使用ViewHolder进行处理。
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mLayoutInflater.inflate(R.layout.item_fragment_merchant_sort,null);
        TextView textView = (TextView) convertView.findViewById(R.id.ifms_tv_title);
        textView.setText(mDataList.get(position));
        return convertView;
    }
}
