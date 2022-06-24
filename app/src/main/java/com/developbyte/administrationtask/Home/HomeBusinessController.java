package com.developbyte.administrationtask.Home;

import android.util.Log;

import com.developbyte.administrationtask.Abstract.AbstractBusinessController;

public class HomeBusinessController extends AbstractBusinessController
                        implements IHome.IHomeTransactionHandler, //MasterBussinesController a BussinesController 
                        IHome.IHomeRepresentationDelegate, //ViewController a Businnes 
                        IHome.IHomeInformationDelegate{ //Service a BusinessComtroller

    private IHome.IHomeRepresentationHandler representationHandler;
    private IHome.IHomeInformationHandler informationHandler;
    private IHome.IHomeTransactionDelegate transactionDelegate;

    public void setRepresentationHandler(IHome.IHomeRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    public void setInformationHandler(IHome.IHomeInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setTransactionDelegate(IHome.IHomeTransactionDelegate transactionDelegate){
        this.transactionDelegate = transactionDelegate;
    }

    @Override
    public void startHome() {
        representationHandler.showHome();
    }

    @Override
    public void showListTask() {
        transactionDelegate.initListTask();
    }

    @Override
    public void showNewProject() {
        transactionDelegate.initNewProject();
    }

    @Override
    public void showInfoProject() {
        transactionDelegate.initInfoProject();
    }


}