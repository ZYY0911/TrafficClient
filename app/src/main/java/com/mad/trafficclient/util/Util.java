package com.mad.trafficclient.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * 项目名称：
 * 类描述：
 * 创建时间：2017/4/19 9:20
 * 修改人：Administrator
 * 修改时间：2017/4/19 9:20
 * 修改备注：
 */
public class Util {

    public static String urlHttp;
    public static String urlPort ;
    public static String simpleDate(String type, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }
    /**
     * 描述：保存数据到SharedPreferences对象中
     * @param ipUrl
     * @param ipPort
     */

    public static void saveSetting(String ipUrl, String ipPort, Context context) {
        SharedPreferences spSettingSave = context.getSharedPreferences("setting", MODE_PRIVATE);// 将需要记录的数据保存在setting.xml文件中
        SharedPreferences.Editor editor = spSettingSave.edit();
        editor.putString("ipUrl", ipUrl);
        editor.putString("ipPort", ipPort);
        editor.commit();
    }

    /**
     * 描述：获取数据到SharedPreferences对象中
     * @return
     */
    public static UrlBean loadSetting(Context context) {
        UrlBean urlBean=new UrlBean();

        SharedPreferences loadSettingLoad = context.getSharedPreferences("setting", MODE_PRIVATE);
        //这里是将setting.xml 中的数据读出来
        urlBean.setUrl( loadSettingLoad.getString("ipUrl", "118.190.26.201") );
        urlBean.setPort( loadSettingLoad.getString("ipPort", "8080") );

//        String urlSetting = "http://" + urlHttp+ ":" + urlPort + "/";
        return urlBean;
    }
}
