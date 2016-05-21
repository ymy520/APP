package cn.elife.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.OnClick;
import cn.elife.bean.SnacksListData;
import cn.elife.elife.R;
import cn.elife.interfaces.OnRecyclerViewItemClickListener;

/**
 * Created by 叶梦雅 on 2016/5/18.
 */
public class MySnacksListAdapter extends RecyclerView.Adapter<MySnacksListAdapter.MyViewHolder> implements View.OnClickListener{
    List<SnacksListData> mList;
    Context mContext;
    LayoutInflater mInflater;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;


    public MySnacksListAdapter(List<SnacksListData> list, Context context) {
        mList = list;
        mContext = context;
        mInflater=LayoutInflater.from(mContext);
    }

    //绑定布局
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.snacksitem, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener((View.OnClickListener) this);
        return holder;
    }
    //设置布局的内容
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mImageView.setImageResource(mList.get(position).getImgaddress());

        holder.mdetalTextView.setText(mList.get(position).getName());
        holder.mpriceTextView.setText(mList.get(position).getPrice()+"");
        holder.mcountTextView.setText(mList.get(position).getSale()+"");

        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(mList.get(position));
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener onClickListener) {
        this.mOnItemClickListener = onClickListener;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(SnacksListData)v.getTag());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mdetalTextView,mpriceTextView,mcountTextView;
        private OnRecyclerViewItemClickListener  mOnItemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            //初始化，绑定布局
            mImageView = (ImageView) itemView.findViewById(R.id.snackslist);
            mdetalTextView= (TextView) itemView.findViewById(R.id.snackdetal);
            mpriceTextView= (TextView) itemView.findViewById(R.id.priceTextView);
            mcountTextView= (TextView) itemView.findViewById(R.id.countTextView);
        }


    }

}
