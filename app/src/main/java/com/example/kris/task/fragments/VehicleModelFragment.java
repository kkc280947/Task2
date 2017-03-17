package com.example.kris.task.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kris.task.R;

import static android.graphics.Color.WHITE;

/**
 * Created by cbluser3 on 17/3/17.
 */
public class VehicleModelFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private TextView tvText,tvPassenger,tvGoods,tvDiesel,tvCNG,tvBuildBus,tvBusChasis,tvAC,tvNonAc;
    private LinearLayout lmVehicle,lmGoods;
    private ImageView ivClose;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.custom_vehicle_dialog,container,false);
        init(view);
        clickEvents();
        return view;
    }

    private void clickEvents() {
        tvPassenger.setOnClickListener(this);
        tvGoods.setOnClickListener(this);
        tvBuildBus.setOnClickListener(this);
        tvBusChasis.setOnClickListener(this);
        tvAC.setOnClickListener(this);
        tvDiesel.setOnClickListener(this);
        tvCNG.setOnClickListener(this);
        tvNonAc.setOnClickListener(this);
        ivClose.setOnClickListener(this);
    }

    private void init(View view) {
        ivClose=(ImageView)view.findViewById(R.id.ivCloseDialog);
        lmVehicle=(LinearLayout)view.findViewById(R.id.lmPassenger);
        lmGoods=(LinearLayout)view.findViewById(R.id.lmGoods);
        tvGoods=(TextView)view.findViewById(R.id.tvGoodsCarrier);
        tvPassenger=(TextView)view.findViewById(R.id.tvPassengerCarrier);
        tvDiesel=(TextView)view.findViewById(R.id.tvDiesel);
        tvCNG=(TextView)view.findViewById(R.id.tvCng);
        lmGoods.setVisibility(LinearLayout.GONE);
        tvBuildBus=(TextView)view.findViewById(R.id.tvBuildUp);
        tvBusChasis=(TextView)view.findViewById(R.id.tvBusChasis);
        tvAC=(TextView)view.findViewById(R.id.tvAC);
        tvNonAc=(TextView)view.findViewById(R.id.tvNonAc);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // creating the fullscreen dialog
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(WHITE));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return dialog;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.ivCloseDialog:
                dialog.dismiss();
                break;

            case R.id.tvGoodsCarrier:

                changeTab(tvPassenger,tvGoods);
                changeTab(tvCNG,tvDiesel);
                lmVehicle.setVisibility(LinearLayout.GONE);
                lmGoods.setVisibility(LinearLayout.VISIBLE);
                break;

            case R.id.tvPassengerCarrier:
                changeTab(tvGoods,tvPassenger);
                changeTab(tvDiesel,tvCNG);

                lmVehicle.setVisibility(LinearLayout.VISIBLE);
                lmGoods.setVisibility(LinearLayout.GONE);
                break;

            case R.id.tvDiesel:
                changeTab(tvCNG,tvDiesel);
                break;

            case R.id.tvCng:
                changeTab(tvDiesel,tvCNG);
                break;

            case R.id.tvBuildUp:
                changeTab(tvBusChasis,tvBuildBus);
                break;

            case R.id.tvBusChasis:
                changeTab(tvBuildBus,tvBusChasis);
                break;
            case R.id.tvAC:
                changeTab(tvNonAc,tvAC);
                break;
            case R.id.tvNonAc:
                changeTab(tvAC,tvNonAc);
        }
    }

    private void changeTab(TextView tv1, TextView tv2) {
        tv1.setBackgroundResource(R.color.transparent);
        tv2.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.toggle_red_fill));
        tv1.setTextColor(Color.GRAY);
        tv2.setTextColor(Color.WHITE);
    }
}
