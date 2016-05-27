package cn.elife.elife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedBackActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.left_box)
    ImageView mLeftBox;
    @Bind(R.id.feed_et_detail)
    EditText mFeedEtDetail;
    @Bind(R.id.feed_tv_save)
    TextView mFeedTvSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ButterKnife.bind(this);
        initListener();

    }

    private void initListener(){
            mFeedEtDetail.setOnClickListener(this);
            mFeedTvSave.setOnClickListener(this);
        }
    @OnClick({R.id.feed_et_detail, R.id.feed_tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.feed_et_detail:

                break;
            case R.id.feed_tv_save:
                Toast.makeText(FeedBackActivity.this, "提交", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
