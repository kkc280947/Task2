package com.example.kris.task.adapter;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kris.task.R;
import com.example.kris.task.modals.CallerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbluser3 on 3/3/17.
 */
public class CallRecyclerAdapter extends RecyclerView.Adapter<CallRecyclerAdapter.RecyclerViewHolder>{
    private Activity activity;

    private List<CallerModel> callList=new ArrayList<>();
    public CallRecyclerAdapter(Activity activity, List<CallerModel> callList) {
        this.activity=activity;
        this.callList=callList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_call,parent,false));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.tvCallTime.setText(callList.get(position).time);
        holder.tvCallerName.setText(callList.get(position).callerName);
        holder.tvCallCategory.setText(callList.get(position).callCategory);
        if(callList.get(position).callCategory.equals(activity.getString(R.string.Hot))){
            holder.tvCallCategory.setBackground(activity.getResources().getDrawable(R.drawable.hot_color));
            holder.tvCallCategory.setTextColor(activity.getResources().getColor(R.color.red));
        }
        if (position==0){
            holder.vwTop.setVisibility(View.GONE);
        }else if(position==callList.size()-1){
            holder.vwBottom.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return callList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tvCallerName,tvCallTime,tvCallCategory;
        View vwTop,vwBottom;


        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tvCallCategory=(TextView)itemView.findViewById(R.id.tvCategory);
            tvCallerName=(TextView)itemView.findViewById(R.id.tvCallName);
            tvCallTime=(TextView)itemView.findViewById(R.id.tvCallTime);
            vwBottom=itemView.findViewById(R.id.vwBottom);
            vwTop=itemView.findViewById(R.id.vwTop);

        }
    }
}
