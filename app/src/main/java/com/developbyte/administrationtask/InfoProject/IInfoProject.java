package com.developbyte.administrationtask.InfoProject;

import com.developbyte.administrationtask.Model.TasksModel;

import java.util.List;

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
        void setAllProgressTask(List<TasksModel> progressTask);
        void setAllCompleteTask(List<TasksModel> completeTask);
    }

    //Comunica de Service a BusinessComtroller
    interface IInfoProjectInformationDelegate{
    }

    //Comunica de BusinessController a Service
    interface IInfoProjectInformationHandler{
    }

    //Comunica de ViewController a Businnes
    interface IInfoProjectRepresentationDelegate{
        void getAllProgressTask(int idProject);
        void getAllCompleteTask(int idProject);
    }
}