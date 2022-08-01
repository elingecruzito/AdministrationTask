package com.developbyte.administrationtask.Widgets;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.developbyte.administrationtask.Adapters.ListNewTaskAdapter;
import com.developbyte.administrationtask.InfoProject.IInfoProject;
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class WidgetCreateNewTask {
    private Activity activity;
    private Utilerias utilerias;

    //private AlertDialog.Builder alertDialogAddNewTask;
    //private AlertDialog alertDialog;
    private BottomSheetDialog alertDialog;
    private View viewAddNewTask;
    private AppCompatEditText txtNameNewTask;
    private AppCompatEditText txtDateNewTask;
    private AppCompatButton btnModalCalendar;
    private AppCompatEditText txtDateNewHour;
    private AppCompatButton btnModalClock;
    private AppCompatButton btnCancelNewTask;
    private AppCompatButton btnCreateNewTask;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    public WidgetCreateNewTask(Activity activity, Utilerias utilerias) {
        this.activity = activity;
        this.utilerias = utilerias;
        init();
    }

    private void init(){
        //alertDialogAddNewTask = new AlertDialog.Builder(activity);
        alertDialog = new BottomSheetDialog(activity);
        viewAddNewTask = activity.getLayoutInflater().inflate(R.layout.widget_modal_new_task, null);
        txtNameNewTask = viewAddNewTask.findViewById(R.id.txt_name_new_task);

        txtDateNewTask = viewAddNewTask.findViewById(R.id.txt_date_new_task);
        datePickerDialog = utilerias.getDatePickerDialog(activity, txtDateNewTask);
        btnModalCalendar = viewAddNewTask.findViewById(R.id.btn_modal_calendar);
        btnModalCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        txtDateNewHour = viewAddNewTask.findViewById(R.id.txt_date_new_hour);
        timePickerDialog = utilerias.getTimePickerDialog(activity, txtDateNewHour);
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
        //alertDialog = alertDialogAddNewTask.setView(viewAddNewTask).create();
        alertDialog.setContentView(viewAddNewTask);
    }

    private void clearModalNewTask(){
        txtNameNewTask.setText("");
        txtDateNewTask.setText("");
        txtDateNewHour.setText("");
    }

    public void showCreateNewTask(ListNewTaskAdapter listNewTaskAdapter, RunnableWidgetAddItemList runnable){

        btnCreateNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                runnable.setListNewTaskAdapter(listNewTaskAdapter);
                runnable.setName(txtNameNewTask.getText().toString());
                runnable.setDate(txtDateNewTask.getText().toString());
                runnable.setHour(txtDateNewHour.getText().toString());

                runnable.run();
                clearModalNewTask();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public void showCreateNewTask(int id_project, RunnableWidgetAddItem addItem, IInfoProject.IInfoProjectRepresentationDelegate representationDelegate){
        btnCreateNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem.setRepresentationDelegate(representationDelegate);
                addItem.setTasksModel(new TasksModel(
                        txtNameNewTask.getText().toString(),
                        txtDateNewHour.getText().toString(),
                        txtDateNewTask.getText().toString(),
                        id_project
                ));
                addItem.run();
                clearModalNewTask();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
   