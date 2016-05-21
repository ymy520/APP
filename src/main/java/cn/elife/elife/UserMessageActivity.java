package cn.elife.elife;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class UserMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private View headView;
    private View nameView;
    private View sexView;
    private View passwordView;
    private View emailView;
    private View codeView;
    private ImageView myHeadImage;
    AlertDialog.Builder dialogBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);
        initViews();
        headView.setOnClickListener(this);
        nameView.setOnClickListener(this);
        sexView.setOnClickListener(this);
        passwordView.setOnClickListener(this);
        emailView.setOnClickListener(this);
        codeView.setOnClickListener(this);
    }

    private void initViews() {
        headView=findViewById(R.id.user_myhead);
        nameView=findViewById(R.id.user_myname);
        sexView=findViewById(R.id.user_mysex);
        passwordView=findViewById(R.id.user_mypassword);
        emailView=findViewById(R.id.user_myemail);
        codeView=findViewById(R.id.user_mycode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_myhead:
                modifyMyHead();
                break;
            case R.id.user_myname:
                modifyMyName();
                break;
            case R.id.user_mysex:
                modifySex();
                break;
            case R.id.user_mypassword:

                break;
            case R.id.user_myemail:

                break;
            case R.id.user_mycode:

                break;
        }
    }

    private void modifySex() {
        dialogBuilder=new AlertDialog.Builder(this);
        dialogBuilder.setTitle("修改性别");
        final String[] items={"男","女"};
        dialogBuilder.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(UserMessageActivity.this, "你选择了："+items, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void modifyMyName() {

    }

    private void modifyMyHead() {
        dialogBuilder=new AlertDialog.Builder(this);
        dialogBuilder.setTitle("更改头像");
        final String[] Items={"相册","拍照"};
        dialogBuilder.setItems(Items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(UserMessageActivity.this, "你点击了："+Items[i], Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.setCancelable(true);
        AlertDialog dialog=dialogBuilder.create();
        dialog.show();
    }
}
