package com.developbyte.administrationtask.InfoProject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.developbyte.administrationtask.Widgets.RunnableWidgetAddItem;
import com.developbyte.administrationtask.Widgets.Utilerias;
import com.developbyte.administrationtask.Widgets.WidgetCreateNewTask;
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
    private Utilerias utilerias;
    private AppCompatTextView txtNameProjectInfo;

    private Pie pie;
    private AnyChartView chartProject;
    private List<DataEntry> data = new ArrayList<>();

    private TabLayout tbTaskInfo;
    private FloatingActionButton btnAddTaskInfo;
    private WidgetCreateNewTask newTask;

    int sizeInfoProgressTask = 0;
    int sizeInfoCompleteTask = 0;
    

    public void setRepresentationDelegate(IInfoProject.IInfoProjectRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    public void setUtilerias(Utilerias utilerias) {
        this.utilerias = utilerias;
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
        refreshFragment();

        btnAddTaskInfo = view.findViewById(R.id.btn_add_task_info);
        newTask = new WidgetCreateNewTask(getActivity(), utilerias);
        btnAddTaskInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newTask.showCreateNewTask(id_project, new RunnableWidgetAddItem(), representationDelegate);
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
        setFragmentTabTask(new ProgressInfoFragment(progressTask, representationDelegate, utilerias));
    }

    @Override
    public void setAllCompleteTask(List<TasksModel> completeTask) {
        sizeInfoCompleteTask = completeTask.size();
        setFragmentTabTask(new CompleteInfoFragment(completeTask, representationDelegate, utilerias));
    }

    @Override
    public void setInsertTask(TasksModel task) {
        refreshFragment();
    }

    @Override
    public void updateStatusTaskResult(boolean ready) {
        if(ready){
            refreshFragment();
        }
    }

    @Override
    public void deleteTask(boolean ready) {
        if(ready){
            refreshFragment();
        }
    }

    private void refreshFragment(){
        if(tbTaskInfo.getSelectedTabPosition() == 0){
            representationDelegate.getAllCompleteTask(id_project);
            representationDelegate.getAllProgressTask(id_project);
        }else{
            representationDelegate.getAllProgressTask(id_project);
            representationDelegate.getAllCompleteTask(id_project);
        }
        setDataChart();
    }

    public void setDataChart(){

        pie = AnyChart.pie();
        pie.setBackground(getResources().getString(R.string.background_view));

        if (data.size() > 0){
            data.clear();
        }

        data.add(new ValueDataEntry(
                sizeInfoCompleteTask + " " + getResources().getString(R.string.lbl_chart_complete),
                sizeInfoCompleteTask
        ));
        data.add(new ValueDataEntry(
                sizeInfoProgressTask + " " + getResources().getString(R.string.lbl_chart_progress),
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

}