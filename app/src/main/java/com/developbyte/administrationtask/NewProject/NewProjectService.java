package com.developbyte.administrationtask.NewProject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.developbyte.administrationtask.Abstract.AbstractService;
import com.developbyte.administrationtask.DataBase.Entrys.ProjectEntry;
import com.developbyte.administrationtask.DataBase.Entrys.TaskEntry;
import com.developbyte.administrationtask.DataBase.TaskAdministrationDBHelper;
import com.developbyte.administrationtask.Model.TasksModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewProjectService extends AbstractService implements INewProject.INewProjectInformationHandler {

    private INewProject.INewProjectInformationDelegate iNewProjectInformationDelegate;
    private ContentValues parameters = new ContentValues();
    private Context context;

    private TaskAdministrationDBHelper dbHelper;
    private SQLiteDatabase db;
    private Long idRow;

    public void setInformationDelegate(INewProject.INewProjectInformationDelegate iNewProjectInformationDelegate) {
        this.iNewProjectInformationDelegate = iNewProjectInformationDelegate;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void createNewProject(String name, List<TasksModel> tasksModelList) {
        if(context != null){
            dbHelper = new TaskAdministrationDBHelper(context);
            db = dbHelper.getWritableDatabase();

            parameters.put(ProjectEntry.COLUMN_NAME_PROJECT, name);
            idRow = db.insert(ProjectEntry.TABLE_NAME, null, parameters);
            parameters.clear();

            for (TasksModel tasksModel: tasksModelList) {
                parameters.put(TaskEntry.COLUMN_NAME_TASK, tasksModel.getTask());
                parameters.put(TaskEntry.COLUMN_NAME_DATE, tasksModel.getDate());
                parameters.put(TaskEntry.COLUMN_NAME_HOUR, tasksModel.getHour());
                parameters.put(TaskEntry.COLUMN_NAME_STATUS, TasksModel.STATUS_IN_PROGRESS);
                parameters.put(TaskEntry.COLUMN_NAME_ID_PROJECT, idRow.intValue());
                db.insert(TaskEntry.TABLE_NAME, null, parameters);
                parameters.clear();
            }
            db.close();
            dbHelper.close();
        }
    }
}