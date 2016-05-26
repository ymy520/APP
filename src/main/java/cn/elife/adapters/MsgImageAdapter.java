package cn.elife.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import cn.elife.elife.R;

/**
 * Created by C5-0 on 2016/5/26.
 */
public class MsgImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Integer> mImageURLList;
    Context mContext;
    LayoutInflater mInflater;

    public MsgImageAdapter(List<Integer> imageURLList, Context context) {
        mImageURLList = imageURLList;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.send_message_msgimage_adapter,parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((MyViewHolder) holder).mImageView.setImageResource(mImageURLList.get(position));

    }

    @Override
    public int getItemCount() {
        return mImageURLList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.msgimage_iv_image);

        }
    }
}
