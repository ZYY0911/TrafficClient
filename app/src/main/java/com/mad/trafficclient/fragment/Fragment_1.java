/**
 *
 */
package com.mad.trafficclient.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.HldAdapter;
import com.mad.trafficclient.bean.Hld;
import com.mad.trafficclient.net.VolleyLo;
import com.mad.trafficclient.net.VolleyTo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Fragment_1 extends Fragment {

    private ImageView ivPhoto;
    private Spinner mySpinner;
    private ListView listView;
    private List<Hld> hlds;
    private Button btQuery;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_layout01, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setDate();
        AnimationDrawable drawable = (AnimationDrawable) ivPhoto.getDrawable();
        drawable.start();
        btQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setListViewAdapter();
            }
        });
    }

    private void setDate() {
        if (hlds == null) hlds = new ArrayList<>();
        else hlds.clear();

        for (int i = 1; i <= 5; i++) {
            setVolley(i);
        }
    }

    /**输入路口id*/
    private void setVolley(final int id) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_trafficlight_config")
                .setJsonObject("UserName", "user1")
                .setJsonObject("TrafficLightId", id)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        /**保存服务器返回数据*/
                        Hld hld = new Gson().fromJson(
                                jsonObject.optJSONArray("ROWS_DETAIL")
                                        .optJSONObject(0).toString()
                                , Hld.class);
                        hld.setLk(id);
                        hlds.add(hld);
                        /**获取完毕设置界面*/
                        if (hlds.size() == 5) {
                            setListViewAdapter();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private HldAdapter adapter;

    private void setListViewAdapter() {
        /**根据用户所选的排序规则进行排序*/
        Collections.sort(hlds, new Comparator<Hld>() {
            @Override
            public int compare(Hld hld, Hld t1) {
                switch ((int) mySpinner.getSelectedItemId()) {
                    case 0:
                        return hld.getLk() - t1.getLk();
                    case 1:
                        return t1.getLk() - hld.getLk();
                    case 2:
                        return hld.getRedTime() - t1.getRedTime();
                    default:
                        return t1.getRedTime() - hld.getRedTime();
                }
            }
        });
        /**给ListView设置适配器*/
        if (adapter == null) {
            adapter = new HldAdapter(getContext(), hlds);
            listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

    }

    private void initView() {
        ivPhoto = (ImageView) getView().findViewById(R.id.iv_photo);
        mySpinner = (Spinner) getView().findViewById(R.id.my_spinner);
        listView = (ListView) getView().findViewById(R.id.list_view);
        btQuery = (Button) getView().findViewById(R.id.bt_query);
    }
}
