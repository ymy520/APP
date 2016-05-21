package cn.elife.adapters;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import cn.elife.bean.CartGoods;
import cn.elife.elife.R;

/**
 * Author：张凯  on 2016/5/17 22:01
 * Blog: bukevin@github.io
 */
public class CartAdapter extends BaseAdapter {
    public static final int TYPE_1 = 0;
    public static final int TYPE_2 = 1;
    Context mContext;
    LayoutInflater mInflater;
    List<CartGoods> mList;
    private int count;

    public CartAdapter(Context mContext, List<CartGoods> mList) {
        this.mContext = mContext;
        this.mList = mList;
        mInflater= LayoutInflater.from(mContext);
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

    @Override
    public int getItemViewType(int position) {
        if(position <= mList.size()){
            return TYPE_1;
        }else{
            return TYPE_2;
        }
    }

    class ViewHolder{
        ImageView mImageView;
        TextView descTextView;
        TextView priceTextView;
        TextView countTextView;
        CheckBox mCheckBox;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if(convertView==null){

            convertView=mInflater.inflate(R.layout.cart_item_listview,null);
            mViewHolder=new ViewHolder();
            mViewHolder.mImageView= (ImageView) convertView.findViewById(R.id.cart_goodImage);
            mViewHolder.descTextView= (TextView) convertView.findViewById(R.id.cart_desc);
            mViewHolder.priceTextView= (TextView) convertView.findViewById(R.id.cart_price);
            mViewHolder.countTextView= (TextView) convertView.findViewById(R.id.cart_count);
            mViewHolder.mCheckBox= (CheckBox) convertView.findViewById(R.id.cart_checkBox);
            convertView.setTag(mViewHolder);
        }else{
            mViewHolder= (ViewHolder) convertView.getTag();
        }
        final CartGoods cartGoods=mList.get(position);
        mViewHolder.mImageView.setImageResource(cartGoods.getUrl());
        mViewHolder.descTextView.setText(cartGoods.getDesc());
        mViewHolder.priceTextView.setText(cartGoods.getPrice());
        mViewHolder.countTextView.setText(cartGoods.getNum());
        mViewHolder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //说明用户选中了第position行
                    cartGoods.setChecked(true);
                } else {
                    //说明用户取消了第position行
                    cartGoods.setChecked(false);
                }
            }
        });

        if(mList.get(position).isChecked())
            mViewHolder.mCheckBox.setChecked(true);
        else
            mViewHolder.mCheckBox.setChecked(false);

        return convertView;
    }
}
