package com.developbyte.administrationtask.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.developbyte.administrationtask.Abstract.AbstractViewController;
import com.developbyte.administrationtask.R;

public class HomeViewController extends AbstractViewController implements IHome.IHomeRepresentationHandler {

    private IHome.IHomeRepresentationDelegate representationDelegate;
    private Button btnListTask,btnNewProject,btnInfoProject;

    public void setRepresentationDelegate(IHome.IHomeRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    @Override
    public View init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.content_home, container, false);

        btnListTask = view.findViewById(R.id.btnListTask);
        btnNewProject = view.findViewById(R.id.btnNewProject);
        btnInfoProject = view.findViewById(R.id.btnInfoProject);

        btnListTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                representationDelegate.showListTask();
            }
        });

        btnNewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                representationDelegate.showNewProject();
            }
        });

        btnInfoProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                representationDelegate.showInfoProject();
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
        getActivity().finish();
    }

    @Override
    public boolean showHome() {
        return masterViewController.presetFragment2(tag);
    }
}