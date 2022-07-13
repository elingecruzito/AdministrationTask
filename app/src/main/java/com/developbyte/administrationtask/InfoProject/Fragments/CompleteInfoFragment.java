package com.developbyte.administrationtask.InfoProject.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Adapters.ListInfoTaskCompleteAdapter;
import com.developbyte.administrationtask.Adapters.ListInfoTaskProgressAdapter;
import com.developbyte.administrationtask.Adapters.Swipers.ListInfoTaskCompleteSwiper;
import com.developbyte.administrationtask.Adapters.Swipers.ListInfoTaskProgressSwiper;
import com.developbyte.administrationtask.InfoProject.IInfoProject;
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;
import com.developbyte.administrationtask.Widgets.Utilerias;

import java.util.List;

public class CompleteInfoFragment extends Fragment {

    private View view;
    private RecyclerView lstInfoCompletTask;
    private List<TasksModel> tasksModelList;
    private IInfoProject.IInfoProjectRepresentationDelegate representationDelegate;
    private Utilerias utilerias;

    public CompleteInfoFragment(List<TasksModel> tasksModelList, IInfoProject.IInfoProjectRepresentationDelegate representationDelegate, Utilerias utilerias) {
        this.tasksModelList = tasksModelList;
        this.representationDelegate = representationDelegate;
        this.utilerias = utilerias;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_info_complet, container, false);

        lstInfoCompletTask = view.findViewById(R.id.lst_info_complet_task);
        lstInfoCompletTask.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lstInfoCompletTask.setAdapter(new ListInfoTaskCompleteAdapter(tasksModelList, getContext()));

        ItemTouchHelper touchHelper = new ItemTouchHelper(new ListInfoTaskCompleteSwiper(getContext(), tasksModelList, representationDelegate, utilerias));
        touchHelper.attachToRecyclerView(lstInfoCompletTask);

        return view;
    }
}
