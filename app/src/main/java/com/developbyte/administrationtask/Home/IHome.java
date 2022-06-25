package com.developbyte.administrationtask.Home;

import com.developbyte.administrationtask.Model.DaysMountModel;
import com.developbyte.administrationtask.Model.TasksModel;

import java.util.List;

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
        void setDaysOfCurrentMount(List<DaysMountModel> daysOfCurrentMount);
        void setTaskInProgress(List<TasksModel> taskInProgress);
        void setTaskComplete(List<TasksModel> taskComplete);
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

        void getDaysOfCurrentMount(int year, int mount);
        void getTaskInProgress(String date);
        void getTaskComplete(String date);
        
    }
}