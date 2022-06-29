package com.developbyte.administrationtask.Home;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Abstract.AbstractViewController;
import com.developbyte.administrationtask.Adapters.ListDaysMountAdapter;
import com.developbyte.administrationtask.Home.Fragments.CompleteFragment;
import com.developbyte.administrationtask.Home.Fragments.ProgressFragment;
import com.developbyte.administrationtask.Model.DaysMountModel;
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class HomeViewController extends AbstractViewController implements IHome.IHomeRepresentationHandler {

    private IHome.IHomeRepresentationDelegate representationDelegate;

    private AppCompatTextView txtDayToday;
    private AppCompatButton btnCalendar;

    private RecyclerView lstDaysMount;
    private LinearLayoutManager layoutManagerDaysMount;
    private ListDaysMountAdapter listDaysMountAdapter;

    private TabLayout tbTask;

    private ProgressFragment progressFragment;
    private CompleteFragment completeFragment;

    private FloatingActionButton btnAddTask;

    public void setRepresentationDelegate(IHome.IHomeRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.content_home, container, false);

        txtDayToday = view.findViewById(R.id.txt_day_today);
        txtDayToday.setText(YearMonth.now().getMonth().name() + " " + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) +", " + YearMonth.now().getYear());

        btnCalendar = view.findViewById(R.id.btn_calendar);
        btnCalendar.setText(YearMonth.now().getMonth().name());

        lstDaysMount = view.findViewById(R.id.lst_days_mount);

        layoutManagerDaysMount = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        lstDaysMount.setLayoutManager(layoutManagerDaysMount);

        listDaysMountAdapter = new ListDaysMountAdapter();
        lstDaysMount.setAdapter(listDaysMountAdapter);

        representationDelegate.getDaysOfCurrentMount(YearMonth.now().getYear(), YearMonth.now().getMonthValue());

        tbTask = view.findViewById(R.id.tb_task);

        representationDelegate.getTaskInProgress("");
        representationDelegate.getTaskComplete("");

        tbTask.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        setFragmentTabTask(progressFragment);
                        break;

                    case 1:
                        setFragmentTabTask(completeFragment);
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
        tbTask.selectTab(tbTask.getTabAt(0));
        setFragmentTabTask(progressFragment);

        btnAddTask = view.findViewById(R.id.btn_new_project);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                representationDelegate.showNewProject();
            }
        });

        /*
        btnListTask = view.findViewById(R.id.btnListTask);
        btnNewProject = view.findViewById(R.id.btnNewProject);
        btnInfoProject = view.findViewById(R.id.btnInfoProject);

        btnListTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                representationDelegate.showListTask();
            }
        });

        btnNewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                representationDelegate.showNewProject();
            }
        });

        btnInfoProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                representationDelegate.showInfoProject();
            }
        });
        */


        return view;
    }

    private void setFragmentTabTask(Fragment fragmentTask) {
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fm_tab_task, fragmentTask)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
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
        getActivity().finish();
    }

    @Override
    public boolean showHome() {
        return masterViewController.presetFragment2(tag);
    }

    @Override
    public void setDaysOfCurrentMount(List<DaysMountModel> daysOfCurrentMount) {
        listDaysMountAdapter.setDaysMountModelList(daysOfCurrentMount);
    }

    @Override
    public void setTaskInProgress(List<TasksModel> taskInProgress) {
        if(progressFragment == null){
            progressFragment = new ProgressFragment(taskInProgress, representationDelegate);
        }else{
            progressFragment.setTasksModelList(taskInProgress);
        }
    }

    @Override
    public void setTaskComplete(List<TasksModel> taskComplete) {
        if(completeFragment == null){
            completeFragment = new CompleteFragment(taskComplete);
        }else{
            completeFragment.setTasksModelList(taskComplete);
        }
    }
}