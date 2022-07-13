package com.developbyte.administrationtask.Home.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Adapters.ListHomeTaskProgressAdapter;
import com.developbyte.administrationtask.Adapters.Swipers.ListHomeTaskProgressSwiper;
import com.developbyte.administrationtask.Adapters.Swipers.ListInfoTaskProgressSwiper;
import com.developbyte.administrationtask.Home.IHome;
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;
import com.developbyte.administrationtask.Widgets.Utilerias;

import java.util.List;

public class ProgressFragment extends Fragment{

    private View view;
    private List<TasksModel> tasksModelList;
    private RecyclerView lstTaskProgress;
    private ListHomeTaskProgressAdapter listTaskProgressAdapter;
    private IHome.IHomeRepresentationDelegate representationDelegate;
    private Utilerias utilerias;

    public ProgressFragment(List<TasksModel> tasksModelList, IHome.IHomeRepresentationDelegate representationDelegate, Utilerias utilerias) {
        this.tasksModelList = tasksModelList;
        this.representationDelegate = representationDelegate;
        this.utilerias = utilerias;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_progress, container, false);

        lstTaskProgress = view.findViewById(R.id.lst_task_progress);
        lstTaskProgress.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        listTaskProgressAdapter = new ListHomeTaskProgressAdapter(tasksModelList, getContext(), representationDelegate);
        lstTaskProgress.setAdapter(listTaskProgressAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ListHomeTaskProgressSwiper(getContext(), tasksModelList, representationDelegate, utilerias));
        itemTouchHelper.attachToRecyclerView(lstTaskProgress);

        return view;
    }


}
