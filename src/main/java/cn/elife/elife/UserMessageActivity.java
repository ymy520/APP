package cn.elife.elife;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserMessageActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "usermessage";
    private View headView;
    private View nameView;
    private View sexView;
    private View passwordView;
    private View emailView;
    private View codeView;
    private TextView modifySex;
    private TextView modifyNameText;
    private TextView modifyEmailText;
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
        modifySex= (TextView) findViewById(R.id.user_mysex1);
        modifyNameText= (TextView) findViewById(R.id.user_myname1);
        modifyEmailText= (TextView) findViewById(R.id.user_myemail1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_myhead:
                Log.e(TAG,""+v.getId());
                modifyMyHead();
                break;
            case R.id.user_myname:
                Log.e(TAG,""+v.getId());
                modifyMyName();
                break;
            case R.id.user_mysex:
                Log.e(TAG,""+v.getId());
                modifySex();
                break;
            case R.id.user_mypassword:
                modifyPassword();
                break;
            case R.id.user_myemail:
                modifyEmail();
                break;
            case R.id.user_mycode:
                barcode();
                break;
        }
    }

    private void barcode() {
        Intent intent=new Intent(UserMessageActivity.this,UserBarcodeActivity.class);
        intent.putExtra("code",modifyNameText.getText());
        startActivity(intent);
    }

    private void modifyPassword() {
        Intent intent=new Intent(UserMessageActivity.this,UserPassActivity.class);
        startActivity(intent);
    }

    private void modifyEmail() {
        final EditText et=new EditText(this);
        //设置用户邮箱的方法
        new AlertDialog.Builder(this).setTitle("请输入用户邮箱").setView(
                et).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // 点击确定按钮后得到输入的值，保存
                String str=et.getText().toString();
                modifyEmailText.setText(str);
            }
        }).setNegativeButton("取消", null).show();
    }

    private void modifySex() {
        dialogBuilder=new AlertDialog.Builder(this);
        dialogBuilder.setTitle("修改性别");
        final String[] items={"男","女"};
        dialogBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(UserMessageActivity.this, "你选择了："+items[i], Toast.LENGTH_SHORT).show();
                modifySex.setText(items[i]);
            }
        });
        dialogBuilder.setCancelable(true);
        AlertDialog dialog=dialogBuilder.create();
        dialog.show();
    }

    private void modifyMyName() {
        final EditText et=new EditText(this);
        //设置用户名的方法
        new AlertDialog.Builder(this).setTitle("请输入新用户名").setView(
                et).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // 点击确定按钮后得到输入的值，保存
                String str=et.getText().toString();
                modifyNameText.setText(str);
            }
        }).setNegativeButton("取消", null).show();
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
