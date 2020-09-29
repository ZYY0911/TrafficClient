package com.mad.trafficclient;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/29 at 7:49
 */
public class AppClient extends Application {
    private static RequestQueue requestQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this);
    }


    public static void add(JsonObjectRequest jsonObjectRequest){
        requestQueue.add(jsonObjectRequest);
    }
}
