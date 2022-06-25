package com.developbyte.administrationtask.Home.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Adapters.ListTaskProgressAdapter;
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;

import java.util.List;

public class ProgressFragment extends Fragment{

    private View view;
    private List<TasksModel> tasksModelList;
    private RecyclerView lstTaskProgress;
    private LinearLayoutManager layoutManagerTaskProgress;
    private ListTaskProgressAdapter listTaskProgressAdapter;

    public ProgressFragment(List<TasksModel> tasksModelList) {
        this.tasksModelList = tasksModelList;
    }

    public void setTasksModelList(List<TasksModel> tasksModelList) {
        if(listTaskProgressAdapter != null){
            listTaskProgressAdapter.setTasksModelList(tasksModelList);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_progress, container, false);

        lstTaskProgress = view.findViewById(R.id.lst_task_progress);

        layoutManagerTaskProgress = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        lstTaskProgress.setLayoutManager(layoutManagerTaskProgress);

        listTaskProgressAdapter = new ListTaskProgressAdapter(tasksModelList, getContext());
        lstTaskProgress.setAdapter(listTaskProgressAdapter);

        return view;
    }


}
