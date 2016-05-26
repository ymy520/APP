package cn.elife.elife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.elife.adapters.HotTitleAdapter;
import cn.elife.adapters.MsgImageAdapter;
import cn.elife.widget.ListViewForScroll;
import cn.elife.widget.NoScrollScrollView;

public class SendMessageActivity extends AppCompatActivity {

    @Bind(R.id.chat_iv_back)
    ImageView mChatIvBack;
    @Bind(R.id.chat_tv_chatwith)
    TextView mChatTvChatwith;
    @Bind(R.id.head)
    RelativeLayout mHead;
    @Bind(R.id.sendmessage_et_editmessage)
    EditText mSendmessageEtEditmessage;
    @Bind(R.id.sendmessage_rv_image)
    RecyclerView mSendmessageRvImage;
    @Bind(R.id.sendmessage_lv_hottitle)
    ListViewForScroll mSendmessageLvHottitle;
    @Bind(R.id.sendmessage_bt_send)
    Button mSendmessageBtSend;

    List<Integer> mImageUrlList;
    List<String> mHotTitleList;

    HotTitleAdapter mHotTitleAdapter;
    MsgImageAdapter mMsgImageAdapter;

    GridLayoutManager mLayoutManager;
    @Bind(R.id.send_message_scrollview)
    NoScrollScrollView mSendMessageScrollview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        ButterKnife.bind(this);

        initData();
        setListener();

        setAdapter();

    }

    private void initData() {
        mHotTitleList = new ArrayList<>();
        mHotTitleList.add("#今天是个好日子#");
        mHotTitleList.add("#画江湖之灵主#");
        mHotTitleList.add("#画江湖之不良人#");
        mHotTitleList.add("#重磅回归#");

        Log.e("SendMessageActivity", "数据量为" + mHotTitleList.size());

        mImageUrlList = new ArrayList<>();
        mImageUrlList.add(R.mipmap.ic_launcher);
        mImageUrlList.add(R.mipmap.ic_launcher);
        mImageUrlList.add(R.mipmap.ic_launcher);
        mImageUrlList.add(R.mipmap.ic_launcher);
        mImageUrlList.add(R.mipmap.ic_launcher);
        mImageUrlList.add(R.mipmap.ic_launcher);
        mImageUrlList.add(R.mipmap.ic_launcher);
        mImageUrlList.add(R.mipmap.ic_launcher);
        mImageUrlList.add(R.mipmap.ic_launcher);
        mImageUrlList.add(R.mipmap.ic_launcher);
        mImageUrlList.add(R.mipmap.ic_launcher);
        mImageUrlList.add(R.mipmap.ic_launcher);
        mImageUrlList.add(R.mipmap.ic_launcher);
        mImageUrlList.add(R.mipmap.ic_launcher);

    }

    private void setAdapter() {

        mHotTitleAdapter = new HotTitleAdapter(mHotTitleList, this);
        mSendmessageLvHottitle.setAdapter(mHotTitleAdapter);
        mSendmessageLvHottitle.setListViewHeightBasedOnChildren(mSendmessageLvHottitle);

        mLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        mSendmessageRvImage.setLayoutManager(mLayoutManager);
        mMsgImageAdapter = new MsgImageAdapter(mImageUrlList, this);
        mSendmessageRvImage.setAdapter(mMsgImageAdapter);

    }

    private void setListener() {

        mSendmessageEtEditmessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = mSendmessageEtEditmessage.getText().toString();
                if (content == null || content.equals("")) {
                    mSendmessageBtSend.setEnabled(false);
                } else {
                    mSendmessageBtSend.setEnabled(true);
                }

            }
        });

        mSendmessageLvHottitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(SendMessageActivity.this, mHotTitleList.get(position), Toast.LENGTH_SHORT).show();

//                mSendmessageEtEditmessage.setText("");
                String title = mHotTitleList.get(position).toString();
                mSendmessageEtEditmessage.setText(Html.fromHtml("<a href=\"www.baidu.com\">"+title+"</a>"));
                mSendmessageEtEditmessage.setFocusable(true);
                mSendMessageScrollview.scrollTo(0,0);

            }
        });

    }
}
