package com.developbyte.administrationtask.InfoProject;

import android.util.Log;

import com.developbyte.administrationtask.Abstract.AbstractBusinessController;

public class InfoProjectBusinessController extends AbstractBusinessController
                        implements IInfoProject.IInfoProjectTransactionHandler, //MasterBussinesController a BussinesController 
                        IInfoProject.IInfoProjectRepresentationDelegate, //ViewController a Businnes 
                        IInfoProject.IInfoProjectInformationDelegate{ //Service a BusinessComtroller

    private IInfoProject.IInfoProjectRepresentationHandler representationHandler;
    private IInfoProject.IInfoProjectInformationHandler informationHandler;
    private IInfoProject.IInfoProjectTransactionDelegate transactionDelegate;

    public void setRepresentationHandler(IInfoProject.IInfoProjectRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    public void setInformationHandler(IInfoProject.IInfoProjectInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setTransactionDelegate(IInfoProject.IInfoProjectTransactionDelegate transactionDelegate){
        this.transactionDelegate = transactionDelegate;
    }

    @Override
    public void startInfoProject() {
        representationHandler.showInfoProject();
    }


}