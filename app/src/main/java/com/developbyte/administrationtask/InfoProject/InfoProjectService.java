package com.developbyte.administrationtask.InfoProject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.developbyte.administrationtask.Abstract.AbstractService;
import com.developbyte.administrationtask.DataBase.Entrys.ProjectEntry;
import com.developbyte.administrationtask.DataBase.Entrys.RelationProjectTask;
import com.developbyte.administrationtask.DataBase.Entrys.TaskEntry;
import com.developbyte.administrationtask.DataBase.TaskAdministrationDBHelper;
import com.developbyte.administrationtask.Model.ProjectModel;
import com.developbyte.administrationtask.Model.TasksModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoProjectService extends AbstractService implements IInfoProject.IInfoProjectInformationHandler {

    private IInfoProject.IInfoProjectInformationDelegate iInfoProjectInformationDelegate;
    private Context context;
    private ProjectModel projectModel = new ProjectModel();

    public void setiInfoProjectInformationDelegate(IInfoProject.IInfoProjectInformationDelegate iInfoProjectInformationDelegate) {
        this.iInfoProjectInformationDelegate = iInfoProjectInformationDelegate;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @SuppressLint("Range")
    @Override
    public void getDataProject(int id) {
        dbHelper = new TaskAdministrationDBHelper(context);
        db = dbHelper.getReadableDatabase();

        String selection = ProjectEntry.COLUMN_NAME_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        cursor = db.query(
                ProjectEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            do {
                projectModel.setId_project(cursor.getString(cursor.getColumnIndex(ProjectEntry.COLUMN_NAME_ID)));
                projectModel.setProject_name(cursor.getString(cursor.getColumnIndex(ProjectEntry.COLUMN_NAME_PROJECT)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        dbHelper.close();
        iInfoProjectInformationDelegate.setDataProject(projectModel);
    }

    @Override
    public void getAllProgressTask(int id) {
        loadData(
                RelationProjectTask.QUERY_RELATION + " AND " + TaskEntry.COLUMN_NAME_STATUS + " = ?" + " AND " + TaskEntry.TABLE_NAME + "." +TaskEntry.COLUMN_NAME_ID_PROJECT + " = ?",
                new String[]{String.valueOf(TasksModel.STATUS_IN_PROGRESS), String.valueOf(id)},
                context
        );
        iInfoProjectInformationDelegate.setAllProgressTask(modelList);
    }

    @Override
    public void getAllCompleteTask(int id) {
        loadData(
                RelationProjectTask.QUERY_RELATION + " AND " + TaskEntry.COLUMN_NAME_STATUS + " = ?" + " AND " + TaskEntry.TABLE_NAME + "." +TaskEntry.COLUMN_NAME_ID_PROJECT + " = ?",
                new String[]{String.valueOf(TasksModel.STATUS_COMPLETE), String.valueOf(id)},
                context
        );
        iInfoProjectInformationDelegate.setAllCompleteTask(modelList);
    }
}