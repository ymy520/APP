package cn.elife.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.elife.bean.Merchant;
import cn.elife.elife.R;

/**
 * Created by wgyscsf on 2016/5/14.完成商家页面listview
 */
public class MerchantFragmentAdapter extends BaseAdapter {
    private List<Merchant> mMerchantList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public MerchantFragmentAdapter(Context context, List<Merchant> merchantList) {
        this.mContext = context;
        this.mMerchantList = merchantList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mMerchantList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMerchantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=  mLayoutInflater.inflate(R.layout.item_fragment_mechant, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //开始填充数据
        Merchant merchant=mMerchantList.get(position);
        viewHolder.mIfmIvImg.setImageResource(Integer.parseInt(merchant.getUrl()));
        viewHolder.mIfmTvTitle.setText(merchant.getTitle());
        viewHolder.mIfmRbScore.setRating(merchant.getRateBar());
        viewHolder.mIfmTvBegin.setText(merchant.getBegin());
        viewHolder.mIfmTvNums.setText(merchant.getNums()+"");
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.ifm_iv_img)
        ImageView mIfmIvImg;
        @Bind(R.id.ifm_tv_title)
        TextView mIfmTvTitle;
        @Bind(R.id.ifm_rb_score)
        RatingBar mIfmRbScore;
        @Bind(R.id.ifm_tv_begin)
        TextView mIfmTvBegin;
        @Bind(R.id.ifm_tv_nums)
        TextView mIfmTvNums;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
