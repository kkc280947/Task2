package com.example.kris.task.fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kris.task.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.zip.Inflater;

/**
 * Created by cbluser3 on 14/3/17.
 */
public class InfoFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    private ImageView ivDatePicker;
    private TextView tvDate,tvEditCategory,tvEditPrice;
    private Calendar myCalendar;
    private EditText etCategory,etExpectedPrice;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_info,container,false);
        init(view);
        clickListeners();
        return view;
    }

    private void clickListeners() {
        etCategory.setFocusable(false);
        etExpectedPrice.setFocusable(false);
        tvEditPrice.setOnClickListener(this);
        tvEditCategory.setOnClickListener(this);
        ivDatePicker.setOnClickListener(this);
    }

    private void init(View view) {

        ivDatePicker=(ImageView)view.findViewById(R.id.ivDatePicker);
        tvDate=(TextView)view.findViewById(R.id.tvFollowUpDate);
        etCategory=(EditText)view.findViewById(R.id.etCategory);
        etExpectedPrice=(EditText)view.findViewById(R.id.etExpectedPriceValue);
        tvEditPrice=(TextView)view.findViewById(R.id.tvEditPrice);
        tvEditCategory=(TextView)view.findViewById(R.id.tvEditCategButton);

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tvDate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvEditCategButton:{
                etExpectedPrice.setFocusable(false);
                etCategory.setFocusableInTouchMode(true);

                break;
            }

            case R.id.tvEditPrice:{
                etCategory.setFocusable(false);
                etExpectedPrice.setFocusableInTouchMode(true);
                break;
            }
            case R.id.ivDatePicker:{
                myCalendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel();
                    }

                };
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            }
        }
    }
}
