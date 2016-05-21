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
    public static final int TYPE_COUNT = 2;
    Context mContext;
    LayoutInflater mInflater;
    List<CartGoods> mList;
    private float total;
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
        if(position+1 < mList.size()){
            return TYPE_1;
        }else{
            return TYPE_2;
        }
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    class ViewHolder{
        ImageView mImageView;
        TextView descTextView;
        TextView priceTextView;
        TextView countTextView;
        CheckBox mCheckBox;
    }
    class ViewHolderImage{
        ImageView mImageView1;
        ImageView mImageView2;
        ImageView mImageView3;
        ImageView mImageView4;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder=null;
        ViewHolderImage mViewHolderImage=null;
        int type=getItemViewType(position);
        if(convertView==null){
            switch (type) {
                case TYPE_1:
                    convertView = mInflater.inflate(R.layout.cart_item_listview, null);
                    mViewHolder = new ViewHolder();
                    mViewHolder.mImageView = (ImageView) convertView.findViewById(R.id.cart_goodImage);
                    mViewHolder.descTextView = (TextView) convertView.findViewById(R.id.cart_desc);
                    mViewHolder.priceTextView = (TextView) convertView.findViewById(R.id.cart_price);
                    mViewHolder.countTextView = (TextView) convertView.findViewById(R.id.cart_count);
                    mViewHolder.mCheckBox = (CheckBox) convertView.findViewById(R.id.cart_checkBox);
                    convertView.setTag(mViewHolder);
                    break;
                case TYPE_2:
                    convertView=mInflater.inflate(R.layout.cart_item_bottom,null);
                    mViewHolderImage=new ViewHolderImage();
                    mViewHolderImage.mImageView1= (ImageView) convertView.findViewById(R.id.cart_image1);
                    mViewHolderImage.mImageView2= (ImageView) convertView.findViewById(R.id.cart_image2);
                    mViewHolderImage.mImageView3= (ImageView) convertView.findViewById(R.id.cart_image3);
                    mViewHolderImage.mImageView4= (ImageView) convertView.findViewById(R.id.cart_image4);
                    convertView.setTag(mViewHolderImage);
                    break;
            }
        }else{
            switch (type){
                case TYPE_1:
                    mViewHolder= (ViewHolder) convertView.getTag();
                    break;
                case TYPE_2:
                    mViewHolderImage= (ViewHolderImage) convertView.getTag();
                    break;
            }
        }
        switch (type){
            case TYPE_1:
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
                            int num=Integer.parseInt(mList.get(position).getNum());
                            float price=Float.parseFloat(mList.get(position).getPrice());
                            total=num*price;
                        } else {
                            //说明用户取消了第position行
                            cartGoods.setChecked(false);
                            int num=Integer.parseInt(mList.get(position).getNum());
                            float price=Float.parseFloat(mList.get(position).getPrice());
                            total=total-num*price;
                        }
                    }
                });
                if(mList.get(position).isChecked())
                    mViewHolder.mCheckBox.setChecked(true);
                else
                    mViewHolder.mCheckBox.setChecked(false);
                break;
            case TYPE_2:
                mViewHolderImage.mImageView1.setImageResource(R.drawable.cart_test);
                mViewHolderImage.mImageView2.setImageResource(R.drawable.cart_test);
                mViewHolderImage.mImageView3.setImageResource(R.drawable.cart_test);
                mViewHolderImage.mImageView4.setImageResource(R.drawable.cart_test);
                break;
        }
        return convertView;
    }
    public float getTotal(){
        return total;
    }
}
