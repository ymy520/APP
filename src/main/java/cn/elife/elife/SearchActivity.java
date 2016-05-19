package cn.elife.elife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.elife.adapters.SearchActivityAdapter;

public class SearchActivity extends AppCompatActivity {

    @Bind(R.id.as_iv_back)
    ImageView mAsIvBack;
    @Bind(R.id.as_et_search)
    EditText mAsEtSearch;
    @Bind(R.id.as_btn_search)
    TextView mAsBtnSearch;
    @Bind(R.id.as_lv_history)
    ListView mAsLvHistory;
    @Bind(R.id.as_ll_voice)
    LinearLayout mAsLlVoice;
    private List<String> mHistoryList;
    private BaseAdapter mBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initData();
        initAdapter();
    }

    private void initAdapter() {
        mBaseAdapter=new SearchActivityAdapter(this,mHistoryList);
       //设置头
         View view=LinearLayout.inflate(this,R.layout.textview_activity_search,null) ;
        TextView textView= (TextView) view.findViewById(R.id.tas_tv_title);
        mAsLvHistory.addHeaderView(view);
        mAsLvHistory.setAdapter(mBaseAdapter);
    }

    //核心数据来源
    private void initData() {
        mHistoryList=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mHistoryList.add("历史记录"+(i+1));
        }

    }

    @OnClick({R.id.as_iv_back, R.id.as_et_search, R.id.as_btn_search, R.id.as_ll_voice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.as_iv_back:
                break;
            case R.id.as_et_search:
                break;
            case R.id.as_btn_search:
                break;
            case R.id.as_ll_voice:
                break;
        }
    }
}
