package com.example.kris.task.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kris.task.R;
import com.example.kris.task.adapter.SeeStatusAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbluser3 on 14/3/17.
 */
public class StatusFragment extends Fragment{
    private List<String> statusLists=new ArrayList<>();
    private RecyclerView rvStatus;
    private SeeStatusAdapter seeStatusAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_status,container,false);
        init(view);
        setStatusRecycler();
        return view;
    }

    private void setStatusRecycler() {
        statusLists.add(0,getString(R.string.Opened));
        statusLists.add(1,getString(R.string.booked));
        statusLists.add(2,getString(R.string.delivered));
        statusLists.add(3,getString(R.string.lost));
        statusLists.add(4,getString(R.string.deffrered));

        seeStatusAdapter=new SeeStatusAdapter(getActivity(),statusLists);
        rvStatus.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rvStatus.setAdapter(seeStatusAdapter);
        rvStatus.setNestedScrollingEnabled(true);
    }

    private void init(View view) {
        rvStatus=(RecyclerView)view.findViewById(R.id.rvStatus);

    }
}
