package com.mad.trafficclient.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.mad.trafficclient.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/29 at 10:31
 */
@SuppressLint("ValidFragment")
public class Sjfxfragment1 extends Fragment {
    private PieChart pirChart;
    private int yes, no;

    public Sjfxfragment1(int yes, int no) {
        this.yes = yes;
        this.no = no;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sjfc_fragemnt1, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(yes, "有重复违章"));
        pieEntries.add(new PieEntry(no, "无重复违章"));
        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setColors(new int[]{Color.parseColor("#AA4442"), Color.parseColor("#4571A6")});
        dataSet.setValueLinePart1Length(1);
        dataSet.setValueLinePart2Length(1);
        dataSet.setValueLinePart1OffsetPercentage(1);
        Log.i("aaa", "onActivityCreated: ");
        dataSet.setValueTextColor(Color.BLACK);

        PieData data = new PieData(dataSet);
        Legend legend = pirChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        pirChart.setData(data);
        pirChart.getDescription().setEnabled(false);
        pirChart.setUsePercentValues(true);
        pirChart.setDrawHoleEnabled(false);
        pirChart.invalidate();
        pirChart.setRotationEnabled(false);
    }



    private void initView() {
        pirChart = (PieChart) getView().findViewById(R.id.pir_chart);
    }
}
