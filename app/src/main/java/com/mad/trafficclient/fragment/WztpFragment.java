package com.mad.trafficclient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mad.trafficclient.R;
import com.mad.trafficclient.activity.TpActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/29 at 9:23
 */
public class WztpFragment extends Fragment {
    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layout3;
    private LinearLayout layout4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wztp_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        addClick(layout1,0);
        addClick(layout2,1);
        addClick(layout3,2);
        addClick(layout4,3);
    }

    private void addClick(LinearLayout linearLayout, final int index) {
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TpActivity.class);
                intent.putExtra("index", index);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        layout1 = (LinearLayout) getView().findViewById(R.id.layout_1);
        layout2 = (LinearLayout) getView().findViewById(R.id.layout_2);
        layout3 = (LinearLayout) getView().findViewById(R.id.layout_3);
        layout4 = (LinearLayout) getView().findViewById(R.id.layout_4);
    }
}
