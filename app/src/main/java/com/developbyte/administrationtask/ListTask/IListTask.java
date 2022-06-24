package com.developbyte.administrationtask.ListTask;

public interface IListTask {
    //Comunica de MasterBussinesController a BussinesController
    interface IListTaskTransactionHandler{
        void startListTask();
    }
    
    //Comunica de BussinesController a MasterBussinesController
    interface IListTaskTransactionDelegate{
        
    }

    //Comunica de BusinessController a ViewController
    interface IListTaskRepresentationHandler{
        void showListTask();
    }

    //Comunica de Service a BusinessComtroller
    interface IListTaskInformationDelegate{
    }

    //Comunica de BusinessController a Service
    interface IListTaskInformationHandler{
    }

    //Comunica de ViewController a Businnes
    interface IListTaskRepresentationDelegate{
        
    }
}