package com.mad.trafficclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mad.trafficclient.R;
import com.mad.trafficclient.bean.Sjfx;
import com.mad.trafficclient.net.VolleyLo;
import com.mad.trafficclient.net.VolleyTo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/29 at 10:20
 */
public class SjfxFragment extends Fragment {
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private List<Sjfx> sjfxes;
    private ImageView iv1;
    private ImageView iv2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sjfx_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        fragments = new ArrayList<>();
        setVolley();

    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_all_car_peccancy")
                .setJsonObject("UserName", "user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        sjfxes = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<Sjfx>>() {
                                }.getType());
                        Log.i("aaa", "onResponse: "+sjfxes.size());
                        setListDate();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private Map<String, Integer> map;
    private List<Fragment> fragments;

    private void setListDate() {
        map = new HashMap<>();
        for (int i = 0; i < sjfxes.size(); i++) {
            Sjfx sjfx = sjfxes.get(i);
            Integer count = map.get(sjfx.getCarnumber());
            map.put(sjfx.getCarnumber(), count == null ? 1 : count + 1);
        }
        int yes = 0, no = 0, a = 0, b = 0, c = 0;
        for (Integer i : map.values()) {
            if (i == 1) {
                no++;
            } else {
                yes++;
            }
            if (i <= 2) {
                c++;
            } else if (i <= 5) {
                b++;
            } else {
                a++;
            }
        }
        Log.i("aaa", "setListDate: " + a + "-" + b + "-" + c);
        fragments.add(new Sjfxfragment1(yes, no));
        fragments.add(new SjfcFragemnt2(a, b, c));
        viewPager.setAdapter(new MyAdapte(getChildFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                iv1.setImageResource(position==0?R.drawable.yuan_blue:R.drawable.yuan_blue_k);
                iv2.setImageResource(position==1?R.drawable.yuan_blue:R.drawable.yuan_blue_k);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });

    }

    private void initView() {
        viewPager = (ViewPager) getView().findViewById(R.id.view_pager);
        linearLayout = (LinearLayout) getView().findViewById(R.id.linear_layout);
        iv1 = (ImageView) getView().findViewById(R.id.iv_1);
        iv2 = (ImageView)getView(). findViewById(R.id.iv_2);
    }

    class MyAdapte extends FragmentPagerAdapter {

        public MyAdapte(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


}
