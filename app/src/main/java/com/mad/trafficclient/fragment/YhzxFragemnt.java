package com.mad.trafficclient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mad.trafficclient.AppClient;
import com.mad.trafficclient.R;
import com.mad.trafficclient.activity.YhzxActivity;
import com.mad.trafficclient.adapter.YhglAdapter;
import com.mad.trafficclient.bean.Yhzx;
import com.mad.trafficclient.net.VolleyLo;
import com.mad.trafficclient.net.VolleyTo;
import com.mad.trafficclient.util.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/29 at 11:10
 */
public class YhzxFragemnt extends Fragment {
    private ListView listView;
    private List<Yhzx> yhzxes;
    private AppClient appClient;
    private List<Yhzx> yhzxesSc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.yhzx_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        appClient = (AppClient) getActivity().getApplication();
        yhzxes = appClient.getYhzxes();
        yhzxesSc = appClient.getScYhList();
        setListView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private YhglAdapter adapter;

    private void setListView() {
        if (adapter == null) {
            adapter = new YhglAdapter(getContext(), yhzxes, getActivity().getWindowManager().getDefaultDisplay().getWidth());
            listView.setAdapter(adapter);
            adapter.setMyitemClick(new YhglAdapter.OnMyitemClick() {
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
                        Yhzx yhzx = yhzxes.get(position);
                        yhzx.setSc(!yhzx.isSc());
                        yhzx.setIs(false);
                        yhzxes.set(position, yhzx);
                        yhzx.setTime(Util.simpleDate("yyyy.M.d HH:mm", new Date()));
                        yhzxesSc.add(yhzx);
                        adapter.notifyDataSetChanged();
                    } else if (lx == 3) {
                        startActivity(new Intent(getContext(), YhzxActivity.class));
                    }
                }
            });
        }


    }

    private void initView() {
        listView = (ListView) getView().findViewById(R.id.list_view);
    }
}
