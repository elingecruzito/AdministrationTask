package com.developbyte.administrationtask.InfoProject.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Adapters.ListInfoTaskProgressAdapter;
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;

import java.util.List;

public class ProgressInfoFragment extends Fragment {

    private View view;
    private RecyclerView lstInfoProgressTask;
    private List<TasksModel> tasksModelList;

    public ProgressInfoFragment(List<TasksModel> tasksModelList) {
        this.tasksModelList = tasksModelList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_info_progress, container, false);

        lstInfoProgressTask = view.findViewById(R.id.lst_info_progress_task);
        lstInfoProgressTask.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lstInfoProgressTask.setAdapter(new ListInfoTaskProgressAdapter(tasksModelList, getContext()));

        return view;
    }
}
