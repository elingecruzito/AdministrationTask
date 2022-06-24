package com.developbyte.administrationtask.InfoProject;

public interface IInfoProject {
    //Comunica de MasterBussinesController a BussinesController
    interface IInfoProjectTransactionHandler{
        void startInfoProject();
    }
    
    //Comunica de BussinesController a MasterBussinesController
    interface IInfoProjectTransactionDelegate{
        
    }

    //Comunica de BusinessController a ViewController
    interface IInfoProjectRepresentationHandler{
        void showInfoProject();
    }

    //Comunica de Service a BusinessComtroller
    interface IInfoProjectInformationDelegate{
    }

    //Comunica de BusinessController a Service
    interface IInfoProjectInformationHandler{
    }

    //Comunica de ViewController a Businnes
    interface IInfoProjectRepresentationDelegate{
        
    }
}