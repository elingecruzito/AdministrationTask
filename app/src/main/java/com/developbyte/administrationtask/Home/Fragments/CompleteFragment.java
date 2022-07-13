package com.developbyte.administrationtask.Home.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Adapters.ListHomeTaskCompleteAdapter;
import com.developbyte.administrationtask.Adapters.Swipers.ListHomeTaskCompleteSwiper;
import com.developbyte.administrationtask.Home.IHome;
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;
import com.developbyte.administrationtask.Widgets.Utilerias;

import java.util.List;

public class CompleteFragment extends Fragment {

    private List<TasksModel> tasksModelList;
    private IHome.IHomeRepresentationDelegate representationDelegate;
    private Utilerias utilerias;

    private View view;
    private RecyclerView lstTaskComplete;


    public CompleteFragment(List<TasksModel> tasksModelList, IHome.IHomeRepresentationDelegate representationDelegate, Utilerias utilerias) {
        this.tasksModelList = tasksModelList;
        this.representationDelegate = representationDelegate;
        this.utilerias = utilerias;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_complete, container, false);

        lstTaskComplete = view.findViewById(R.id.lst_task_complete);
        lstTaskComplete.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lstTaskComplete.setAdapter(new ListHomeTaskCompleteAdapter(tasksModelList, getContext(), representationDelegate));

        new ItemTouchHelper(new ListHomeTaskCompleteSwiper(getContext(), tasksModelList, representationDelegate, utilerias)).attachToRecyclerView(lstTaskComplete);

        return view;
    }
}
