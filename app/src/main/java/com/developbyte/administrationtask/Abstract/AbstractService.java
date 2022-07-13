package com.developbyte.administrationtask.Abstract;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.developbyte.administrationtask.DataBase.Entrys.ProjectEntry;
import com.developbyte.administrationtask.DataBase.Entrys.TaskEntry;
import com.developbyte.administrationtask.DataBase.TaskAdministrationDBHelper;
import com.developbyte.administrationtask.Model.TasksModel;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractService
{

    public TaskAdministrationDBHelper dbHelper;
    public SQLiteDatabase db;
    public Cursor cursor;

    public List<TasksModel> modelList;
    public ContentValues values;

    private Long idRow;
    public int countUpdate;
    public int countDeletes;

    private void initListModel(){
        if(modelList == null){
            modelList = new ArrayList<>();
        }else if(modelList.size() > 0){
            modelList.clear();
        }
    }

    private void initValues(){
        if(values == null){
            values = new ContentValues();
        }else{
            values.clear();
        }
    }

    @SuppressLint("Range")
    public void loadData(String query, String[] selectionArgs, Context context){
        
        initListModel();
        
        dbHelper = new TaskAdministrationDBHelper(context);
        db = dbHelper.getReadableDatabase();

        cursor = db.rawQuery(query, selectionArgs);

        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            do {
                modelList.add(new TasksModel(
                        cursor.getInt(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_ID)),
                        cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_TASK)),
                        cursor.getInt(cursor.getColumnIndex(ProjectEntry.COLUMN_NAME_ID)),
                        cursor.getString(cursor.getColumnIndex(ProjectEntry.COLUMN_NAME_PROJECT)),
                        cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_HOUR)),
                        cursor.getString(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_DATE)),
                        cursor.getInt(cursor.getColumnIndex(TaskEntry.COLUMN_NAME_STATUS))
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        dbHelper.close();
    }

    public TasksModel createTask(TasksModel tasksModel, Context context){

        dbHelper = new TaskAdministrationDBHelper(context);
        db = dbHelper.getReadableDatabase();
        initValues();

        values.put(TaskEntry.COLUMN_NAME_TASK, tasksModel.getTask());
        values.put(TaskEntry.COLUMN_NAME_DATE, tasksModel.getDate());
        values.put(TaskEntry.COLUMN_NAME_HOUR, tasksModel.getHour());
        values.put(TaskEntry.COLUMN_NAME_STATUS, TasksModel.STATUS_IN_PROGRESS);
        values.put(TaskEntry.COLUMN_NAME_ID_PROJECT, tasksModel.getId_project());

        idRow = db.insert(
            TaskEntry.TABLE_NAME,
                null,
            values
        );

        tasksModel.setId_task(idRow.intValue());

        db.close();
        dbHelper.close();

        return tasksModel;
    }

    public void updateStatusTask(int idTask, Context context){
        dbHelper = new TaskAdministrationDBHelper(context);
        db = dbHelper.getReadableDatabase();
        initValues();
        countUpdate = 0;

        values.put(TaskEntry.COLUMN_NAME_STATUS, TasksModel.STATUS_COMPLETE);

        countUpdate = db.update(
                TaskEntry.TABLE_NAME,
                values,
                TaskEntry.COLUMN_NAME_ID + " = ? ",
                new String[]{ String.valueOf(idTask) }
        );

        db.close();
        dbHelper.close();
    }

    public void deleteTask(int idTask, Context context){
        dbHelper = new TaskAdministrationDBHelper(context);
        db = dbHelper.getReadableDatabase();
        initValues();

        countDeletes = db.delete(
            TaskEntry.TABLE_NAME,
                TaskEntry.COLUMN_NAME_ID + " = ?",
                new String[]{ String.valueOf(idTask) }
        );

        db.close();
        dbHelper.close();
    }
}