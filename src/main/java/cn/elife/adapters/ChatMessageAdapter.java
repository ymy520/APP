package cn.elife.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.elife.elife.ChatActivity;
import cn.elife.elife.R;

/**
 * Created by C5-0 on 2016/5/26.
 */
public class ChatMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ME = 0;
    public static final int HE = 1;
    List<ChatActivity.ChatMessage> mMessagesList;
    Context mContext;
    LayoutInflater mInflater;

    public ChatMessageAdapter(List<ChatActivity.ChatMessage> messagesList, Context context) {
        mMessagesList = messagesList;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        if(viewType == ME){
            view = mInflater.inflate(R.layout.chat_me,parent,false);
            return new MyItemViewHolder(view,viewType);
        }else if(viewType == HE){
            view = mInflater.inflate(R.layout.chat_he,parent,false);
            return new MyItemViewHolder(view,viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ChatActivity.ChatMessage message = mMessagesList.get(position);

        ((MyItemViewHolder) holder).mTextViewMessage.setText(mMessagesList.get(position).getMessage());
        ((MyItemViewHolder) holder).mImageViewHead.setImageResource(mMessagesList.get(position).getImageid());

    }

    @Override
    public int getItemCount() {
        return mMessagesList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mMessagesList.get(position).getFlag();
    }

    class MyItemViewHolder extends RecyclerView.ViewHolder{

        TextView mTextViewMessage;
        ImageView mImageViewHead;

        public MyItemViewHolder(View itemView,int viewType) {
            super(itemView);
            if(viewType == ME){
                mTextViewMessage = (TextView) itemView.findViewById(R.id.chatme_tv_content);
                mImageViewHead = (ImageView) itemView.findViewById(R.id.chatme_iv_headimg);
            }else if(viewType == HE){
                mTextViewMessage = (TextView) itemView.findViewById(R.id.chathe_tv_content);
                mImageViewHead = (ImageView) itemView.findViewById(R.id.chathe_iv_headimg);
            }
        }
    }
}
