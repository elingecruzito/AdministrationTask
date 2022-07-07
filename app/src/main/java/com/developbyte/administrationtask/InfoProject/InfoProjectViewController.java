package com.developbyte.administrationtask.InfoProject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.Pie;
import com.anychart.anychart.ValueDataEntry;
import com.developbyte.administrationtask.Abstract.AbstractViewController;
import com.developbyte.administrationtask.InfoProject.Fragments.CompleteInfoFragment;
import com.developbyte.administrationtask.InfoProject.Fragments.ProgressInfoFragment;
import com.developbyte.administrationtask.Model.ProjectModel;
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class InfoProjectViewController extends AbstractViewController implements IInfoProject.IInfoProjectRepresentationHandler {

    private int id_project;

    private IInfoProject.IInfoProjectRepresentationDelegate representationDelegate;
    private AppCompatTextView txtNameProjectInfo;

    private Pie pie;
    private AnyChartView chartProject;
    private List<DataEntry> data = new ArrayList<>();

    private TabLayout tbTaskInfo;
    private FloatingActionButton btnAddTaskInfo;

    int sizeInfoProgressTask = 0;
    int sizeInfoCompleteTask = 0;

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
    

    public void setRepresentationDelegate(IInfoProject.IInfoProjectRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    @Override
    public View init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.content_infoproject, container, false);

        txtNameProjectInfo = view.findViewById(R.id.txt_name_project_info);
        chartProject = view.findViewById(R.id.chart_project);

        representationDelegate.getDataProject(id_project);

        tbTaskInfo = view.findViewById(R.id.tb_task_info);
        tbTaskInfo.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        representationDelegate.getAllProgressTask(id_project);
                        break;
                    case 1:
                        representationDelegate.getAllCompleteTask(id_project);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tbTaskInfo.selectTab(tbTaskInfo.getTabAt(0));
        representationDelegate.getAllCompleteTask(id_project);
        representationDelegate.getAllProgressTask(id_project);

        btnAddTaskInfo = view.findViewById(R.id.btn_add_task_info);
        this.createViewNewTask();
        btnAddTaskInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
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
    public void showInfoProject(int id) {
        this.id_project = id;
        masterViewController.presetFragment(this.tag);
    }

    @Override
    public void setDataProject(ProjectModel project) {
        txtNameProjectInfo.setText(project.getProject_name());
    }

    @Override
    public void setAllProgressTask(List<TasksModel> progressTask) {
        sizeInfoProgressTask = progressTask.size();
        setDataChart();
        setFragmentTabTask(new ProgressInfoFragment(progressTask));
    }

    @Override
    public void setAllCompleteTask(List<TasksModel> completeTask) {
        sizeInfoCompleteTask = completeTask.size();
        setDataChart();
        setFragmentTabTask(new CompleteInfoFragment(completeTask));
    }

    public void setDataChart(){

        pie = AnyChart.pie();
        pie.setBackground(getResources().getString(R.string.background_view));

        if (data.size() > 0){
            data.clear();
        }

        data.add(new ValueDataEntry(
                getResources().getString(R.string.lbl_chart_complete),
                sizeInfoCompleteTask
        ));
        data.add(new ValueDataEntry(
                getResources().getString(R.string.lbl_chart_progress),
                sizeInfoProgressTask
        ));

        pie.setData(data);
        chartProject.setChart(pie);
    }

    private void setFragmentTabTask(Fragment fragmentTask) {
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fm_tab_task_info, fragmentTask)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
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
//                listNewTaskAdapter.setTasksModel(new TasksModel(
//                        txtNameNewTask.getText().toString(),
//                        txtDateNewHour.getText().toString(),
//                        txtDateNewTask.getText().toString()
//                ));
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