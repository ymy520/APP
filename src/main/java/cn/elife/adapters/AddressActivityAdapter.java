package cn.elife.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.elife.bean.Address;
import cn.elife.elife.R;

/**
 * Created by wgyscsf on 2016/5/17.
 */
public class AddressActivityAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<Address> mAddressList;
    private Context mContext;

    public AddressActivityAdapter(Context context, List<Address> mAddressList) {
        this.mContext = context;
        this.mAddressList = mAddressList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mAddressList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAddressList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    ViewHolder viewHolder = null;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
             convertView = mLayoutInflater.inflate(R.layout.item_activity_address, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        /*
        开始处理chickbox错乱问题
         */
        viewHolder.mIaaCbDefalut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//               //解决只能选择一个默认地址
//                for (int i = 0; i < mAddressList.size(); i++) {
//                    mAddressList.get(i).setDefaultAddresss(false);
//                }
                //解决错乱问题
                if (viewHolder.mIaaCbDefalut.isChecked()) {
                    mAddressList.get(position).setDefaultAddresss(true);
                } else {
                    mAddressList.get(position).setDefaultAddresss(false);
                }
                //开始解决只能选择一个默认地址
//                Toast.makeText(mContext, position+"---"+mAddressList.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
        //开始设置参数
        Address address = mAddressList.get(position);
        viewHolder.mIaaTvName.setText(address.getName());
        viewHolder.mIaaTvPhone.setText(address.getPhone());
        viewHolder.mIaaTvAddress.setText(address.getAddress());
        viewHolder.mIaaCbDefalut.setChecked(address.isDefaultAddresss());
        notifyDataSetChanged();
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iaa_tv_name)
        TextView mIaaTvName;
        @Bind(R.id.iaa_tv_phone)
        TextView mIaaTvPhone;
        @Bind(R.id.iaa_tv_address)
        TextView mIaaTvAddress;
        @Bind(R.id.iaa_cb_defalut)
        CheckBox mIaaCbDefalut;
        @Bind(R.id.iaa_tv_del)
        TextView mIaaTvDel;
        @Bind(R.id.ifms_tv_line)
        TextView mIfmsTvLine;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
