package com.developbyte.administrationtask.Abstract;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.developbyte.administrationtask.Interfaces.IBackPressed;
import com.developbyte.administrationtask.Interfaces.IMasterViewController;

public abstract class AbstractViewController extends Fragment implements IBackPressed {

    protected IMasterViewController masterViewController;
    public int tag;
    protected View view;
    protected Bundle savedInstanceState;

    public void setTag(int tag) {
        this.tag = tag;
    }

    public void setMasterViewController(IMasterViewController masterViewController) {
        this.masterViewController = masterViewController;
    }

    public abstract View init(LayoutInflater inflater, ViewGroup container);
    public abstract void resume();
    public abstract void onSaveInstanceState(Bundle outState);
    public abstract void restoreData(Bundle savedInstanceState);

    public void ActivityResult(int requestCode, int resultCode, Intent data){}

    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        if(savedInstanceState != null)
            restoreData(savedInstanceState);

        if(view == null){
            view = init(inflater,container);
        }

        return view;
    }

    @Override
    public final void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null)
            restoreData(savedInstanceState);

        resume();
    }
}