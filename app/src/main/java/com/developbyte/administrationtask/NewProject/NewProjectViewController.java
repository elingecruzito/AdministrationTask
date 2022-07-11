package com.developbyte.administrationtask.NewProject;

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
import com.developbyte.administrationtask.R;
import com.developbyte.administrationtask.Widgets.RunnableWidgetAddItemList;
import com.developbyte.administrationtask.Widgets.Utilerias;
import com.developbyte.administrationtask.Widgets.WidgetCreateNewTask;

public class NewProjectViewController extends AbstractViewController implements INewProject.INewProjectRepresentationHandler {

    private INewProject.INewProjectRepresentationDelegate representationDelegate;
    private Utilerias utilerias;

    private AppCompatEditText txtNewNameProject;
    private AppCompatButton btnAddNewTask;
    private RecyclerView lstNewProjectTask;
    private LinearLayoutManager linearLayoutManagerNewProjectTask;
    private ListNewTaskAdapter listNewTaskAdapter;
    private AppCompatButton btnCreateProject;
    private AppCompatButton btnCancelProject;

    private WidgetCreateNewTask newTask;


    public void setRepresentationDelegate(INewProject.INewProjectRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    public void setUtilerias(Utilerias utilerias) {
        this.utilerias = utilerias;
    }

    @Override
    public View init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.content_newproject, container, false);

        newTask = new WidgetCreateNewTask(getActivity(), utilerias);

        txtNewNameProject = view.findViewById(R.id.txt_new_name_project);
        lstNewProjectTask = view.findViewById(R.id.lst_new_project_task);
        linearLayoutManagerNewProjectTask = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        lstNewProjectTask.setLayoutManager(linearLayoutManagerNewProjectTask);
        listNewTaskAdapter = new ListNewTaskAdapter();
        lstNewProjectTask.setAdapter(listNewTaskAdapter);

        btnAddNewTask = view.findViewById(R.id.btn_add_new_task);
        btnAddNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newTask.showCreateNewTask(listNewTaskAdapter, new RunnableWidgetAddItemList());
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

}