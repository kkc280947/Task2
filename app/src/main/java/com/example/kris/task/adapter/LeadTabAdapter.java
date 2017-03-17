package com.example.kris.task.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kris.task.fragments.ActivityFragment;
import com.example.kris.task.fragments.InfoFragment;
import com.example.kris.task.fragments.StatusFragment;

/**
 * Created by cbluser3 on 14/3/17.
 */
public class LeadTabAdapter extends FragmentPagerAdapter {
    private int tabCount;
    public LeadTabAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount=tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new InfoFragment();

            case 1:
                return new StatusFragment();

            case 2:
                return new ActivityFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
