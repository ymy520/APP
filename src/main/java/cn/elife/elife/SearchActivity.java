package cn.elife.elife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.elife.adapters.SearchActivityAdapter;
import cn.elife.bean.Cw;
import cn.elife.bean.Sentence;
import cn.elife.bean.Ws;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    @Bind(R.id.as_iv_back)
    ImageView mAsIvBack;//返回按钮
    @Bind(R.id.as_et_search)
    EditText mAsEtSearch;//搜索框
    @Bind(R.id.as_btn_search)
    TextView mAsBtnSearch;//搜索按钮
    @Bind(R.id.as_lv_history)
    ListView mAsLvHistory;
    @Bind(R.id.as_ll_voice)
    LinearLayout mAsLlVoice;//语音按钮
    private List<String> mHistoryList;
    private BaseAdapter mBaseAdapter;
    //外部list底部的引用
    private TextView tbas_tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initData();
        initAdapter();
        setAdapterListener();//监听历史记录
    }

    //核心数据来源
    private void initData() {
        mHistoryList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mHistoryList.add("历史记录" + i);
        }

    }

    private void initAdapter() {
        mBaseAdapter = new SearchActivityAdapter(this, mHistoryList);
        //设置头
        View head = LinearLayout.inflate(this, R.layout.textview_activity_search, null);
        //设置尾部
        View bottom = LinearLayout.inflate(this, R.layout.textview_bottom_activity_search, null);
        //拿去底部控件
        tbas_tv_title = (TextView) bottom.findViewById(R.id.tbas_tv_title);
        //不是太复杂，直接在这写监听了
        tbas_tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchActivity.this, "点击了清空历史按钮", Toast.LENGTH_SHORT).show();
                clearHistory();//这里处理清空历史记录操作
            }
        });
        mAsLvHistory.addHeaderView(head);
        mAsLvHistory.addFooterView(bottom);
        mAsLvHistory.setAdapter(mBaseAdapter);
    }

    private void setAdapterListener() {
        mAsLvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) return;
                if (position > mHistoryList.size())return;
                //开始
                Toast.makeText(SearchActivity.this, "点击了" + mHistoryList.get(position - 1), Toast.LENGTH_SHORT).show();
                //进入立刻搜索逻辑
                quickSearch(mHistoryList.get(position - 1));
            }
        });
    }

    //这里处理清空历史记录操作
    private void clearHistory() {
    }

    //这里处理点击按钮之后立刻搜索操作
    private void quickSearch(String str) {
    }

    ////这里处理点击返回按钮之后的操作
    private void back() {
        Intent intent=new Intent(SearchActivity.this,MainActivity.class);
        startActivity(intent);
    }

    //进入自动搜索核心逻辑
    private void processSearch(String[] searchArr) {
    }


    @OnClick({R.id.as_iv_back, R.id.as_et_search, R.id.as_btn_search, R.id.as_ll_voice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.as_iv_back:
                Toast.makeText(SearchActivity.this, "点击了返回按钮", Toast.LENGTH_SHORT).show();
                back();//这里处理点击返回按钮之后的操作
                break;
            case R.id.as_et_search:
                break;
            case R.id.as_btn_search:
                Toast.makeText(SearchActivity.this, "点击了搜索按钮", Toast.LENGTH_SHORT).show();
                quickSearch(mAsEtSearch.getText().toString());//这里处理点击按钮之后立刻搜索操作
                break;
            case R.id.as_ll_voice:
                //这里处理语音相关逻辑
                showMSC();
                break;
        }
    }

    //这里处理语音识别
    private void showMSC() {
        final List<String> chineseWordList = new ArrayList<String>();//分词结果集
        //1.创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(this, new InitListener() {
            @Override
            public void onInit(int i) {

            }
        });
        //2.设置accent、 language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        //若要将UI控件用于语义理解，必须添加以下参数设置，设置之后onResult回调返回将是语义理解
        //结果
        // mDialog.setParameter("asr_sch", "1");
        // mDialog.setParameter("nlp_version", "2.0");
        //3.设置回调接口
        mDialog.setListener(new RecognizerDialogListener() {

            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                String Str = "";//最终形成的句子
                String str2 = "分词结果：";//要做的分词效果
                String jsonObg = recognizerResult.getResultString();
                Gson gson = new Gson();
                Sentence sentence = gson.fromJson(jsonObg, new Sentence().getClass());
                List<Ws> wsList = sentence.getWs();
                for (Ws w : wsList) {
                    List<Cw> cwList = w.getCw();
                    for (Cw c : cwList) {
                        String chineseWord = c.getW();
                        Str += chineseWord;
                        str2 += chineseWord + ",";
                    }
                }
                chineseWordList.add(Str);
                chineseWordList.add(str2);
                processMSC(chineseWordList);
            }

            @Override
            public void onError(SpeechError speechError) {

            }
        });
        //4.显示dialog，接收语音输入
        mDialog.show();

    }

    //这里搜索预处理相关逻辑
    private void processMSC(List<String> chineseWordList) {
        String showStr = chineseWordList.get(0);
        String[] searchArr = chineseWordList.get(1).split(",");
        Log.d(TAG, "分词结果：" + Arrays.toString(searchArr));
        mAsEtSearch.setText(showStr);
        //开始搜索，搜索用searchArr
        processSearch(searchArr);
    }
}
