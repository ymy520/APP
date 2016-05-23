package cn.elife.elife;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.qrcode.QRCodeWriter;

public class UserBarcodeActivity extends AppCompatActivity {

    private TextView nameTextView;
    private ImageView codeImageView;
    private ImageView codebackimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_code);
        initViews();
        Intent intent=getIntent();
        String username=intent.getStringExtra("code");
        nameTextView.setText(username);
        Bitmap bitmap=generateQRCode(username);
        codeImageView.setImageBitmap(bitmap);
        codebackimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViews() {
        nameTextView= (TextView) findViewById(R.id.barcode_name);
        codeImageView= (ImageView) findViewById(R.id.barcode_image);
        codebackimage= (ImageView) findViewById(R.id.barcode_back);
    }


    private Bitmap generateQRCode(String qrCodeString){
        Bitmap bmp = null;    //二维码图片
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(qrCodeString, BarcodeFormat.QR_CODE, 512, 512); //参数分别表示为: 条码文本内容，条码格式，宽，高
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            //绘制每个像素
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bmp;
    }
}
