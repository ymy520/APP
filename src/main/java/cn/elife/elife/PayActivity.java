package cn.elife.elife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cn.elife.utils.PayRadioGroup;
import cn.elife.utils.PayRadioPurified;

public class PayActivity extends AppCompatActivity {
    PayRadioGroup payGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        payGroup= (PayRadioGroup) findViewById(R.id.pay_radiogroup);
        payGroup.setOnCheckedChangeListener(new PayRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(PayRadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
//              PayRadioButton rb = (PayRadioButton)MainActivity.this.findViewById(radioButtonId);
//              Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();

                PayRadioPurified rl = (PayRadioPurified)PayActivity.this.findViewById(radioButtonId);
                for (int i = 0; i < group.getChildCount(); i++) {
                    ((PayRadioPurified)group.getChildAt(i)).setChangeImg(checkedId);
                }
                Toast.makeText(PayActivity.this, rl.getTextTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

