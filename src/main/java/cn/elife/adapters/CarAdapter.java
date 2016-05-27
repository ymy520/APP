package cn.elife.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.elife.bean.CarMerchant;
import cn.elife.elife.R;

/**
 * Author：张凯  on 2016/5/25 15:25
 * Blog: bukevin@github.io
 */
public class CarAdapter extends BaseAdapter {
    private Context mContext;
    private List<CarMerchant> mList;
    private LayoutInflater mInflater;

    public CarAdapter(Context mContext, List<CarMerchant> mList) {
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
        private ImageView carimage;
        private TextView nameTV;
        private TextView addrTV;
        private TextView evTV;
        private TextView orderTV;
        private TextView distanceTV;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.car_item,null);
            viewHolder=new ViewHolder();
            viewHolder.carimage= (ImageView) convertView.findViewById(R.id.car_image);
            viewHolder.nameTV= (TextView) convertView.findViewById(R.id.car_name);
            viewHolder.addrTV= (TextView) convertView.findViewById(R.id.car_addr);
            viewHolder.evTV= (TextView) convertView.findViewById(R.id.car_evnum);
            viewHolder.orderTV= (TextView) convertView.findViewById(R.id.car_ordernum);
            viewHolder.distanceTV= (TextView) convertView.findViewById(R.id.car_locationnum);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.carimage.setImageResource(mList.get(position).getImageId());
        viewHolder.nameTV.setText(mList.get(position).getMerchantName());
        viewHolder.addrTV.setText(mList.get(position).getMerchantAddr());
        viewHolder.evTV.setText(""+mList.get(position).getEvNum());
        viewHolder.orderTV.setText(""+mList.get(position).getOrderNum());
        viewHolder.distanceTV.setText(""+mList.get(position).getDistanceNum());
        return convertView;
    }
}
