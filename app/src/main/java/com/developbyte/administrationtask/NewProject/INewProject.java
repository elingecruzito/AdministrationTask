package com.developbyte.administrationtask.NewProject;

import android.content.Context;

import com.developbyte.administrationtask.Model.TasksModel;

import java.util.List;

public interface INewProject {
    //Comunica de MasterBussinesController a BussinesController
    interface INewProjectTransactionHandler{
        void startNewProject();
    }
    
    //Comunica de BussinesController a MasterBussinesController
    interface INewProjectTransactionDelegate{
        
    }

    //Comunica de BusinessController a ViewController
    interface INewProjectRepresentationHandler{
        void showNewProject();
    }

    //Comunica de Service a BusinessComtroller
    interface INewProjectInformationDelegate{
    }

    //Comunica de BusinessController a Service
    interface INewProjectInformationHandler{
        void createNewProject(String name, List<TasksModel> tasksModelList);
    }

    //Comunica de ViewController a Businnes
    interface INewProjectRepresentationDelegate{
        void createNewProject(String name, List<TasksModel> tasksModelList);
    }
}