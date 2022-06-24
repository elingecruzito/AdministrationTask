package com.developbyte.administrationtask.ListTask;

import android.util.Log;

import com.developbyte.administrationtask.Abstract.AbstractBusinessController;

public class ListTaskBusinessController extends AbstractBusinessController
                        implements IListTask.IListTaskTransactionHandler, //MasterBussinesController a BussinesController 
                        IListTask.IListTaskRepresentationDelegate, //ViewController a Businnes 
                        IListTask.IListTaskInformationDelegate{ //Service a BusinessComtroller

    private IListTask.IListTaskRepresentationHandler representationHandler;
    private IListTask.IListTaskInformationHandler informationHandler;
    private IListTask.IListTaskTransactionDelegate transactionDelegate;

    public void setRepresentationHandler(IListTask.IListTaskRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    public void setInformationHandler(IListTask.IListTaskInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setTransactionDelegate(IListTask.IListTaskTransactionDelegate transactionDelegate){
        this.transactionDelegate = transactionDelegate;
    }

    @Override
    public void startListTask() {
        representationHandler.showListTask();
    }


}