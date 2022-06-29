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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Abstract.AbstractViewController;
import com.developbyte.administrationtask.Adapters.ListNewTaskAdapter;
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewProjectViewController extends AbstractViewController implements INewProject.INewProjectRepresentationHandler {

    private INewProject.INewProjectRepresentationDelegate representationDelegate;

    private AppCompatEditText txtNewNameProject;

    private RecyclerView lstNewProjectTask;
    private LinearLayoutManager linearLayoutManagerNewProjectTask;
    private ListNewTaskAdapter listNewTaskAdapter;

    private AppCompatButton btnAddNewTask;
    private AlertDialog.Builder alertDialogAddNewTask;
    private AlertDialog alertDialog;
    private View viewAddNewTask;
    private AppCompatEditText txtNameNewTask;
    private AppCompatEditText txtDateNewTask;
    private AppCompatButton btnModalCalendar;
    private AppCompatEditText txtDateNewHour;
    private AppCompatButton btnModalClock;
    private AppCompatButton btnCancelNewTask;
    private AppCompatButton btnCreateNewTask;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private AppCompatButton btnCreateProject;
    private AppCompatButton btnCancelProject;


    public void setRepresentationDelegate(INewProject.INewProjectRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    @Override
    public View init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.content_newproject, container, false);

        txtNewNameProject = view.findViewById(R.id.txt_new_name_project);
        lstNewProjectTask = view.findViewById(R.id.lst_new_project_task);
        linearLayoutManagerNewProjectTask = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        lstNewProjectTask.setLayoutManager(linearLayoutManagerNewProjectTask);
        listNewTaskAdapter = new ListNewTaskAdapter();
        lstNewProjectTask.setAdapter(listNewTaskAdapter);

        this.createViewNewTask();
        btnAddNewTask = view.findViewById(R.id.btn_add_new_task);
        btnAddNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
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

    private void createViewNewTask(){

        alertDialogAddNewTask = new AlertDialog.Builder(getContext());
        viewAddNewTask = requireActivity().getLayoutInflater().inflate(R.layout.widget_modal_new_task, null);
        alertDialog = alertDialogAddNewTask.setView(viewAddNewTask).create();
        this.createDialogs();
        txtNameNewTask = viewAddNewTask.findViewById(R.id.txt_name_new_task);
        txtDateNewTask = viewAddNewTask.findViewById(R.id.txt_date_new_task);
        btnModalCalendar = viewAddNewTask.findViewById(R.id.btn_modal_calendar);
        btnModalCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        txtDateNewHour = viewAddNewTask.findViewById(R.id.txt_date_new_hour);
        btnModalClock = viewAddNewTask.findViewById(R.id.btn_modal_clock);
        btnModalClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });
        btnCancelNewTask = viewAddNewTask.findViewById(R.id.btn_cancel_new_task);
        btnCancelNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearModalNewTask();
                alertDialog.dismiss();
            }
        });
        btnCreateNewTask = viewAddNewTask.findViewById(R.id.btn_create_new_task);
        btnCreateNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listNewTaskAdapter.setTasksModel(new TasksModel(
                        txtNameNewTask.getText().toString(),
                        txtDateNewHour.getText().toString(),
                        txtDateNewTask.getText().toString()
                ));
                clearModalNewTask();
                alertDialog.dismiss();
            }
        });
    }

    private void clearModalNewTask(){
        txtNameNewTask.setText("");
        txtDateNewTask.setText("");
        txtDateNewHour.setText("");
    }


    private void createDialogs(){
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        datePickerDialog = new DatePickerDialog(getContext(), R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                txtDateNewTask.setText(dateFormat.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH));

        timePickerDialog = new TimePickerDialog(getContext(), R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                txtDateNewHour.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime()));
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
    }
}