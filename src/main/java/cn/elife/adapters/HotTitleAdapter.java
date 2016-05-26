package cn.elife.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.elife.elife.R;

/**
 * Created by C5-0 on 2016/5/26.
 */
public class HotTitleAdapter extends BaseAdapter {

    List<String> mHotTitleList;
    Context mContext;
    LayoutInflater mInflater;

    public HotTitleAdapter(List<String> hotTitleList, Context context) {
        mHotTitleList = hotTitleList;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
//        Log.e("HotTitleAdapter","热门话题的数量" + mHotTitleList.size());
        return mHotTitleList.size();
    }

    @Override
    public Object getItem(int position) {
        Log.e("HotTitleAdapter",mHotTitleList.get(position) + "这是第" + position + "个数据");
        return mHotTitleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.send_message_hot_title_adapter,null);
            holder.mTextView = (TextView) convertView.findViewById(R.id.hottitle_tv_content);

            convertView.setTag(holder);
            Log.e("HotTitleAdapter布局为空",mHotTitleList.get(position));
        }else{
            holder = (ViewHolder) convertView.getTag();
            Log.e("HotTitleAdapter布局不为空",mHotTitleList.get(position));
        }

        Log.e("HotTitleAdapter这是第",position + "个数据");

        holder.mTextView.setText(mHotTitleList.get(position));
//        holder.mTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "我点了" + mHotTitleList.get(position), Toast.LENGTH_SHORT).show();
//            }
//        });
        return convertView;
    }

    class ViewHolder{
        TextView mTextView;
    }
}
