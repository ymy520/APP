package cn.elife.elife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.elife.adapters.ChatMessageAdapter;

public class ChatActivity extends AppCompatActivity {

    @Bind(R.id.chat_iv_back)
    ImageView mChatIvBack;
    @Bind(R.id.chat_tv_chatwith)
    TextView mChatTvChatwith;
    @Bind(R.id.chat_rv_messsagelist)
    RecyclerView mChatRvMesssagelist;
    @Bind(R.id.chat_et_messsagecontent)
    EditText mChatEtMesssagecontent;
    @Bind(R.id.chat_bt_messagesend)
    Button mChatBtMessagesend;

    List<ChatMessage> mChatMessagesList;
    LinearLayoutManager mLayoutManager;
    ChatMessageAdapter mMessageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        /**
         * 商家和客户聊天界面
         */
        initViews();

        initData();

        setAdapter();
        
        setListener();


    }

    private void initViews() {
//        mChatBtMessagesend.setClickable(false);
    }

    private void setListener() {

        mChatEtMesssagecontent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

//                show("我是beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                show("我是onTextChanged");

            }

            @Override
            public void afterTextChanged(Editable s) {
//                show("我是afterTextChanged");
                String content = mChatEtMesssagecontent.getText().toString();
                if(content == null || content.equals("")){
                    //输入内容为空，发送按钮不可点击
                    mChatBtMessagesend.setEnabled(false);
                }else{
                    //输入内容不为空，发送按钮可点击
                    mChatBtMessagesend.setEnabled(true);
                }

            }
        });
    }

    private void setAdapter() {
        //构造方法重点额几个参数不知什么意思，，，可能会出问题
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mChatRvMesssagelist.setLayoutManager(mLayoutManager);

        mMessageAdapter = new ChatMessageAdapter(mChatMessagesList,this);
        mChatRvMesssagelist.setAdapter(mMessageAdapter);


    }


    private void initData() {

        mChatMessagesList = new ArrayList<>();
        mChatMessagesList.add(new ChatMessage(null,"老板在吗",R.mipmap.ic_launcher,0));
        mChatMessagesList.add(new ChatMessage(null,"在啊",R.mipmap.ic_launcher,1));
        mChatMessagesList.add(new ChatMessage(null,"你们那个衣服有那个号的吗",R.mipmap.ic_launcher,0));
        mChatMessagesList.add(new ChatMessage(null,"什么号啊？",R.mipmap.ic_launcher,1));
        mChatMessagesList.add(new ChatMessage(null,"就是那个号啊",R.mipmap.ic_launcher,0));
        mChatMessagesList.add(new ChatMessage(null,"在啊",R.mipmap.ic_launcher,1));
        mChatMessagesList.add(new ChatMessage(null,"你们那个衣服有那个号的吗",R.mipmap.ic_launcher,0));
        mChatMessagesList.add(new ChatMessage(null,"什么号啊？",R.mipmap.ic_launcher,1));
        mChatMessagesList.add(new ChatMessage(null,"就是那个号啊",R.mipmap.ic_launcher,0));
        mChatMessagesList.add(new ChatMessage(null,"在啊",R.mipmap.ic_launcher,1));
        mChatMessagesList.add(new ChatMessage(null,"你们那个衣服有那个号的吗",R.mipmap.ic_launcher,0));
        mChatMessagesList.add(new ChatMessage(null,"什么号啊？",R.mipmap.ic_launcher,1));
        mChatMessagesList.add(new ChatMessage(null,"就是那个号啊",R.mipmap.ic_launcher,0));
    }

    @OnClick({R.id.chat_iv_back, R.id.chat_bt_messagesend})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chat_iv_back:
                show("我点击了返回键");
                break;
            case R.id.chat_bt_messagesend:
                show("我点击了发送键");
                break;
        }
    }

    private void show(String text) {
        Toast.makeText(ChatActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    public class ChatMessage {
        String url;
        String message;
        int  imageid;
        int flag;

        public ChatMessage(String url, String message, int  imageid,int flag) {
            this.url = url;
            this.message = message;
            this.imageid = imageid;
            this.flag = flag;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getImageid() {
            return imageid;
        }

        public void setImageid(int imageid) {
            this.imageid = imageid;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }
    }
}
