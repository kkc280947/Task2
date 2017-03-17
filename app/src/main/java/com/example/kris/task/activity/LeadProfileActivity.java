package com.example.kris.task.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kris.task.adapter.LeadTabAdapter;
import com.example.kris.task.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbluser3 on 14/3/17.
 */

public class LeadProfileActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private Toolbar tbLeadTool;
    private TabLayout tabLead;
    private LeadTabAdapter leadTabAdapter;
    private ViewPager pager;
    private int lastPostition;
    private RelativeLayout tab1, tab2,tab3;
    private View v1,v2,v3;
    private int tabCount=3;
    private List<View> view=new ArrayList<>();

    private TextView tabTwo,tabOne,tabThree,tvHeader;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_profile);
        init();
        setToolbar();
        setTabs();
    }

    private void init() {
        tbLeadTool=(Toolbar)findViewById(R.id.tbLeadPro);
        tvHeader=(TextView)findViewById(R.id.tvHeader);
        pager=(ViewPager)findViewById(R.id.pager);
        tabLead=(TabLayout)findViewById(R.id.tlTabLead);
        tvHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LeadProfileActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setTabs() {

        leadTabAdapter=new LeadTabAdapter(getSupportFragmentManager(),tabCount);
        pager.setAdapter(leadTabAdapter);
        tabLead.setupWithViewPager(pager);
        createTabs();
        lastPostition=tabLead.getSelectedTabPosition();
        view.add(0,v1);
        view.add(1,v2);
        view.add(2,v3);



       tabLead.addOnTabSelectedListener(this);
    }

    private void tabTransition(int pos) {

        view.get(pos).setVisibility(View.VISIBLE);

        if(pos==1){
            v1.setVisibility(View.GONE);
            v3.setVisibility(View.GONE);
            int color=tabTwo.getCurrentTextColor();

            tabOne.setTextColor(color);
            tabTwo.setTextColor(Color.RED);
            tabThree.setTextColor(color);
        }
        else if(pos==2){
            v2.setVisibility(View.GONE);
            v1.setVisibility(View.GONE);
            int color=tabThree.getCurrentTextColor();

            tabOne.setTextColor(color);
            tabTwo.setTextColor(color);
            tabThree.setTextColor(Color.RED);
        }
        else if(pos==0){
            v3.setVisibility(View.GONE);
            v2.setVisibility(View.GONE);
            int color=tabOne.getCurrentTextColor();
            tabOne.setTextColor(Color.RED);
            tabTwo.setTextColor(color);
            tabThree.setTextColor(color);
        }
    }

    private void createTabs() {
        tab1 = (RelativeLayout) getLayoutInflater().inflate(
                R.layout.custom_tab_view, null);
        tab2 = (RelativeLayout) getLayoutInflater().inflate(
                R.layout.custom_tab_view2, null);
        tab3 = (RelativeLayout) getLayoutInflater().inflate(
                R.layout.custom_tab_view3, null);
        v1=tab1.findViewById(R.id.vwIndicator1);
        v2=tab2.findViewById(R.id.vwIndicator2);
        v3=tab3.findViewById(R.id.vwIndicator3);

        tabTwo = (TextView) tab2.findViewById(R.id.tvTabNameTwo);
        tabTwo.setText(R.string.status);
        v2.setVisibility(View.GONE);

        tabLead.getTabAt(1).setCustomView(tab2);

        tabThree = (TextView)tab3.findViewById(R.id.tvTabNameThree);
        tabThree.setText(R.string.activity);
        v3.setVisibility(View.GONE);
        tabLead.getTabAt(2).setCustomView(tab3);

        tabOne = (TextView) tab1.findViewById(R.id.tvTabName);
        tabOne.setText(R.string.info);
        tabOne.setTextColor(Color.RED);

        tabLead.getTabAt(0).setCustomView(tab1);
    }

    private void setToolbar() {

        setSupportActionBar(tbLeadTool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_lead_prof,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int pos= tab.getPosition();
        tabTransition(pos);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
