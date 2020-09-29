package com.mad.trafficclient.net;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mad.trafficclient.AppClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/29 at 7:46
 */
public class VolleyTo extends Thread {
    private String Url = "http://118.190.26.201:8080/apis/";
    private boolean isLoop;
    private int time;
    private JSONObject jsonObject = new JSONObject();
    private VolleyLo volleyLo;

    public VolleyTo setVolleyLo(VolleyLo volleyLo) {
        this.volleyLo = volleyLo;
        return this;
    }

    public VolleyTo setUrl(String url) {
        Url += url;
        return this;
    }

    public VolleyTo setLoop(boolean loop) {
        isLoop = loop;
        return this;
    }

    public VolleyTo setTime(int time) {
        this.time = time;
        return this;
    }

    public VolleyTo setJsonObject(String k, Object v) {
        try {
            jsonObject.put(k, v);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public void run() {
        super.run();
        do {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    volleyLo.onResponse(jsonObject);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyLo.onErrorResponse(volleyError);
                }
            });
            AppClient.add(jsonObjectRequest);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (isLoop);
    }
}
