package com.developbyte.administrationtask.Home;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.developbyte.administrationtask.Abstract.AbstractBusinessController;
import com.developbyte.administrationtask.Model.DaysMountModel;
import com.developbyte.administrationtask.Model.TasksModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class HomeBusinessController extends AbstractBusinessController
                        implements IHome.IHomeTransactionHandler, //MasterBussinesController a BussinesController 
                        IHome.IHomeRepresentationDelegate, //ViewController a Businnes 
                        IHome.IHomeInformationDelegate{ //Service a BusinessComtroller

    private IHome.IHomeRepresentationHandler representationHandler;
    private IHome.IHomeInformationHandler informationHandler;
    private IHome.IHomeTransactionDelegate transactionDelegate;

    private List<DaysMountModel> daysMountModels;
    private DateFormat dateFormat;
    private SimpleDateFormat format;
    private Date date;
    private String day_text;

    private List<TasksModel> tasksModels;

    public void setRepresentationHandler(IHome.IHomeRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    public void setInformationHandler(IHome.IHomeInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setTransactionDelegate(IHome.IHomeTransactionDelegate transactionDelegate){
        this.transactionDelegate = transactionDelegate;
    }

    @Override
    public void startHome() {
        representationHandler.showHome();
    }

    @Override
    public void showListTask() {
        transactionDelegate.initListTask();
    }

    @Override
    public void showNewProject() {
        transactionDelegate.initNewProject();
    }

    @Override
    public void showInfoProject() {
        transactionDelegate.initInfoProject();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void getDaysOfCurrentMount(int year, int mount) {

        if(daysMountModels == null){
            daysMountModels = new ArrayList<>();
            format = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat  = new SimpleDateFormat("EEEE");
        }else{
            daysMountModels.clear();
        }

        for (int i = mount == YearMonth.now().getMonthValue() ? Calendar.getInstance().get(Calendar.DAY_OF_MONTH) : 1; i <= YearMonth.now().lengthOfMonth(); i++){
            try {
                date = format.parse(i+"/"+mount+"/"+year);
                day_text = dateFormat.format(date).substring(0,3);
            } catch (ParseException e) {
                day_text = "";
                Log.e(getClass().getName(), e.getMessage());
            }
            daysMountModels.add(new DaysMountModel(i, day_text));
        }

        representationHandler.setDaysOfCurrentMount(daysMountModels);

    }

    @Override
    public void getTaskInProgress(String date) {
        if(tasksModels == null){
            tasksModels = new ArrayList<>();
        }else{
            tasksModels.clear();
        }

        for(int i = 0; i < 50; i++){
            tasksModels.add(new TasksModel(
                    "Task #" + (i+1),
                    "Project",
                    "00:00 am",
                    "dd/mm/yyyy",
                    TasksModel.STATUS_IN_PROGRESS
            ));
        }

        representationHandler.setTaskInProgress(tasksModels);

    }

    @Override
    public void getTaskComplete(String date) {
        if(tasksModels == null){
            tasksModels = new ArrayList<>();
        }else{
            tasksModels.clear();
        }

        for(int i = 0; i < 50; i++){
            tasksModels.add(new TasksModel(
                    "Task #" + (i+1),
                    "Project",
                    "00:00 am",
                    "dd/mm/yyyy",
                    TasksModel.STATUS_COMPLETE
            ));
        }

        representationHandler.setTaskComplete(tasksModels);
    }


}