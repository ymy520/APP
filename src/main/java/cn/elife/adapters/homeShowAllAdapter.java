package cn.elife.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import cn.elife.bean.Goods;
import cn.elife.elife.R;
import cn.elife.widget.ShowGoodsView;

/**
 * Created by Bill on 2016/5/7.
 */
public class homeShowAllAdapter extends RecyclerView.Adapter<homeShowAllAdapter.MyViewHolder> {
    List<Goods> mGoodsList;
    Context mContext;
    LayoutInflater mInflater;
    MyItemClickListener mOnClickListener;


    public homeShowAllAdapter(List<Goods> goodsList, Context context) {
        mGoodsList = goodsList;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建布局缓存器
        View v = mInflater.inflate(R.layout.homegoodsshow,parent,false);
        MyViewHolder holder = new MyViewHolder(v,mOnClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        设置布局布局内容
        Goods goods = mGoodsList.get(position);
        holder.mShowGoodsView.setTv_name(goods.getName());
        holder.mShowGoodsView.setTv_price(goods.getPrice()+"");
        holder.mShowGoodsView.setTv_sale(goods.getSale()+"");
        holder.mShowGoodsView.setIv_image(goods.getImgaddress());
    }

    @Override
    public int getItemCount() {
        //返回资源个数
        return mGoodsList.size();
    }

    public void setOnItemClickListener(MyItemClickListener mListener){
        //给适配器定义布局监听事件，，，相当于这里是初始化
        this.mOnClickListener = mListener;

        Toast.makeText(mContext, "我在这里能不能监听到点击事件呢", Toast.LENGTH_SHORT).show();

    }




    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ShowGoodsView mShowGoodsView;
        private MyItemClickListener mListener;

        public MyViewHolder(View itemView,MyItemClickListener listener) {
            super(itemView);
            mShowGoodsView = (ShowGoodsView) itemView.findViewById(R.id.test_define_goods);
            mListener = listener;
            mShowGoodsView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(v,getLayoutPosition());

//                Toast.makeText(mShowGoodsView.getContext(), "我是设置在适配器中的点击方法", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public interface MyItemClickListener {
        public void onItemClick(View view,int position);
    }

}
