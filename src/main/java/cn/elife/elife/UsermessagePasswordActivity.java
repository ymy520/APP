package cn.elife.elife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UsermessagePasswordActivity extends AppCompatActivity {

    private EditText oldText;
    private EditText newText;
    private ImageView backImage;
    private Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermessage_password);
        initViews();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass=oldText.getText().toString();
                String newPass=newText.getText().toString();
                Toast.makeText(UsermessagePasswordActivity.this, oldPass+"-->"+newPass, Toast.LENGTH_SHORT).show();
            }
        });
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViews() {
        backImage= (ImageView) findViewById(R.id.usermessage_passback);
        oldText= (EditText) findViewById(R.id.usemessage_oldpassword);
        newText= (EditText) findViewById(R.id.usemessage_newpassword);
        saveButton= (Button) findViewById(R.id.usermessage_passsave);
    }
}
