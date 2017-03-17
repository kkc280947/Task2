package com.example.kris.task.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.kris.task.R;
import com.example.kris.task.fragments.VehicleModelFragment;

/**
 * Created by cbluser3 on 16/3/17.
 */

public class VehicleModelActivity extends AppCompatActivity {
    private Button btnModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vehicle_model);
        btnModel=(Button)findViewById(R.id.btnModel);
        btnModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VehicleModelFragment vehicleModelFragment= new VehicleModelFragment();

                vehicleModelFragment.show(getFragmentManager(),"VehicleModel");

            }
        });
    }

}
