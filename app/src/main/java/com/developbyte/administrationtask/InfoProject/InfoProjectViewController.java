package com.developbyte.administrationtask.InfoProject;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.RequiresApi;
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
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class InfoProjectViewController extends AbstractViewController implements IInfoProject.IInfoProjectRepresentationHandler {

    private IInfoProject.IInfoProjectRepresentationDelegate representationDelegate;
    private AppCompatTextView txtNameProjectInfo;

    private Pie pie;
    private AnyChartView chartProject;
    private List<DataEntry> data = new ArrayList<>();

    private TabLayout tbTaskInfo;
    private FloatingActionButton btnAddTaskInfo;

    private ProgressInfoFragment progressInfoFragment;
    private CompleteInfoFragment completeInfoFragment;
    

    public void setRepresentationDelegate(IInfoProject.IInfoProjectRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    @Override
    public View init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.content_infoproject, container, false);

        txtNameProjectInfo = view.findViewById(R.id.txt_name_project_info);

        pie = AnyChart.pie();
        pie.setBackground(getResources().getString(R.string.background_view));
        setDataChart();
        pie.setData(data);
        chartProject = view.findViewById(R.id.chart_project);
        chartProject.setChart(pie);

        tbTaskInfo = view.findViewById(R.id.tb_task_info);
        representationDelegate.getAllProgressTask(0);
        representationDelegate.getAllCompleteTask(0);
        tbTaskInfo.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        setFragmentTabTask(progressInfoFragment);
                        break;
                    case 1:
                        setFragmentTabTask(completeInfoFragment);
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
        setFragmentTabTask(progressInfoFragment);

        btnAddTaskInfo = view.findViewById(R.id.btn_add_task_info);

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
    public void showInfoProject() {
        masterViewController.presetFragment(this.tag);
    }

    @Override
    public void setAllProgressTask(List<TasksModel> progressTask) {
        if(progressInfoFragment == null){
            progressInfoFragment = new ProgressInfoFragment(progressTask, getContext());
        }
        progressInfoFragment.setTasksModelList(progressTask);
    }

    @Override
    public void setAllCompleteTask(List<TasksModel> progressTask) {
    }

    public void setDataChart(){

        if (data.size() > 0){
            data.clear();
        }

        data.add(new ValueDataEntry(
                getResources().getString(R.string.lbl_chart_complete),
                ThreadLocalRandom.current().nextInt(0, 999999)
        ));
        data.add(new ValueDataEntry(
                getResources().getString(R.string.lbl_chart_progress),
                ThreadLocalRandom.current().nextInt(0, 999999)
        ));
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