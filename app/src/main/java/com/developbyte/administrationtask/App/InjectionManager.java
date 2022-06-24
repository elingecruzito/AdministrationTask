package com.developbyte.administrationtask.App;

import com.developbyte.administrationtask.Home.HomeBusinessController;
import com.developbyte.administrationtask.Home.HomeMasterViewController;
import com.developbyte.administrationtask.Home.HomeService;
import com.developbyte.administrationtask.Home.HomeViewController;
import com.developbyte.administrationtask.InfoProject.InfoProjectBusinessController;
import com.developbyte.administrationtask.InfoProject.InfoProjectService;
import com.developbyte.administrationtask.InfoProject.InfoProjectViewController;
import com.developbyte.administrationtask.ListTask.ListTaskBusinessController;
import com.developbyte.administrationtask.ListTask.ListTaskService;
import com.developbyte.administrationtask.ListTask.ListTaskViewController;
import com.developbyte.administrationtask.NewProject.NewProjectBusinessController;
import com.developbyte.administrationtask.NewProject.NewProjectService;
import com.developbyte.administrationtask.NewProject.NewProjectViewController;

public class InjectionManager {

    private static InjectionManager instance;

    public static InjectionManager getInstance(){
        if(instance == null)
            instance = new InjectionManager();
        return instance;
    }

    public void startHome(HomeMasterViewController homeMasterViewController){
        MasterBusinessController masterBusinessController = new MasterBusinessController();

        //------------------------------------------------HOME---------------------------------------------

        HomeBusinessController homeBusinessController = new HomeBusinessController();
        HomeViewController homeViewController = new HomeViewController();
        HomeService homeservice = new HomeService();

        homeBusinessController.setRepresentationHandler(homeViewController);
        homeBusinessController.setTransactionDelegate(masterBusinessController);
        homeBusinessController.setInformationHandler(homeservice);

        homeViewController.setTag(HomeMasterViewController.HOME_CONTROLLER);
        homeViewController.setRepresentationDelegate(homeBusinessController);
        homeViewController.setMasterViewController(homeMasterViewController);

        homeservice.setInformationDelegate(homeBusinessController);

        homeMasterViewController.addFragment(homeViewController);
        masterBusinessController.setHomeTransactionHandler(homeBusinessController);
        //------------------------------------------------LISTTASK---------------------------------------------

        ListTaskBusinessController listtaskBusinessController = new ListTaskBusinessController();
        ListTaskViewController listtaskViewController = new ListTaskViewController();
        ListTaskService listtaskservice = new ListTaskService();

        listtaskBusinessController.setRepresentationHandler(listtaskViewController);
        listtaskBusinessController.setTransactionDelegate(masterBusinessController);
        listtaskBusinessController.setInformationHandler(listtaskservice);

        listtaskViewController.setTag(HomeMasterViewController.LISTTASK_CONTROLLER);
        listtaskViewController.setRepresentationDelegate(listtaskBusinessController);
        listtaskViewController.setMasterViewController(homeMasterViewController);

        listtaskservice.setInformationDelegate(listtaskBusinessController);

        homeMasterViewController.addFragment(listtaskViewController);
        masterBusinessController.setListTaskTransactionHandler(listtaskBusinessController);
        //------------------------------------------------NEWPROJECT---------------------------------------------

        NewProjectBusinessController newprojectBusinessController = new NewProjectBusinessController();
        NewProjectViewController newprojectViewController = new NewProjectViewController();
        NewProjectService newprojectservice = new NewProjectService();

        newprojectBusinessController.setRepresentationHandler(newprojectViewController);
        newprojectBusinessController.setTransactionDelegate(masterBusinessController);
        newprojectBusinessController.setInformationHandler(newprojectservice);

        newprojectViewController.setTag(HomeMasterViewController.NEWPROJECT_CONTROLLER);
        newprojectViewController.setRepresentationDelegate(newprojectBusinessController);
        newprojectViewController.setMasterViewController(homeMasterViewController);

        newprojectservice.setInformationDelegate(newprojectBusinessController);

        homeMasterViewController.addFragment(newprojectViewController);
        masterBusinessController.setNewProjectTransactionHandler(newprojectBusinessController);
        //------------------------------------------------INFOPROJECT---------------------------------------------

        InfoProjectBusinessController infoprojectBusinessController = new InfoProjectBusinessController();
        InfoProjectViewController infoprojectViewController = new InfoProjectViewController();
        InfoProjectService infoprojectservice = new InfoProjectService();

        infoprojectBusinessController.setRepresentationHandler(infoprojectViewController);
        infoprojectBusinessController.setTransactionDelegate(masterBusinessController);
        infoprojectBusinessController.setInformationHandler(infoprojectservice);

        infoprojectViewController.setTag(HomeMasterViewController.INFOPROJECT_CONTROLLER);
        infoprojectViewController.setRepresentationDelegate(infoprojectBusinessController);
        infoprojectViewController.setMasterViewController(homeMasterViewController);

        infoprojectservice.setInformationDelegate(infoprojectBusinessController);

        homeMasterViewController.addFragment(infoprojectViewController);
        masterBusinessController.setInfoProjectTransactionHandler(infoprojectBusinessController);


        masterBusinessController.homeInit();
    }
}