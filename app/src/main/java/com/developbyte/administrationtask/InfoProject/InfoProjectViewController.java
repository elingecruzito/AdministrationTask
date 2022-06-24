package com.developbyte.administrationtask.InfoProject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.developbyte.administrationtask.Abstract.AbstractViewController;
import com.developbyte.administrationtask.R;

public class InfoProjectViewController extends AbstractViewController implements IInfoProject.IInfoProjectRepresentationHandler {

    private IInfoProject.IInfoProjectRepresentationDelegate representationDelegate;
    

    public void setRepresentationDelegate(IInfoProject.IInfoProjectRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    @Override
    public View init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.content_infoproject, container, false);




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
    public void showInfoProject() {
        masterViewController.presetFragment(this.tag);
    }
}