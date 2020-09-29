package com.mad.trafficclient.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.mad.trafficclient.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/9/29 at 10:46
 */
@SuppressLint("ValidFragment")
public class SjfcFragemnt2 extends Fragment {

    private HorizontalBarChart pirChart;
    private int a, b, c;

    public SjfcFragemnt2(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sjfc_fragemnt2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    @Override
    public void onResume() {
        super.onResume();
        List<BarEntry> entries = new ArrayList<>();
        float all = a + b + c;
        entries.add(new BarEntry(0, (float) (c / all)*100));
        entries.add(new BarEntry(1, (float) (b / all)*100));
        entries.add(new BarEntry(2, (float) (a / all)*100));
        BarDataSet  dataSet = new BarDataSet(entries,"");
        dataSet.setColors(new int[]{Color.parseColor("#90D34E")
                ,Color.parseColor("#4E82BF")
                ,Color.parseColor("#C11511")});
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat format = new DecimalFormat("0.00");
                return format.format(value)+"%";
            }
        });
        dataSet.setValueTextColor(Color.RED);
        dataSet.setBarBorderWidth(0.2f);
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.2f);
        XAxis xAxis = pirChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"1~2条违章","3~5条违章","5条以上违章"}));
        YAxis yAxis = pirChart.getAxisLeft();
        yAxis.setStartAtZero(true);
        yAxis.setEnabled(false);
        YAxis yAxis1 = pirChart.getAxisRight();
        yAxis1.setStartAtZero(true);
        yAxis1.setValueFormatter(new PercentFormatter());
        yAxis1.setLabelCount(8);
        yAxis1.setTextSize(10f);
        pirChart.getLegend().setEnabled(false);
       // pirChart.setTouchEnabled(false);
        pirChart.setData(data);
        pirChart.getDescription().setEnabled(false);
        pirChart.invalidate();
    }

    private void initView() {
        pirChart = (HorizontalBarChart) getView().findViewById(R.id.pir_chart);
    }
}
