package com.example.kris.task.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kris.task.R;
import com.example.kris.task.modals.StatusModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbluser3 on 15/3/17.
 */
public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.RecyclerViewHolder> {
    private Activity activity;
    private List<StatusModel> statusModelList=new ArrayList<>();
    public StatusAdapter(Activity activity, List<StatusModel> statusModelList) {
        this.activity=activity;
        this.statusModelList=statusModelList;

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_fragment_activity,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        holder.tvStatusDate.setText(statusModelList.get(position).date);
        holder.tvStatus.setText(statusModelList.get(position).status);

        if (position==0){
            holder.vwTopLine.setBackgroundColor(activity.getResources().getColor(R.color.transparent));
        }else if(position==statusModelList.size()-1){
            holder.vwTimeline.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return statusModelList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tvStatusDate,tvStatus;
        View vwTimeline,vwTopLine;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tvStatus=(TextView)itemView.findViewById(R.id.tvStatus);
            tvStatusDate=(TextView)itemView.findViewById(R.id.tvDateAct);
            vwTimeline=itemView.findViewById(R.id.vwLineBottom);
            vwTopLine=itemView.findViewById(R.id.vwTopLine);

        }
    }
}
