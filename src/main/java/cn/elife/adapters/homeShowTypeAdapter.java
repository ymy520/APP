package cn.elife.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.elife.elife.R;
import cn.elife.fragments.HomeFragment;

/**
 * Created by Bill on 2016/5/8.
 */
public class homeShowTypeAdapter extends BaseAdapter {
    List<HomeFragment.Type> mlist;
    Context mContext;
    LayoutInflater mInflater;

    public homeShowTypeAdapter(Context context, List<HomeFragment.Type> typeList) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mlist = typeList;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.home_type,null);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.home_type_image);
        TextView textView = (TextView) convertView.findViewById(R.id.home_type_title);

        HomeFragment.Type type = mlist.get(position);
        imageView.setImageResource(type.getImage());
        textView.setText(type.getTitle());

        return convertView;
    }


}
