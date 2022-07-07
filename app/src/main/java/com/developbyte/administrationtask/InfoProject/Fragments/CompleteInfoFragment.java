package com.developbyte.administrationtask.InfoProject.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Adapters.ListInfoTaskCompleteAdapter;
import com.developbyte.administrationtask.Adapters.ListInfoTaskProgressAdapter;
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;

import java.util.List;

public class CompleteInfoFragment extends Fragment {

    private View view;
    private RecyclerView lstInfoCompletTask;
    private List<TasksModel> tasksModelList;

    public CompleteInfoFragment(List<TasksModel> tasksModelList) {
        this.tasksModelList = tasksModelList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_info_complet, container, false);

        lstInfoCompletTask = view.findViewById(R.id.lst_info_complet_task);
        lstInfoCompletTask.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lstInfoCompletTask.setAdapter(new ListInfoTaskCompleteAdapter(tasksModelList, getContext()));

        return view;
    }
}
