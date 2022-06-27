package com.developbyte.administrationtask.NewProject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.developbyte.administrationtask.Abstract.AbstractViewController;
import com.developbyte.administrationtask.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewProjectViewController extends AbstractViewController implements INewProject.INewProjectRepresentationHandler {

    private INewProject.INewProjectRepresentationDelegate representationDelegate;

    private AppCompatEditText txtNameTask;
    private AppCompatEditText txtDateTask;
    private AppCompatEditText txtDateHour;

    private AppCompatButton btnModalCalendar;
    private AppCompatButton btnModalClock;

    private AppCompatButton btnCreateTask;
    private AppCompatButton btnCancelTask;

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private SimpleDateFormat dateFormat;

    public void setRepresentationDelegate(INewProject.INewProjectRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    @Override
    public View init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.content_newproject, container, false);

        txtNameTask = view.findViewById(R.id.txt_new_name_task);
        txtDateTask = view.findViewById(R.id.txt_date_task);
        txtDateHour = view.findViewById(R.id.txt_date_hour);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        datePickerDialog = new DatePickerDialog(getContext(), R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                txtDateTask.setText(dateFormat.format(calendar.getTime()));
                Log.i(getClass().getName(), dateFormat.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH));

        timePickerDialog = new TimePickerDialog(getContext(), R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                txtDateHour.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime()));
                Log.i(getClass().getName(), DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime()));
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);


        btnModalCalendar = view.findViewById(R.id.btn_modal_calendar);
        btnModalCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        btnModalClock = view.findViewById(R.id.btn_modal_clock);
        btnModalClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        btnCreateTask = view.findViewById(R.id.btn_create_task);
        btnCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(getClass().getName(), txtNameTask.getText().toString() + " | " + txtDateTask.getText().toString() + " | " + txtDateHour.getText().toString());
            }
        });
        btnCancelTask = view.findViewById(R.id.btn_cancel_task);
        btnCancelTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        return view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void restoreData(Bundle savedInstanceState) {

    }


    @Override
    public void onBackPressed() {
        txtNameTask.setText("");
        txtDateTask.setText("");
        txtDateHour.setText("");
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void showNewProject() {
        masterViewController.presetFragment(this.tag);
    }
}