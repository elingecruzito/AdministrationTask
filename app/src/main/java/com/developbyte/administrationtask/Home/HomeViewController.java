package com.developbyte.administrationtask.Home;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Abstract.AbstractViewController;
import com.developbyte.administrationtask.Adapters.ListDaysMountAdapter;
import com.developbyte.administrationtask.Adapters.ModalListMonthsAdapeter;
import com.developbyte.administrationtask.Home.Fragments.CompleteFragment;
import com.developbyte.administrationtask.Home.Fragments.ProgressFragment;
import com.developbyte.administrationtask.Model.DaysMountModel;
import com.developbyte.administrationtask.Model.MonthsModel;
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;
import com.developbyte.administrationtask.Widgets.Utilerias;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormatSymbols;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.List;

public class HomeViewController extends AbstractViewController implements IHome.IHomeRepresentationHandler {

    private IHome.IHomeRepresentationDelegate representationDelegate;
    private Utilerias utilerias;

    private AppCompatTextView txtDayToday;
    private AppCompatButton btnCalendar;
    private AlertDialog.Builder builderMonth;
    private AlertDialog alertDialogMonths;
    private View viewDialogMonths;
    private RecyclerView lstModalMonths;
    private ModalListMonthsAdapeter listMonthsAdapeter;
    private AppCompatButton btnConfirmationModalMonths;

    private RecyclerView lstDaysMount;
    private ListDaysMountAdapter listDaysMountAdapter;

    private AppCompatTextView txtTaskProgress;
    private AppCompatTextView txtTaskComplete;

    private TabLayout tbTask;
    private String dateSelected = "";
    private FloatingActionButton btnAddTask;

    public void setRepresentationDelegate(IHome.IHomeRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    public void setUtilerias(Utilerias utilerias) {
        this.utilerias = utilerias;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.content_home, container, false);

        dateSelected = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/"
                        + Calendar.getInstance().get(Calendar.MONTH) + "/"
                        + Calendar.getInstance().get(Calendar.YEAR);

        txtDayToday = view.findViewById(R.id.txt_day_today);
        txtDayToday.setText(YearMonth.now().getMonth().name() + " " + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) +", " + YearMonth.now().getYear());

        btnCalendar = view.findViewById(R.id.btn_select_month);
        btnCalendar.setText(YearMonth.now().getMonth().name());
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showListMonths();
            }
        });

        lstDaysMount = view.findViewById(R.id.lst_days_mount);
        lstDaysMount.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        listDaysMountAdapter = new ListDaysMountAdapter(getContext(), representationDelegate);
        lstDaysMount.setAdapter(listDaysMountAdapter);

        txtTaskProgress = view.findViewById(R.id.txt_task_progress);
        txtTaskComplete = view.findViewById(R.id.txt_task_complete);
        tbTask = view.findViewById(R.id.tb_task);

        tbTask.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        representationDelegate.getTaskInProgress(dateSelected);
                        break;

                    case 1:
                        representationDelegate.getTaskComplete(dateSelected);
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
        reloadData();

        btnAddTask = view.findViewById(R.id.btn_new_project);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                representationDelegate.showNewProject();
            }
        });

        representationDelegate.getDaysOfCurrentMount(YearMonth.now().getMonthValue());

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void setDaysOfCurrentMount(List<DaysMountModel> daysOfCurrentMount) {
        int currentMonth = listMonthsAdapeter == null ? YearMonth.now().getMonthValue() : listMonthsAdapeter.getIndexSelected();
        listDaysMountAdapter.setDaysMountModelList(daysOfCurrentMount, currentMonth);

        dateSelected = (listDaysMountAdapter.getIndexToday() + 1) + "/"
                + currentMonth + "/"
                + Calendar.getInstance().get(Calendar.YEAR);

        btnCalendar.setText(DateFormatSymbols.getInstance().getMonths()[currentMonth - 1]);
        lstDaysMount.scrollToPosition(listDaysMountAdapter.getIndexToday());

        reloadData();
    }

    @Override
    public void setTaskInProgress(List<TasksModel> taskInProgress) {
        txtTaskProgress.setText(taskInProgress.size() + " " + getResources().getString(R.string.lbl_card_name_task));
        setFragmentTabTask(new ProgressFragment(taskInProgress, representationDelegate, utilerias));

    }

    @Override
    public void setTaskComplete(List<TasksModel> taskComplete) {
        txtTaskComplete.setText(taskComplete.size() + " " + getResources().getString(R.string.lbl_card_name_task));
        setFragmentTabTask(new CompleteFragment(taskComplete, representationDelegate, utilerias));
    }

    @Override
    public void setMonthList(List<MonthsModel> monthList) {
        lstModalMonths = viewDialogMonths.findViewById(R.id.lst_months_modal);
        lstModalMonths.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        listMonthsAdapeter = new ModalListMonthsAdapeter(monthList, getContext());
        lstModalMonths.setAdapter(listMonthsAdapeter);
        lstModalMonths.scrollToPosition(listMonthsAdapeter.getIndexSelected() - 2 );
    }

    @Override
    public void updateStatusTaskResult(boolean ready) {
        if(ready){
            reloadData();
        }
    }

    @Override
    public void deleteTaskResult(boolean ready) {
        if(ready){
            reloadData();
        }
    }

    private void reloadData(){
        if(tbTask.getSelectedTabPosition() == 0){
            representationDelegate.getTaskComplete(dateSelected);
            representationDelegate.getTaskInProgress(dateSelected);
        }else{
            representationDelegate.getTaskInProgress(dateSelected);
            representationDelegate.getTaskComplete(dateSelected);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showListMonths(){

        if(builderMonth == null){
            builderMonth = new AlertDialog.Builder(getContext());
            viewDialogMonths = requireActivity().getLayoutInflater().inflate(R.layout.widget_modal_list_month, null);

            representationDelegate.getMonthsList();

            btnConfirmationModalMonths = viewDialogMonths.findViewById(R.id.btn_confirmation_modal_months);
            btnConfirmationModalMonths.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    representationDelegate.getDaysOfCurrentMount(listMonthsAdapeter.getIndexSelected());
                    alertDialogMonths.dismiss();
                }
            });

            alertDialogMonths = builderMonth.setView(viewDialogMonths).create();
        }
        alertDialogMonths.show();

    }
}