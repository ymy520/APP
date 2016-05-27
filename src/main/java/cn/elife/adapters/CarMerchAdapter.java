package cn.elife.adapters;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.elife.bean.CarEVContent;
import cn.elife.bean.CarEVNum;
import cn.elife.bean.CarMerchantHead;
import cn.elife.elife.R;
import cn.elife.utils.CarDetailViewPager;

/**
 * Author：张凯  on 2016/5/26 11:04
 * Blog: bukevin@github.io
 */
public class CarMerchAdapter extends BaseAdapter {
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    private Context mContext;
    private List<Object> mList;
    private LayoutInflater mInflater;
    private CarDetailViewPager mViewPager;

    public CarMerchAdapter(Context mContext, List<Object> mList) {
        this.mContext = mContext;
        this.mList = mList;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object obj=mList.get(position);
        if(obj instanceof CarMerchantHead){
            return ZERO;
        }else if(obj instanceof CarEVNum){
            return ONE;
        }else if(obj instanceof CarEVContent){
            return TWO;
        }else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public int getViewTypeCount() {
        return 4;
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
        private TextView userNameTV;
        private TextView EVTimeTV;
        private TextView EVContentTV;
        private TextView typeTV;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        int type = getItemViewType(position);
        if (convertView == null){
            switch (type) {
                case TWO:
                    convertView=mInflater.inflate(R.layout.car_ev_item,null);
                    viewHolder=new ViewHolder();
                    viewHolder.userNameTV= (TextView) convertView.findViewById(R.id.car_ev_user);
                    viewHolder.EVTimeTV= (TextView) convertView.findViewById(R.id.car_ev_time);
                    viewHolder.EVContentTV= (TextView) convertView.findViewById(R.id.car_ev_content);
                    viewHolder.typeTV= (TextView) convertView.findViewById(R.id.car_server);
                    convertView.setTag(viewHolder);
                    break;
            }
        }
        else {
            switch (type) {
                case TWO:
                    viewHolder= (ViewHolder) convertView.getTag();
                    break;
            }
        }
        Object object=mList.get(position);
        switch (type) {
            case ZERO:
                CarMerchantHead merchantHead= (CarMerchantHead) object;
                convertView=mInflater.inflate(R.layout.car_banner,null);
                List<Integer> resId=new ArrayList<>();
                resId.add(merchantHead.getImageid1());
                resId.add(merchantHead.getImageid2());
                resId.add(merchantHead.getImageid3());
                CarBannerAdapter carBannerAdapter=new CarBannerAdapter(mContext);
                carBannerAdapter.update(resId);
                mViewPager= (CarDetailViewPager) convertView.findViewById(R.id.car_view_pager);
                mViewPager.setAdapter(carBannerAdapter);
                mViewPager.start();
                TextView addr= (TextView) convertView.findViewById(R.id.car_merchantaddr);
                addr.setText(merchantHead.getAddress());
                break;
            case ONE:
                CarEVNum carEVNum= (CarEVNum) object;
                convertView=mInflater.inflate(R.layout.car_ev_head,null);
                TextView evNum= (TextView) convertView.findViewById(R.id.car_ev_num);
                evNum.setText(""+carEVNum.getCarEVNum());
                break;
            case TWO:
                CarEVContent carEVContent= (CarEVContent) object;
                viewHolder.userNameTV.setText(carEVContent.getCarEVUsername());
                viewHolder.EVTimeTV.setText(carEVContent.getCarEVTime());
                viewHolder.EVContentTV.setText(carEVContent.getCarEVContent());
                if(carEVContent.getCarServerCode()==1) {
                    viewHolder.typeTV.setText("内外洗");
                }else if(carEVContent.getCarServerCode()==2) {
                viewHolder.typeTV.setText("外洗");
                }else if(carEVContent.getCarServerCode()==3) {
                    viewHolder.typeTV.setText("外洗+打蜡");
                }
                break;
        }
        return convertView;
    }
}
