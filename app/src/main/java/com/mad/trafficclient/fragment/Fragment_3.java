/**
 *
 */
package com.mad.trafficclient.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.j256.ormlite.dao.Dao;
import com.mad.trafficclient.R;
import com.mad.trafficclient.adapter.CzjlAdapter;
import com.mad.trafficclient.bean.Czjl;
import com.mad.trafficclient.net.VolleyLo;
import com.mad.trafficclient.net.VolleyTo;
import com.mad.trafficclient.util.DbHelper;
import com.mad.trafficclient.util.Util;

import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class Fragment_3 extends Fragment {

    private RadioButton btAsc;
    private RadioButton btDesc;
    private ListView listView;
    private Spinner spinnerCh;
    private Button btQuery;
    private Spinner spinnerJe;
    private Button btSubmit;
    private Spinner spinnerCh2;
    private Button btQuery2;
    private TextView tvSuccess;
    private TextView tvSuccess2;
    private TextView tvYe;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == 0) {
                tvSuccess.setVisibility(View.INVISIBLE);
            } else {
                tvSuccess2.setVisibility(View.INVISIBLE);
            }
            return false;
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_layout03, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        tvSuccess.setVisibility(View.INVISIBLE);
        tvSuccess2.setVisibility(View.INVISIBLE);
        initClick();
    }

    private void initClick() {
        btQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCarBalance(true);
            }
        });
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSuccess2.setVisibility(View.VISIBLE);
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("set_car_account_recharge")
                        .setJsonObject("UserName", "user1")
                        .setJsonObject("CarId", spinnerCh.getSelectedItem().toString())
                        .setJsonObject("Money", spinnerJe.getSelectedItem().toString())
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    tvSuccess2.setText("充值成功");
                                } else {
                                    tvSuccess2.setText("充值失败");
                                }
                                Dao<Czjl, Integer> dao = null;
                                try {
                                    dao = new DbHelper(getContext()).getDao(Czjl.class);
                                    dao.create(new Czjl(spinnerCh.getSelectedItem().toString()
                                            , "admin", Integer.parseInt(spinnerJe.getSelectedItem().toString())
                                            , Util.simpleDate("yyyy.M.d HH:mm:ss", new Date())));
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                getCarBalance(false);
                                handler.sendEmptyMessageDelayed(1, 2000);
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                tvSuccess2.setText("充值失败");
                                handler.sendEmptyMessageDelayed(1, 2000);
                            }
                        }).start();
            }
        });
        btQuery2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Dao<Czjl, Integer> dao = new DbHelper(getContext()).getDao(Czjl.class);
                    List<Czjl> czjlList = dao.queryForAll();
                    List<Czjl> czjls = new ArrayList<>();
                    for (int i = 0; i < czjlList.size(); i++) {
                        Czjl czjl = czjlList.get(i);
                        if (czjl.getCh().equals(spinnerCh2.getSelectedItem().toString())) {
                            czjls.add(czjl);
                        }
                    }
                    setAdaptet(czjls);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void setAdaptet(List<Czjl> czjls) {
        Collections.sort(czjls, new Comparator<Czjl>() {
            @Override
            public int compare(Czjl czjl, Czjl t1) {
                if (btAsc.isChecked()) {
                    return czjl.getId() - t1.getId();
                } else {
                    return t1.getId() - czjl.getId();
                }
            }
        });
        listView.setAdapter(new CzjlAdapter(getContext(), czjls));
    }

    private void getCarBalance(final boolean show) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_car_account_balance")
                .setJsonObject("UserName", "user1")
                .setJsonObject("CarId", spinnerCh.getSelectedItem().toString())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        tvYe.setText(jsonObject.optString("Balance"));
                        if (show) {
                            tvSuccess.setVisibility(View.VISIBLE);
                            handler.sendEmptyMessageDelayed(0, 2000);
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void initView() {
        btAsc = (RadioButton) getView().findViewById(R.id.bt_asc);
        btDesc = (RadioButton) getView().findViewById(R.id.bt_desc);
        listView = (ListView) getView().findViewById(R.id.list_view);
        spinnerCh = (Spinner) getView().findViewById(R.id.spinner_ch);
        btQuery = (Button) getView().findViewById(R.id.bt_query);
        spinnerJe = (Spinner) getView().findViewById(R.id.spinner_je);
        btSubmit = (Button) getView().findViewById(R.id.bt_submit);
        spinnerCh2 = (Spinner) getView().findViewById(R.id.spinner_ch_2);
        btQuery2 = (Button) getView().findViewById(R.id.bt_query_2);
        tvSuccess = (TextView) getView().findViewById(R.id.tv_success);
        tvSuccess2 = (TextView) getView().findViewById(R.id.tv_success2);
        tvYe = (TextView) getView().findViewById(R.id.tv_ye);
    }
}
