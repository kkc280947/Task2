package com.example.kris.task.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.kris.task.customView.MyMarkerView;
import com.example.kris.task.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbluser3 on 1/3/17.
 */

public class AnalyticsActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    private BarChart barChart;
    private BarDataSet set;
    private YAxis left;
    private BarData data;
    private XAxis xAxis;
    private int barXindex;
    private Description description = null;
    private List<BarEntry> entries = new ArrayList<>();
    private IMarker marker;
    private int[] clickColor;
    private List<Integer> colorType = new ArrayList<>();
    private List<Integer> barColor = new ArrayList<>();
    int[] colorIntegers;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);
        init();
        makeCharts();
    }

    private void makeCharts() {
        
        
        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1f, 25f));
        entries.add(new BarEntry(2f, 40f));
        entries.add(new BarEntry(3f, 20f));
        setYChartAxis();
        setXChartAxis();
        setBarData();
        setBarDecor();


    }

    private void setBarDecor() {
        barChart.setExtraOffsets(0, 0, 0, 15f);
        barChart.getLegend().setEnabled(false);
        barChart.setDescription(description);
        barChart.animateY(2000);
        barChart.setDragOffsetY(10f);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setScaleEnabled(false);
        marker = new MyMarkerView(AnalyticsActivity.this, R.layout.barchart_pop_up);
        barChart.setOnChartValueSelectedListener(this);
    }

    private void setBarData() {
        set = new BarDataSet(entries, "");

        colorIntegers = new int[barColor.size()];
        for (int i = 0; i < colorIntegers.length; i++) {
            colorIntegers[i] = barColor.get(i);
        }
        set.setColors(colorIntegers, getBaseContext());

        data = new BarData(set);
        data.setDrawValues(false);//this will hide text over from barchart
        data.setBarWidth(.75f);


        barChart.setData(data);
    }

    private void setXChartAxis() {

        final String[] quarters = new String[]{getString(R.string.booked), getString(R.string.delivered), getString(R.string.deffrered),
                getString(R.string.lost)};

        //to set x-axis labels
        final IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
            }

        };
        barChart.getAxisRight().setEnabled(false);
        xAxis = barChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setSpaceMin(.5f);
        xAxis.setValueFormatter(formatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAvoidFirstLastClipping(false);//for setting x-data properly
        xAxis.setYOffset(10f);//to give margin between X-axis and data
        xAxis.setTextColor(Color.GRAY);
    }

    private void setYChartAxis() {
        left = barChart.getAxisLeft();
        left.setDrawLabels(true);
        left.setDrawAxisLine(true);
        left.setDrawGridLines(false);
        left.setAxisMaximum(55);
        left.setAxisMinimum(0);
        left.setXOffset(20f);//to give margin between Y axis and data
        left.setTextColor(Color.GRAY);
    }

    private void init() {

        barChart = (BarChart) findViewById(R.id.bctAnalytics);

        barColor.add(R.color.red);
        barColor.add(R.color.yellow);
        barColor.add(R.color.blue);
        barColor.add(R.color.purple);

        colorType.add(0, R.color.grey);
        colorType.add(1, R.color.grey);
        colorType.add(2, R.color.grey);
        colorType.add(3, R.color.grey);
    }

    private void changeColor(int barXindex) {

        int col = barColor.get(barXindex);

        colorType.set(barXindex, col);
        clickColor = new int[colorType.size()];
        for (int i = 0; i < clickColor.length; i++) {
            clickColor[i] = colorType.get(i);
        }
        set.setHighLightAlpha(0);
        set.setColors(clickColor, getBaseContext());
        colorType.set(barXindex, R.color.grey);
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        barChart.setMarker(marker);
        barXindex = (int) e.getX();
        changeColor(barXindex);
    }


    @Override
    public void onNothingSelected() {
        barChart.invalidate();
        set.setColors(colorIntegers, getBaseContext());
    }
}
