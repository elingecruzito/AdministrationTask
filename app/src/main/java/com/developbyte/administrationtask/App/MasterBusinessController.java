package com.developbyte.administrationtask.App;

import com.developbyte.administrationtask.Home.IHome;
import com.developbyte.administrationtask.InfoProject.IInfoProject;
import com.developbyte.administrationtask.ListTask.IListTask;
import com.developbyte.administrationtask.NewProject.INewProject;

public class MasterBusinessController implements  IHome.IHomeTransactionDelegate,
        IListTask.IListTaskTransactionDelegate,
        INewProject.INewProjectTransactionDelegate,
        IInfoProject.IInfoProjectTransactionDelegate{

    private IHome.IHomeTransactionHandler homeTransactionHandler;
    private IListTask.IListTaskTransactionHandler listtaskTransactionHandler;
    private INewProject.INewProjectTransactionHandler newprojectTransactionHandler;
    private IInfoProject.IInfoProjectTransactionHandler infoprojectTransactionHandler;


    public void setHomeTransactionHandler(IHome.IHomeTransactionHandler homeTransactionHandler) {
        this.homeTransactionHandler = homeTransactionHandler;
    }
    public void setListTaskTransactionHandler(IListTask.IListTaskTransactionHandler listtaskTransactionHandler) {
        this.listtaskTransactionHandler = listtaskTransactionHandler;
    }
    public void setNewProjectTransactionHandler(INewProject.INewProjectTransactionHandler newprojectTransactionHandler) {
        this.newprojectTransactionHandler = newprojectTransactionHandler;
    }
    public void setInfoProjectTransactionHandler(IInfoProject.IInfoProjectTransactionHandler infoprojectTransactionHandler) {
        this.infoprojectTransactionHandler = infoprojectTransactionHandler;
    }


    public void homeInit(){
        homeTransactionHandler.startHome();
    }
    @Override
    public void initListTask() {
        listtaskTransactionHandler.startListTask();
    }
    @Override
    public void initNewProject() {
        newprojectTransactionHandler.startNewProject();
    }
    @Override
    public void initInfoProject() {
        infoprojectTransactionHandler.startInfoProject();
    }

}