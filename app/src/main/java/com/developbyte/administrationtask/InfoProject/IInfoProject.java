package com.developbyte.administrationtask.InfoProject;

import com.developbyte.administrationtask.Model.ProjectModel;
import com.developbyte.administrationtask.Model.TasksModel;

import java.util.List;

public interface IInfoProject {
    //Comunica de MasterBussinesController a BussinesController
    interface IInfoProjectTransactionHandler{
        void startInfoProject(int id);
    }
    
    //Comunica de BussinesController a MasterBussinesController
    interface IInfoProjectTransactionDelegate{
        
    }

    //Comunica de BusinessController a ViewController
    interface IInfoProjectRepresentationHandler{
        void showInfoProject(int id);
        void setDataProject(ProjectModel project);
        void setAllProgressTask(List<TasksModel> progressTask);
        void setAllCompleteTask(List<TasksModel> completeTask);
    }

    //Comunica de Service a BusinessComtroller
    interface IInfoProjectInformationDelegate{
        void setDataProject(ProjectModel project);
        void setAllProgressTask(List<TasksModel> progressTask);
        void setAllCompleteTask(List<TasksModel> completeTask);
    }

    //Comunica de BusinessController a Service
    interface IInfoProjectInformationHandler{
        void getDataProject(int id);
        void getAllProgressTask(int id);
        void getAllCompleteTask(int id);
    }

    //Comunica de ViewController a Businnes
    interface IInfoProjectRepresentationDelegate{
        void getDataProject(int id);
        void getAllProgressTask(int idProject);
        void getAllCompleteTask(int idProject);
    }
}