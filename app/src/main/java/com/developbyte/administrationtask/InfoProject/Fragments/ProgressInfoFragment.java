package com.developbyte.administrationtask.InfoProject.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developbyte.administrationtask.Adapters.ListInfoTaskProgressAdapter;
import com.developbyte.administrationtask.Adapters.Swipers.ListInfoTaskProgressSwiper;
import com.developbyte.administrationtask.Home.IHome;
import com.developbyte.administrationtask.InfoProject.IInfoProject;
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.R;
import com.developbyte.administrationtask.Widgets.Utilerias;

import java.util.List;

public class ProgressInfoFragment extends Fragment {

    private View view;
    private RecyclerView lstInfoProgressTask;
    private List<TasksModel> tasksModelList;
    private IInfoProject.IInfoProjectRepresentationDelegate representationDelegate;
    private Utilerias utilerias;

    public ProgressInfoFragment(List<TasksModel> tasksModelList, IInfoProject.IInfoProjectRepresentationDelegate representationDelegate, Utilerias utilerias) {
        this.tasksModelList = tasksModelList;
        this.representationDelegate = representationDelegate;
        this.utilerias = utilerias;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_info_progress, container, false);

        lstInfoProgressTask = view.findViewById(R.id.lst_info_progress_task);
        lstInfoProgressTask.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lstInfoProgressTask.setAdapter(new ListInfoTaskProgressAdapter(tasksModelList, getContext()));

        new ItemTouchHelper(new ListInfoTaskProgressSwiper(getContext(), tasksModelList, representationDelegate, utilerias)).attachToRecyclerView(lstInfoProgressTask);

        return view;
    }
}
