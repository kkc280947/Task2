package com.example.kris.task.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.kris.task.R;
import com.example.kris.task.adapter.StatusAdapter;
import com.example.kris.task.modals.StatusModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbluser3 on 14/3/17.
 */
public class ActivityFragment extends Fragment{
    private RecyclerView statusRecycler;
    private StatusAdapter statusAdapter;
    private List<StatusModel> statusModelList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_activity,container,false);

        init(view);
        setActivityFragRecycler();


        return view;
    }

    private void init(View view) {
        statusRecycler=(RecyclerView)view.findViewById(R.id.rvFragActivity);

    }

    private void setActivityFragRecycler() {
        if(statusModelList.isEmpty()) {
            statusModelList.add(new StatusModel("16th March 2017", "Updated Status to be Delivered"));
            statusModelList.add(new StatusModel("25th March 2017", "Updated Status to be Checked"));
            statusModelList.add(new StatusModel("28th March 2017", "Updated Status to be Seen"));
            statusModelList.add(new StatusModel("1st april 2017", "Updated Status to be Delivered"));
        }

        statusAdapter=new StatusAdapter(getActivity(),statusModelList);
        statusRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        statusRecycler.setAdapter(statusAdapter);
    }
}
