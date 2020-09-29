/**
 *
 */
package com.mad.trafficclient.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.mad.trafficclient.R;
import com.mad.trafficclient.net.VolleyLo;
import com.mad.trafficclient.net.VolleyTo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Fragment_4 extends Fragment {

    private TextView tvYe;
    private TextView tvWd;
    private TextView tvSd;
    private TextView roadTv1;
    private TextView roadTv2;
    private TextView roadTv3;
    private TextView roadColor1;
    private TextView roadColor2;
    private TextView roadColor3;
    private VolleyTo volleyTo;
    private List<RoadBean> roadBeans;
    private boolean isLoop = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_layout04, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setVolley_Sense();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isLoop) {
                    setDate();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

    private void setDate() {
        if (roadBeans == null) roadBeans = new ArrayList<>();
        else roadBeans.clear();
        for (int i = 1; i <= 3; i++) {
            setVolley_Road(i);
        }

    }

    private void setVolley_Road(final int id) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_road_status")
                .setJsonObject("UserName", "user1")
                .setJsonObject("RoadId", id)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        roadBeans.add(new RoadBean(id, jsonObject.optInt("Status")));
                        if (roadBeans.size() == 3) {
							RoadBean roadBean = roadBeans.get(0);
							roadTv1.setText("1号道路:"+getState(roadBean.getState()));
							roadColor1.setBackgroundColor(getStateColoe(roadBean.getState()));
							roadBean =roadBeans.get(1);
							roadTv2.setText("2号道路:"+getState(roadBean.getState()));
							roadColor2.setBackgroundColor(getStateColoe(roadBean.getState()));
							roadBean =roadBeans.get(2);
							roadTv3.setText("3号道路:"+getState(roadBean.getState()));
							roadColor3.setBackgroundColor(getStateColoe(roadBean.getState()));
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private int getStateColoe(int state) {
        switch (state) {
            case 1:
                return Color.parseColor("#0ebd12");
            case 2:
                return Color.parseColor("#98ed1f");
            case 3:
                return Color.parseColor("#ffff01");
            case 4:
                return Color.parseColor("#ff0103");
            default:
                return Color.parseColor("#4c060e");
        }
    }

    private String getState(int state) {
        switch (state) {
            case 1:
                return "通畅";
            case 2:
                return "较通畅";
            case 3:
                return "拥挤";
            case 4:
                return "堵塞";
            default:
                return "爆表";
        }
    }

    private void setVolley_Sense() {
        volleyTo = new VolleyTo();
        volleyTo.setUrl("get_all_sense")
                .setJsonObject("UserName", "user1")
                .setLoop(true)
                .setTime(3000)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        tvYe.setText(jsonObject.optString("pm2.5"));
                        tvWd.setText(jsonObject.optString("temperature"));
                        tvSd.setText(jsonObject.optString("humidity"));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void initView() {
        tvYe = (TextView) getView().findViewById(R.id.tv_ye);
        tvWd = (TextView) getView().findViewById(R.id.tv_wd);
        tvSd = (TextView) getView().findViewById(R.id.tv_sd);
        roadTv1 = (TextView) getView().findViewById(R.id.road_tv_1);
        roadTv2 = (TextView) getView().findViewById(R.id.road_tv_2);
        roadTv3 = (TextView) getView().findViewById(R.id.road_tv_3);
        roadColor1 = (TextView) getView().findViewById(R.id.road_color_1);
        roadColor2 = (TextView) getView().findViewById(R.id.road_color_2);
        roadColor3 = (TextView) getView().findViewById(R.id.road_color_3);
    }

    class RoadBean {
        private int id;
        private int state;

        public RoadBean(int id, int state) {
            this.id = id;
            this.state = state;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }

	@Override
	public void onDestroy() {
		super.onDestroy();
		isLoop = false;
		if (volleyTo!=null){
			volleyTo.setLoop(false);
			volleyTo=null;
		}
	}
}
