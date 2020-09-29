package com.mad.trafficclient.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
                return;
            case 1:
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.car2));
                return;
            case 2:
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.car3));
                return;
            case 3:
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.car4));
                return;
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
        videoView = (VideoView) findViewById(R.id.video_view);
    }
}
