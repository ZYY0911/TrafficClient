package com.mad.trafficclient.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.mad.trafficclient.R;
import com.mad.trafficclient.util.ImageScale;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/29 at 9:26
 */
public class TpActivity extends Activity {
    private int images[] = {R.drawable.weizhang1, R.drawable.weizhang2, R.drawable.weizhang3, R.drawable.weizhang4};
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tp_layout);
        initView();
        imageView.setImageResource(images[getIntent().getIntExtra("index", 0)]);
        imageView.setOnTouchListener(new ImageScale(imageView));
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.image_view);
    }
}
