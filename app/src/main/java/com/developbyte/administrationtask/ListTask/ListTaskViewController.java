package com.developbyte.administrationtask.ListTask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.developbyte.administrationtask.Abstract.AbstractViewController;
import com.developbyte.administrationtask.R;

public class ListTaskViewController extends AbstractViewController implements IListTask.IListTaskRepresentationHandler {

    private IListTask.IListTaskRepresentationDelegate representationDelegate;
    

    public void setRepresentationDelegate(IListTask.IListTaskRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    @Override
    public View init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.content_listtask, container, false);




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
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void showListTask() {
        masterViewController.presetFragment(this.tag);
    }
}