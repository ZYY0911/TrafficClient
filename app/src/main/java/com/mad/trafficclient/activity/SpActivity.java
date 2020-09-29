package com.mad.trafficclient.activity;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.VideoView;

import com.mad.trafficclient.R;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/29 at 9:14
 */
public class SpActivity extends Activity {
    private VideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sp_layout);
        initView();
        switch (getIntent().getIntExtra("index", 0)) {
            case 0:
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.car1));
                break;
            case 1:
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.car2));
                break;
            case 2:
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.car3));
                break;
            case 3:
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.car4));
                break;
        }

        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                finish();
            }
        });
    }

    private void initView() {
        videoView = (VideoView) findViewById(R.id.my_video_view);
    }

//    private int index;
//    private MyVideoView videoView;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        videoView = new MyVideoView(this);
//        setContentView(videoView);
//
//
//    }
//
//    class MyVideoView extends VideoView {
//
//        public MyVideoView(Context context) {
//            super(context);
//            initView();
//        }
//
//        public MyVideoView(Context context, AttributeSet attrs) {
//            super(context, attrs);
//            initView();
//        }
//
//        public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
//            super(context, attrs, defStyleAttr);
//            initView();
//        }
//
//
//        private void initView() {
//            index = getIntent().getIntExtra("index", 0);
//            switch (index) {
//                case 0:
//                    this.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.car1));
//                    break;
//                case 1:
//                    this.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.car2));
//                    break;
//                case 2:
//                    this.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.car3));
//                    break;
//                case 3:
//                    this.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.car4));
//                    break;
//            }
//            this.start();
//            this.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    finish();
//                }
//            });
//        }
//
//
//    }
}
