package cn.elife.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.elife.bean.DetailsEv;
import cn.elife.bean.DetailsHead1;
import cn.elife.bean.DetailsHead2;
import cn.elife.bean.MerchantDetails;
import cn.elife.elife.R;

/**
 * Author：张凯  on 2016/5/23 19:23
 * Blog: bukevin@github.io
 */
public class DetailsAdapter extends BaseAdapter{

    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final String TAG = "DetailsAdapter";
    private LayoutInflater mInflater;
    private List<Object> mList;
    private Context mContext;

    public DetailsAdapter(List<Object> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
        mInflater =LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    /**
     * 决定加载哪个类型的布局
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if(mList.get(position) instanceof DetailsHead1){
            return ZERO;
        }else if(mList.get(position) instanceof DetailsHead2){
            return ONE;
        }else if(mList.get(position) instanceof MerchantDetails){
            return TWO;
        }else if(mList.get(position) instanceof DetailsEv){
            return THREE;
        }else{
            return super.getItemViewType(position);
        }
    }

    /**
     * 返回布局的个数
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder0{
        private ImageView headImage;
    }
    class ViewHolder1{
        private TextView merchantName;
        private TextView merchantadd;
        private TextView merchantcontent;
    }
    class ViewHolder2{
        private TextView num;
    }
    class ViewHolder3{
        private TextView username;
        private TextView evcontent;
        private TextView evtime;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取要加载布局的类型
        int type=getItemViewType(position);
        ViewHolder0 viewHolder0=null;
        ViewHolder1 viewHolder1=null;
        ViewHolder2 viewHolder2=null;
        ViewHolder3 viewHolder3=null;
        if(convertView==null){
            switch (type){
                case ZERO:
                    convertView=mInflater.inflate(R.layout.detailes_head1,null);
                    viewHolder0=new ViewHolder0();
                    viewHolder0.headImage= (ImageView) convertView.findViewById(R.id.details_image);
                    convertView.setTag(viewHolder0);
                    break;
                case ONE:
                    convertView=mInflater.inflate(R.layout.details_head2,null);
                    viewHolder1=new ViewHolder1();
                    viewHolder1.merchantName= (TextView) convertView.findViewById(R.id.details_merchname);
                    viewHolder1.merchantadd= (TextView) convertView.findViewById(R.id.details_merchaddress);
                    viewHolder1.merchantcontent= (TextView) convertView.findViewById(R.id.details_merchdesc);
                    convertView.setTag(viewHolder1);
                    break;
                case TWO:
                    convertView=mInflater.inflate(R.layout.details_ev,null);
                    viewHolder2=new ViewHolder2();
                    viewHolder2.num= (TextView) convertView.findViewById(R.id.evnum);
                    convertView.setTag(viewHolder2);
                    break;
                case THREE:
                    convertView=mInflater.inflate(R.layout.details_item,null);
                    viewHolder3=new ViewHolder3();
                    viewHolder3.username= (TextView) convertView.findViewById(R.id.detail_username);
                    viewHolder3.evcontent= (TextView) convertView.findViewById(R.id.detail_content);
                    viewHolder3.evtime= (TextView) convertView.findViewById(R.id.detail_time);
                    convertView.setTag(viewHolder3);
                    break;
            }
        }else{
            switch (type){
                case ZERO:
                    viewHolder0= (ViewHolder0) convertView.getTag();
                    break;
                case ONE:
                    viewHolder1= (ViewHolder1) convertView.getTag();
                    break;
                case TWO:
                    viewHolder2= (ViewHolder2) convertView.getTag();
                    break;
                case THREE:
                    viewHolder3= (ViewHolder3) convertView.getTag();
                    break;
            }
        }
        Object obj=mList.get(position);
        //根据返回类型来展示不同的数据
        switch (type){
            case ZERO:
                DetailsHead1 detailsHead1= (DetailsHead1) obj;
                if(detailsHead1!=null){
                    viewHolder0.headImage.setImageResource(detailsHead1.getIamgeid());
                }
                break;
            case ONE:
                DetailsHead2 detailsHead2= (DetailsHead2) obj;
                if(detailsHead2!=null){
                    viewHolder1.merchantName.setText(detailsHead2.getMerchantName());
                    viewHolder1.merchantadd.setText(detailsHead2.getMerchantaddr());
                    viewHolder1.merchantcontent.setText(detailsHead2.getMerchantCont());
                }
                break;
            case TWO:
                MerchantDetails merchantDetails= (MerchantDetails) obj;
                if(merchantDetails!=null){
                    viewHolder2.num.setText(merchantDetails.getName());
                }
                break;
            case THREE:
                DetailsEv detailsEv= (DetailsEv) obj;
                if(detailsEv!=null){
                    viewHolder3.username.setText(detailsEv.getUserName());
                    viewHolder3.evcontent.setText(detailsEv.getEvContent());
                    viewHolder3.evtime.setText(detailsEv.getEvTime());
                }
                break;
        }
        return convertView;
    }
}
