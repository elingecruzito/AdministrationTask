package com.developbyte.administrationtask.NewProject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Abstract.AbstractViewController;
import com.developbyte.administrationtask.Adapters.ListNewTaskAdapter;
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;
import com.developbyte.administrationtask.Widgets.Utilerias;

public class NewProjectViewController extends AbstractViewController implements INewProject.INewProjectRepresentationHandler {

    private INewProject.INewProjectRepresentationDelegate representationDelegate;
    private Utilerias utilerias;

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

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private AppCompatButton btnCreateProject;
    private AppCompatButton btnCancelProject;


    public void setRepresentationDelegate(INewProject.INewProjectRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    public void setUtilerias(Utilerias utilerias) {
        this.utilerias = utilerias;
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

        btnCreateProject = view.findViewById(R.id.btn_create_new_project);
        btnCreateProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNewNameProject.getText().toString().isEmpty()){
                    utilerias.showMessageError(R.string.msg_error_create_project);
                }else{
                    utilerias.showMessageConfirmation(R.string.msg_confirmation_create_project, new Runnable() {
                        @Override
                        public void run() {
                            representationDelegate.createNewProject(
                                    txtNewNameProject.getText().toString(),
                                    listNewTaskAdapter.getListTasksModels()
                            );
                        }
                    });
                }
            }
        });
        btnCancelProject = view.findViewById(R.id.btn_cancel_new_project);
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
        txtNewNameProject.setText("");
        listNewTaskAdapter.clearListTaskModels();
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void showNewProject() {
        masterViewController.presetFragment(this.tag);
    }

    private void createViewNewTask(){
        alertDialogAddNewTask = new AlertDialog.Builder(getContext());

        viewAddNewTask = requireActivity().getLayoutInflater().inflate(R.layout.widget_modal_new_task, null);
        txtNameNewTask = viewAddNewTask.findViewById(R.id.txt_name_new_task);

        txtDateNewTask = viewAddNewTask.findViewById(R.id.txt_date_new_task);
        datePickerDialog = utilerias.getDatePickerDialog(getContext(), txtDateNewTask);
        btnModalCalendar = viewAddNewTask.findViewById(R.id.btn_modal_calendar);
        btnModalCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        txtDateNewHour = viewAddNewTask.findViewById(R.id.txt_date_new_hour);
        timePickerDialog = utilerias.getTimePickerDialog(getContext(), txtDateNewHour);
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
        alertDialog = alertDialogAddNewTask.setView(viewAddNewTask).create();
    }

    private void clearModalNewTask(){
        txtNameNewTask.setText("");
        txtDateNewTask.setText("");
        txtDateNewHour.setText("");
    }
}