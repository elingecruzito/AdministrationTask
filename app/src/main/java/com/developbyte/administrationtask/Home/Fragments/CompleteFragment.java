package com.developbyte.administrationtask.Home.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Adapters.ListTaskCompleteAdapter;
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;

import java.util.List;

public class CompleteFragment extends Fragment {

    private View view;
    private List<TasksModel> tasksModelList;
    private RecyclerView lstTaskComplete;
    private LinearLayoutManager linearLayoutManagerTaskComplete;
    private ListTaskCompleteAdapter listTaskCompleteAdapter;

    public CompleteFragment(List<TasksModel> tasksModelList) {
        this.tasksModelList = tasksModelList;
    }

    public void setTasksModelList(List<TasksModel> tasksModelList) {
        if(listTaskCompleteAdapter != null){
            listTaskCompleteAdapter.setTasksModelList(tasksModelList);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_complete, container, false);

        lstTaskComplete = view.findViewById(R.id.lst_task_complete);

        linearLayoutManagerTaskComplete = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        lstTaskComplete.setLayoutManager(linearLayoutManagerTaskComplete);

        listTaskCompleteAdapter = new ListTaskCompleteAdapter(tasksModelList, getContext());
        lstTaskComplete.setAdapter(listTaskCompleteAdapter);

        return view;
    }
}
