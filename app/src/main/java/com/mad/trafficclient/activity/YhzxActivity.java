package com.mad.trafficclient.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.mad.trafficclient.AppClient;
import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.YhglAdapter2;
import com.mad.trafficclient.bean.Yhzx;

import java.util.List;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-09-29 at 20:52
 */
public class YhzxActivity extends Activity {
    private ImageView change;
    private ListView listView;
    private ImageView ivNone;
    private AppClient appClient;
    private List<Yhzx> yhzxes, allYh;
    private YhglAdapter2 adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yhzx_layout);
        initView();
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        appClient = (AppClient) getApplication();
        yhzxes = appClient.getScYhList();
        allYh = appClient.getYhzxes();
        adapter = new YhglAdapter2(this, yhzxes, getWindowManager().getDefaultDisplay().getWidth());
        listView.setAdapter(adapter);
        listView.setEmptyView(ivNone);
        adapter.setMyitemClick(new YhglAdapter2.OnMyitemClick() {
            @Override
            public void myItemClick(int position, int lx) {
                if (lx == 1) {
                    for (int i = 0; i < yhzxes.size(); i++) {
                        Yhzx yhzx = yhzxes.get(i);
                        if (position == i) {
                            yhzx.setIs(true);
                        } else {
                            yhzx.setIs(false);
                        }
                        yhzxes.set(i, yhzx);
                    }
                    adapter.notifyDataSetChanged();
                } else if (lx == 2) {
                    Yhzx yhzxsc = yhzxes.get(position);
                    yhzxes.remove(position);
                    for (int i = 0; i < allYh.size(); i++) {
                        Yhzx yhzx = allYh.get(i);
                        if (yhzx.getUsername().equals(yhzxsc.getUsername())) {
                            yhzx.setSc(false);
                            yhzx.setIs(false);
                            allYh.set(i, yhzx);
                        }
                    }
                    adapter.notifyDataSetChanged();
                } else if (lx == 3) {
                    Yhzx yhzxsc = yhzxes.get(position);
                    yhzxes.remove(position);
                    for (int i = 0; i < allYh.size(); i++) {
                        Yhzx yhzx = allYh.get(i);
                        if (yhzx.getUsername().equals(yhzxsc.getUsername())) {
                            allYh.remove(i);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void initView() {
        change = (ImageView) findViewById(R.id.change);
        listView = (ListView) findViewById(R.id.list_view);
        ivNone = (ImageView) findViewById(R.id.iv_none);
    }
}
