package com.example.kris.task.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kris.task.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbluser3 on 16/3/17.
 */
public class SeeStatusAdapter extends RecyclerView.Adapter<SeeStatusAdapter.RecyclerViewHolder> {
    private Activity activity;
    private List<String> statusLists=new ArrayList<>();
    private int row_index;
    public SeeStatusAdapter(Activity activity, List<String> statusLists) {
        this.activity=activity;
        this.statusLists=statusLists;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_status,parent,false));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        holder.tvTextStaus.setText(statusLists.get(position));

        holder.tvTextStaus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index=position;
                notifyDataSetChanged();
            }
        });
        if(row_index==position){
            holder.vwDot.setBackground(activity.getResources().getDrawable(R.drawable.circle_red));
            holder.tvTextStaus.setTextColor(Color.RED);
        }
        else
        {
            holder.vwDot.setBackgroundColor(activity.getResources().getColor(R.color.transparent));
            holder.tvTextStaus.setTextColor(ContextCompat.getColor(activity,R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return statusLists.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tvTextStaus;
        View vwDot;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            vwDot=itemView.findViewById(R.id.vwDot);
            tvTextStaus=(TextView)itemView.findViewById(R.id.tvItemStatus);

        }
    }
}
