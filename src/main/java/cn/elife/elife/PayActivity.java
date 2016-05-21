package cn.elife.elife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import cn.elife.fragments.CartFragment;
import cn.elife.utils.PayRadioGroup;
import cn.elife.utils.PayRadioPurified;

public class PayActivity extends AppCompatActivity {
    PayRadioGroup payGroup;
    ImageButton backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        initViews();
        //获取用户选择的支付方式
        payGroup.setOnCheckedChangeListener(new PayRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(PayRadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                PayRadioPurified rl = (PayRadioPurified)PayActivity.this.findViewById(radioButtonId);
                for (int i = 0; i < group.getChildCount(); i++) {
                    ((PayRadioPurified)group.getChildAt(i)).setChangeImg(checkedId);
                }
                Toast.makeText(PayActivity.this, rl.getTextTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViews() {
        payGroup= (PayRadioGroup) findViewById(R.id.pay_radiogroup);
        backButton= (ImageButton) findViewById(R.id.pay_backbutton);
    }
}
