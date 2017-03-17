package com.example.kris.task.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.kris.task.adapter.CallRecyclerAdapter;
import com.example.kris.task.R;
import com.example.kris.task.modals.CallerModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BarChart barChart;
    private BarDataSet set;
    private Description description=null;
    private BarData data;
    private TextView tvpop,tvSecondScreen;
    private Point p;
    private List<BarEntry> entries=new ArrayList<>();
    private RecyclerView recylerCaller;
    private List<String> popList=new ArrayList<>();
    private List<CallerModel> callList=new ArrayList<>();
    private CallRecyclerAdapter myCallAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        makeBarChart();
        setRecycler();
        setPopUp();

    }

    private void init() {
        barChart = (BarChart) findViewById(R.id.barchart);
        recylerCaller=(RecyclerView)findViewById(R.id.rvMain);
        tvSecondScreen=(TextView)findViewById(R.id.tvClosedLeads);
        tvSecondScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AnalyticsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setPopUp() {
        tvpop=(TextView)findViewById(R.id.tvPops);
        popList.add(getString(R.string.todays_followUp));
        popList.add(getString(R.string.yesterdays_followup));

        tvpop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* if (p != null)
                    showPopup(MainActivity.this, p);*/

               PopupMenu popup = new PopupMenu(MainActivity.this, tvpop);
                //Inflating the Popup using menu file
              popup.getMenuInflater().inflate(R.menu.pop_up, popup.getMenu());
               popup.show();
            }
        });

    }

    private void setRecycler() {

        callList.add(new CallerModel("04:20 PM","Chris Martin","Hot"));
        callList.add(new CallerModel("07:20 PM","Anmol","Cold"));
        callList.add(new CallerModel("04:20 AM","Krishna","Cold"));
        callList.add(new CallerModel("04:20 PM","Chris Martin","Hot"));
        callList.add(new CallerModel("07:20 PM","Anmol","Cold"));
        callList.add(new CallerModel("04:20 AM","Krishna","Cold"));

        myCallAdapter=new CallRecyclerAdapter(this,callList);
        recylerCaller.setLayoutManager(new LinearLayoutManager(this,LinearLayout.VERTICAL,false));
        recylerCaller.setAdapter(myCallAdapter);
        recylerCaller.setNestedScrollingEnabled(true);
    }

    private void makeBarChart() {


        entries.add(new BarEntry(0, 30));
        entries.add(new BarEntry(1, 25));
        entries.add(new BarEntry(2, 40));

        makeYAnalyticsLine();
        makeXAnalyticsLine();
        setAnalyticsBarData();
    }

    private void setAnalyticsBarData() {
        set = new BarDataSet(entries,"");
        set.setColors(new int[]{R.color.red,R.color.yellow,R.color.blue}, getBaseContext());

        data = new BarData(set);
        data.setDrawValues(false);//this will hide text over from barchart
        data.setBarWidth(0.75f);

        barChart.setData(data);
        barChart.setExtraOffsets(0,0,0,15f);
        barChart.getLegend().setEnabled(false);
        barChart.setPinchZoom(false);
        barChart.setTouchEnabled(false);
        barChart.setDescription(description);
        barChart.animateY(2000);
        barChart.moveViewToX(60f);
    }

    private void makeYAnalyticsLine() {
        //For Y-Axis
        YAxis left = barChart.getAxisLeft();
        left.setDrawLabels(true);
        left.setDrawAxisLine(true);
        left.setDrawGridLines(false);
        left.setDrawZeroLine(true);
        left.setAxisMaximum(50);
        left.setAxisMinimum(0);
        left.setSpaceMax(5f);
        left.setXOffset(10f);//to give margin between Y axis and data
        left.setTextColor(Color.GRAY);
    }

    private void makeXAnalyticsLine() {
        final String[] quarters = new String[] { "Hot", "Warm", "Cold" };

        //to set x-axis labels
        IAxisValueFormatter formatter=new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
            }

        };

        barChart.getAxisRight().setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAvoidFirstLastClipping(false);
        xAxis.setYOffset(10f);//to give margin between X-axis and data
        xAxis.setTextColor(Color.GRAY);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        int[] location = new int[2];
        // Get the x, y location and store it in the location[] array
        // location[0] = x, location[1] = y.
        tvpop.getLocationOnScreen(location);
        //Initialize the Point with x, and y positions
        p = new Point();
        p.x = location[0];
        p.y = location[1];
    }


    /*private void showPopup(Activity activity, Point p) {
        int popupWidth = 200;
        int popupHeight = 150;
        popArr = new String[popList.size()];
        popList.toArray(popArr);

        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) activity.findViewById(R.id.llpopup);
        LayoutInflater layoutInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(activity);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 30;
        int OFFSET_Y = 30;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
    }*/


}
