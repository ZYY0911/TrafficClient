package com.mad.trafficclient.net;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/29 at 7:48
 */
public interface VolleyLo {
    void onResponse(JSONObject jsonObject);

    void onErrorResponse(VolleyError volleyError);
}
