package com.ctis487.team9.cinemasociety.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.ctis487.team9.cinemasociety.R;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {



    private Button buttonDate;
    // private Button selectDate;
    private DatePickerDialog datePickerDialog;
    private TextView dateTxt;
    private Calendar calendar;
    private int year, month, dayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        buttonDate = findViewById(R.id.buttonDate);
        dateTxt = findViewById(R.id.textDate);
    }


    public void onClick(View view) {

        if (view.getId() == R.id.buttonDate)  {
            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    dateTxt.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                }
            },year, month, dayOfMonth);
            datePickerDialog.show();
        }
    }
}
