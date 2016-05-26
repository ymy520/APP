package cn.elife.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.elife.bean.AllItems;
import cn.elife.elife.R;

/**
 * Author：张凯  on 2016/5/24 17:30
 * Blog: bukevin@github.io
 *
 * 适配器：商家详情页面的全部商品
 */
public class AllGridAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<AllItems> mList;

    public AllGridAdapter(Context mContext, List<AllItems> mList) {
        this.mContext = mContext;
        this.mList = mList;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        private ImageView id;
        private TextView desc;
        private TextView price;
        private TextView num;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.all_items,null);
            viewHolder=new ViewHolder();
            viewHolder.id= (ImageView) convertView.findViewById(R.id.all_itemsimage);
            viewHolder.desc= (TextView) convertView.findViewById(R.id.all_desc);
            viewHolder.price= (TextView) convertView.findViewById(R.id.all_price);
            viewHolder.num= (TextView) convertView.findViewById(R.id.all_num);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //显示
        viewHolder.id.setImageResource(mList.get(position).getImageid());
        viewHolder.desc.setText(mList.get(position).getDesc());
        viewHolder.price.setText(mList.get(position).getPrice());
        viewHolder.num.setText(mList.get(position).getNum());
        return convertView;
    }

}
