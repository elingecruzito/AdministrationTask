package com.developbyte.administrationtask.NewProject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Abstract.AbstractViewController;
import com.developbyte.administrationtask.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewProjectViewController extends AbstractViewController implements INewProject.INewProjectRepresentationHandler {

    private INewProject.INewProjectRepresentationDelegate representationDelegate;

    private AppCompatEditText txtNewNameProject;

    private RecyclerView lstNewProjectTask;

    private FloatingActionButton btnAddNewTask;
    private AlertDialog.Builder alertDialogAddNewTask;

    private AppCompatButton btnCreateProject;
    private AppCompatButton btnCancelProject;


    public void setRepresentationDelegate(INewProject.INewProjectRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    @Override
    public View init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.content_newproject, container, false);

        txtNewNameProject = view.findViewById(R.id.txt_new_name_project);

        btnAddNewTask = view.findViewById(R.id.btn_add_new_task);
        alertDialogAddNewTask = new AlertDialog.Builder(getContext());
        alertDialogAddNewTask.setView(requireActivity().getLayoutInflater().inflate(R.layout.widget_modal_new_task, null))
                            .create();
        btnAddNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogAddNewTask.show();
            }
        });

        btnCreateProject = view.findViewById(R.id.btn_create_project);
        btnCreateProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(getClass().getName(), txtNewNameProject.getText().toString());
            }
        });
        btnCancelProject = view.findViewById(R.id.btn_cancel_project);
        btnCancelProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        /*txtNameTask = view.findViewById(R.id.txt_new_name_task);
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

         */


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
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void showNewProject() {
        masterViewController.presetFragment(this.tag);
    }
}