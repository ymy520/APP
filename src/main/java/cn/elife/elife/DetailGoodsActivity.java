package cn.elife.elife;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_goods);
        ButterKnife.bind(this);

        initData();

        setAdapter();

        setListener();

    }

    private void setListener() {

        mGoodsCommentAdapter.setOnClickListener(new GoodsCommentAdapter.onClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(position == 0){
                    show("我点击了头布局");
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


    @OnClick({R.id.detail_goods_ib_back, R.id.detail_goods_iv_talk})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detail_goods_ib_back:
                show("我点击了返回上一页");
                break;
            case R.id.detail_goods_iv_talk:
                show("我点击了消息");
                break;
        }
    }



    private void show(String text) {
        Toast.makeText(DetailGoodsActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
