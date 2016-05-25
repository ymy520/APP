package cn.elife.elife;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.elife.adapters.GoodsCommentAdapter;
import cn.elife.bean.GoodsComment;

public class DetailGoodsActivity extends AppCompatActivity {


    @Bind(R.id.detail_goods_ib_back)
    ImageView mDetailGoodsIvBack;
    @Bind(R.id.detail_goods_tv_title)
    TextView mDetailGoodsTvTitle;
    @Bind(R.id.detail_goods_iv_talk)
    ImageView mDetailGoodsIvTalk;
    @Bind(R.id.detail_goods_rv_content)
    RecyclerView mDetailGoodsRvContent;
    @Bind(R.id.detail_goods_srl_allmessage)
    SwipeRefreshLayout mDetailGoodsSrlAllmessage;

    List<GoodsComment> mCommentList;//封装评论的数据集合
    GoodsCommentAdapter mGoodsCommentAdapter;//评论列表的适配器
    @Bind(R.id.detail_goods_bottom_addcart)
    TextView mDetailGoodsBottomAddcart;
    @Bind(R.id.detail_goods_bottom_buynow)
    TextView mDetailGoodsBottomBuynow;

    PopupWindow mPopupWindow;

    TextView mTextViewConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_goods);
        ButterKnife.bind(this);

        initViews();

        initData();

        setAdapter();

        setListener();

    }

    private void initViews() {
        View popupView = getLayoutInflater().inflate(R.layout.popupwindow_addcart, null);

        mPopupWindow = new PopupWindow(popupView, LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
//        mPopupWindow.setAnimationStyle(R.style.detail_goods_pop_anim);

        mTextViewConfirm = (TextView) popupView.findViewById(R.id.detail_goods_confirm);

    }

    private void setListener() {
        mTextViewConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show("我点击了确定加入购物车的按钮");
            }
        });

    }

    private void setAdapter() {
        //初始化，适配器
        mGoodsCommentAdapter = new GoodsCommentAdapter(mCommentList, this);
        mDetailGoodsRvContent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //绑定适配器
        mDetailGoodsRvContent.setAdapter(mGoodsCommentAdapter);
    }

    private void initData() {
        //有一些需要参照的信息，不在这个类中，就先做这么多出来
        mCommentList = new ArrayList<GoodsComment>();
        int count = 5;
        while (count > 0) {
            GoodsComment comment = new GoodsComment();
            comment.setContent("hahahaha");
//            comment.setIdentity("我是商家");
            comment.setTime("2016/05/24 9:50");
            comment.setId(R.mipmap.ic_launcher);//暂时充当头像
            comment.setRemark("王高原");//暂时充当姓名
            mCommentList.add(comment);
            count--;
        }

    }


    @OnClick({R.id.detail_goods_ib_back, R.id.detail_goods_iv_talk, R.id.detail_goods_bottom_addcart, R.id.detail_goods_bottom_buynow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detail_goods_ib_back:
                show("我点击了返回上一页");
                break;
            case R.id.detail_goods_iv_talk:
                show("我点击了消息");
                break;
            case R.id.detail_goods_bottom_addcart:
                show("我点击了加入购物车");
                int[] location = new int[2];
                view.getLocationOnScreen(location);
                mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1] - mPopupWindow.getHeight());
                break;
            case R.id.detail_goods_bottom_buynow:
                show("我点击了加入立即购买");

                break;
        }
    }

    private void show(String text) {
        Toast.makeText(DetailGoodsActivity.this, text, Toast.LENGTH_SHORT).show();
    }

}
