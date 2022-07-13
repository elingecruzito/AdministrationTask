package com.developbyte.administrationtask.Home;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.developbyte.administrationtask.Abstract.AbstractBusinessController;
import com.developbyte.administrationtask.Model.DaysMountModel;
import com.developbyte.administrationtask.Model.MonthsModel;
import com.developbyte.administrationtask.Model.TasksModel;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    private List<MonthsModel> monthsModelList;
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
    public void showInfoProject(int id) {
        transactionDelegate.initInfoProject(id);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void getDaysOfCurrentMount(int mount) {

        if(daysMountModels == null){
            daysMountModels = new ArrayList<>();
            format = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat  = new SimpleDateFormat("EEEE");
        }else{
            daysMountModels.clear();
        }

        for (int i = 1; i <= YearMonth.of(Calendar.getInstance().get(Calendar.YEAR), mount).lengthOfMonth(); i++){
            try {
                date = format.parse(i+"/"+mount+"/"+Calendar.getInstance().get(Calendar.YEAR));
                day_text = dateFormat.format(date).substring(0,3);
            } catch (ParseException e) {
                day_text = "";
                Log.e(getClass().getName(), e.getMessage());
            }
            daysMountModels.add(new DaysMountModel(
                    i,
                    day_text,
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)  == i &&
                    Calendar.getInstance().get(Calendar.MONTH) + 1 == mount
                            ? true : false
            ));
        }

        if(Calendar.getInstance().get(Calendar.MONTH) + 1 != mount){
            daysMountModels.get(0).setSelected(true);
        }else{
            daysMountModels.get( Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1 ).setSelected(true);
        }

        representationHandler.setDaysOfCurrentMount(daysMountModels);

    }

    @Override
    public void getTaskInProgress(String date) {
        informationHandler.getTaskInProgress(date);
    }

    @Override
    public void getTaskComplete(String date) {
        informationHandler.getTaskComplete(date);
    }

    private void initYearsMonthsList(){
        if(monthsModelList == null){
            monthsModelList = new ArrayList<>();
        }else if(monthsModelList.size() > 0){
            monthsModelList.clear();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void getMonthsList() {
        initYearsMonthsList();
        for(int i = 0; i < DateFormatSymbols.getInstance().getMonths().length; i++){
            monthsModelList.add(new MonthsModel(
                    DateFormatSymbols.getInstance().getMonths()[i],
                    YearMonth.now().getMonthValue() - 1 == i ? true : false
            ));
        }

        representationHandler.setMonthList(monthsModelList);
    }

    @Override
    public void updateStatusTask(int idTask) {
        informationHandler.updateStatusTask(idTask);
    }

    @Override
    public void deleteTask(int idTask) {
        informationHandler.deleteTask(idTask);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void setTaskInProgress(List<TasksModel> taskInProgress) {
        representationHandler.setTaskInProgress(taskInProgress);
    }

    @Override
    public void setTaskComplete(List<TasksModel> taskComplete) {
        representationHandler.setTaskComplete(taskComplete);
    }

    @Override
    public void updateStatusTaskResult(boolean ready) {
        representationHandler.updateStatusTaskResult(ready);
    }

    @Override
    public void deleteTaskResult(boolean ready) {
        representationHandler.deleteTaskResult(ready);
    }
}