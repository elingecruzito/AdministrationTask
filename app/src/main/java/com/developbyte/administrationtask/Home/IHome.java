package com.developbyte.administrationtask.Home;

public interface IHome {
    //Comunica de MasterBussinesController a BussinesController
    interface IHomeTransactionHandler{
        void startHome();
    }
    
    //Comunica de BussinesController a MasterBussinesController
    interface IHomeTransactionDelegate{
        void initListTask();
        void initNewProject();
        void initInfoProject();
        
    }

    //Comunica de BusinessController a ViewController
    interface IHomeRepresentationHandler{
        boolean showHome();
    }

    //Comunica de Service a BusinessComtroller
    interface IHomeInformationDelegate{
    }

    //Comunica de BusinessController a Service
    interface IHomeInformationHandler{
    }

    //Comunica de ViewController a Businnes
    interface IHomeRepresentationDelegate{
        void showListTask();
        void showNewProject();
        void showInfoProject();
        
    }
}