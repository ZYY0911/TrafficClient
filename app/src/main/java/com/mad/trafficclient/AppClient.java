package com.mad.trafficclient;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mad.trafficclient.bean.Yhzx;
import com.mad.trafficclient.net.VolleyLo;
import com.mad.trafficclient.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/29 at 7:49
 */
public class AppClient extends Application {
    private static RequestQueue requestQueue;
    private List<Yhzx> yhzxes = new ArrayList<>();
    private List<Yhzx> scYhList = new ArrayList<>();

    public List<Yhzx> getYhzxes() {
        return yhzxes;
    }

    public List<Yhzx> getScYhList() {
        return scYhList;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this);
        setVolley();
    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_all_user_info")
                .setJsonObject("UserName", "user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            setVolley_User(jsonObject1);
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setVolley_User(final JSONObject jsonObject1) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("user_login")
                .setJsonObject("UserName", jsonObject1.optString("username"))
                .setJsonObject("UserPwd", "123456")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Yhzx yhzx = new Gson().fromJson(jsonObject1.toString(), Yhzx.class);
                        yhzx.setUserRole(jsonObject.optString("UserRole"));
                        yhzxes.add(yhzx);
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    public static void add(JsonObjectRequest jsonObjectRequest) {
        requestQueue.add(jsonObjectRequest);
    }
}
