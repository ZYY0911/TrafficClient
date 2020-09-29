/**
 *
 */
package com.mad.trafficclient.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mad.trafficclient.R;


public class Fragment_2 extends Fragment {

    private TextView tvWzsp;
    private TextView tvWzpt;
    private FrameLayout frameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_layout02, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        replace(new WzspFragment());
        tvWzsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(new WzspFragment());
                tvWzsp.setBackgroundResource(R.drawable.text_line_red);
                tvWzsp.setTextColor(Color.parseColor("#ee6e55"));
                tvWzpt.setTextColor(Color.BLACK);
                tvWzpt.setBackgroundResource(R.drawable.text_line_black);
            }
        });
        tvWzpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replace(new WztpFragment());
                tvWzpt.setBackgroundResource(R.drawable.text_line_red);
                tvWzpt.setTextColor(Color.parseColor("#ee6e55"));
                tvWzsp.setTextColor(Color.BLACK);
                tvWzsp.setBackgroundResource(R.drawable.text_line_black);
            }
        });
    }


    private void replace(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment).commit();

    }

    private void initView() {
        tvWzsp = (TextView) getView().findViewById(R.id.tv_wzsp);
        tvWzpt = (TextView) getView().findViewById(R.id.tv_wzpt);
        frameLayout = (FrameLayout) getView().findViewById(R.id.frame_layout);
    }
}
