package cn.elife.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.elife.elife.R;

/**
 * Created by Bill on 2016/5/2.
 */
public class ShowGoodsView extends RelativeLayout{

    private ImageView iv_image;
    private TextView tv_name;
    private TextView tv_price;
    private TextView tv_sale;

    private String image;
    private String name;
    private String price;
    private String sale;

    private void initviews(Context context){
       View view = View.inflate(context, R.layout.define_widget_showgoods,this);
        iv_image = (ImageView) view.findViewById(R.id.dw_sg_iv_image);
        tv_name = (TextView) view.findViewById(R.id.dw_sg_tv_name);
        tv_price = (TextView) view.findViewById(R.id.dw_sg_tv_price);
        tv_sale = (TextView) view.findViewById(R.id.dw_sg_tv_sale);


    }

    public ShowGoodsView(Context context) {
        super(context);
        initviews(context);



    }

    public ShowGoodsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initviews(context);
        //获取属性值，然后再设置




    }

    public ShowGoodsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initviews(context);
        //要这个方法是干什么用的
    }

    public void setTv_sale(String sale) {
        tv_sale.setText(sale);
    }

    public void setTv_price(String price) {
        tv_price.setText(price);
    }

    public void setTv_name(String name) {
        tv_name.setText(name);
    }

    public void setIv_image(int image) {
        iv_image.setImageResource(image);
    }
}
