package com.developbyte.administrationtask.Abstract;

import android.annotation.SuppressLint;
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

    private void initListModel(){
        if(modelList == null){
            modelList = new ArrayList<>();
        }else if(modelList.size() > 0){
            modelList.clear();
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
}