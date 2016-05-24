package cn.elife.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.elife.bean.GoodsComment;
import cn.elife.elife.R;

/**
 * Created by C5-0 on 2016/5/24.
 */
public class GoodsCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEAD = 1;
    public static final int TYPE_BODY = 2;
    List<GoodsComment> mGoodsCommentList;
    Context mContext;
    LayoutInflater mInflater;

    onClickListener mOnClickListener;

    public GoodsCommentAdapter(List<GoodsComment> mGoodsCommentList, Context mContext) {
        this.mGoodsCommentList = mGoodsCommentList;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        if (viewType == TYPE_HEAD) {
            view = mInflater.inflate(R.layout.detail_goods_body_head, parent, false);
            MyHeadViewHolder headViewHolder = new MyHeadViewHolder(view,mOnClickListener);
            return headViewHolder;
        } else if (viewType == TYPE_BODY) {
            view = mInflater.inflate(R.layout.detail_goods_body_item, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        往布局上绑定数据，根据position从数据源中获取数据，，，但是头布局的数据从哪里获取？？？
        if (holder instanceof MyHeadViewHolder) {
            //这个是头布局,,,头布局的数据从哪里来呢？？？现写
            final MyHeadViewHolder headViewHolder = (MyHeadViewHolder) holder;
            headViewHolder.mTextViewGoodsName.setText("牛肉干");
            headViewHolder.mTextViewGoodsPrice.setText("￥89.0");
            headViewHolder.mTextViewGoodsCount.setText("999");

            headViewHolder.mCheckBoxLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    show("单选按钮的状态发生了改变" + isChecked);
                    if(isChecked){
                        show("我设置了喜欢");
                        headViewHolder.mCheckBoxLike.setChecked(true);
                    }else{
                        show("我设置了不喜欢");
                        headViewHolder.mCheckBoxLike.setChecked(false);
                    }
                }
            });

            headViewHolder.mTextViewMoreCommnet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show("我点击了，更多评论");
                }
            });

            headViewHolder.mImageViewShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show("我点击了，更多分享");
                }
            });



        } else if (holder instanceof MyViewHolder) {
            //这个是item布局
            GoodsComment goodsCommment = mGoodsCommentList.get(position - 1);
            MyViewHolder myViewHolder = (MyViewHolder) holder;

            myViewHolder.mImageViewHead.setImageResource(goodsCommment.getId());
            myViewHolder.mTextViewName.setText(goodsCommment.getRemark());
            myViewHolder.mTextViewContent.setText(goodsCommment.getContent());
            myViewHolder.mTextViewTime.setText(goodsCommment.getTime());
            myViewHolder.mRatingBar.setRating(3.5f);
        }

    }

    @Override
    public int getItemCount() {
//        第一个布局为头部布局，剩余的为recyclerView的数据源的布局
        return mGoodsCommentList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        //如果是第一个布局的话，则填充头布局
        if (position == 0) {
            //检测一下，头部布局是否为第一个
            return TYPE_HEAD;
        } else {
            return TYPE_BODY;
        }
    }

    public void setOnClickListener(onClickListener onClickListener){
        //这句话有什么用呢？
        this.mOnClickListener = onClickListener;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        //评论列表项中的控件
        ImageView mImageViewHead;
        TextView mTextViewName;
        TextView mTextViewContent;
        RatingBar mRatingBar;
        //会有一个显示九张图片的组件，还没有想好用什么组件
        TextView mTextViewTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageViewHead = (ImageView) itemView.findViewById(R.id.detail_goods_item_iv_headiamge);
            mTextViewName = (TextView) itemView.findViewById(R.id.detail_goods_item_tv_name);
            mTextViewContent = (TextView) itemView.findViewById(R.id.detail_goods_item_sb_commentcotent);
            mRatingBar = (RatingBar) itemView.findViewById(R.id.detail_goods_item_sb_ratingbar);
            mTextViewTime = (TextView) itemView.findViewById(R.id.detail_goods_item_tv_commenttime);
        }
    }

    public class MyHeadViewHolder extends RecyclerView.ViewHolder implements onClickListener {
        //        头部布局中的所有控件
        ViewPager mViewPager;
        TextView mTextViewGoodsName;
        TextView mTextViewGoodsPrice;
        TextView mTextViewGoodsCount;
        CheckBox mCheckBoxLike;
        ImageView mImageViewShare;

        TextView mTextViewMoreCommnet;

        private onClickListener mOnClickListener;

        public MyHeadViewHolder(View itemView,onClickListener onListener) {
            super(itemView);
            mViewPager = (ViewPager) itemView.findViewById(R.id.detail_goods_head_vp_showimage);
            mTextViewGoodsName = (TextView) itemView.findViewById(R.id.detail_goods_head_tv_goodsname);
            mTextViewGoodsPrice = (TextView) itemView.findViewById(R.id.detail_goods_head_tv_goodsprice);
            mTextViewGoodsCount = (TextView) itemView.findViewById(R.id.detail_goods_head_tv_count);
            mCheckBoxLike = (CheckBox) itemView.findViewById(R.id.detail_goods_head_cb_like);
            mImageViewShare = (ImageView) itemView.findViewById(R.id.detail_goods_head_iv_share);
            mTextViewMoreCommnet = (TextView) itemView.findViewById(R.id.detail_goods_head_tv_morecomment);

            this.mOnClickListener = onListener;

            Drawable home[] = mTextViewMoreCommnet.getCompoundDrawables();
            home[2].setBounds(0, 0, 36, 36);
            mTextViewMoreCommnet.setCompoundDrawables(null, null, home[2], null);

            /*//给控件添加监听事件
            mRadioButtonLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    show("单选按钮的状态发生了改变");
                    if(isChecked){
                        show("我设置了喜欢");
                        mRadioButtonLike.setChecked(true);
                    }else{
                        show("我设置了不喜欢");
                        mRadioButtonLike.setChecked(false);
                    }

                }
            });*/



        }

        @Override
        public void onClick(View view, int position) {

            if(mOnClickListener != null){
                mOnClickListener.onClick(view,getLayoutPosition());

            }

            switch (view.getId()) {
                case R.id.detail_goods_head_cb_like:
                    show("我点击了我喜欢");
                    break;
                case R.id.detail_goods_head_iv_share:
                    show("我点击了分享");
                    break;
                case R.id.detail_goods_head_tv_morecomment:
                    show("我点击了更多评论");
                    break;
            }
        }
    }

    private void show(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    public interface onClickListener {
        //这两个数据是从哪里来的
        public void onClick(View view, int position);

    }


}
