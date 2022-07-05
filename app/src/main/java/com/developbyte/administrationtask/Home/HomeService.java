package com.developbyte.administrationtask.Home;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.developbyte.administrationtask.Abstract.AbstractService;
import com.developbyte.administrationtask.DataBase.Entrys.ProjectEntry;
import com.developbyte.administrationtask.DataBase.Entrys.RelationProjectTask;
import com.developbyte.administrationtask.DataBase.Entrys.TaskEntry;
import com.developbyte.administrationtask.DataBase.TaskAdministrationDBHelper;
import com.developbyte.administrationtask.Model.TasksModel;
import com.developbyte.administrationtask.Widgets.Utilerias;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class HomeService extends AbstractService implements IHome.IHomeInformationHandler {

    private IHome.IHomeInformationDelegate iHomeInformationDelegate;
    private ContentValues parameters = new ContentValues();
    private Context context;
    private Utilerias utilerias;

    private TaskAdministrationDBHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private Long idRow;

    private List<TasksModel> modelList;

    public void setInformationDelegate(IHome.IHomeInformationDelegate iHomeInformationDelegate) {
        this.iHomeInformationDelegate = iHomeInformationDelegate;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setUtilerias(Utilerias utilerias) {
        this.utilerias = utilerias;
    }

    private void initListModel(){
        if(modelList == null){
            generateTestInformation();
            modelList = new ArrayList<>();
        }else if(modelList.size() > 0){
            modelList.clear();
        }
    }

    private void generateTestInformation(){

        Random RANDOM = new Random();
        dbHelper = new TaskAdministrationDBHelper(context);
        db = dbHelper.getReadableDatabase();
        cursor = db.query(
            ProjectEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if(cursor.getCount() > 0){
            return;
        }

        db = dbHelper.getWritableDatabase();
        for(int i = 0; i < RANDOM.nextInt(99); i++){
            parameters.put(ProjectEntry.COLUMN_NAME_PROJECT, utilerias.randomString());
            idRow = db.insert(ProjectEntry.TABLE_NAME, null, parameters);
            parameters.clear();

            for(int x = 0; x < RANDOM.nextInt(9999); x++){
                parameters.put(TaskEntry.COLUMN_NAME_TASK, utilerias.randomString());
                parameters.put(TaskEntry.COLUMN_NAME_DATE, RANDOM.nextInt(28) + "/" + RANDOM.nextInt(12) + "/2022");
                parameters.put(TaskEntry.COLUMN_NAME_HOUR, RANDOM.nextInt(12) + ":" + RANDOM.nextInt(60) + " pm");
                parameters.put(TaskEntry.COLUMN_NAME_STATUS, RANDOM.nextInt(TasksModel.STATUS_COMPLETE));
                parameters.put(TaskEntry.COLUMN_NAME_ID_PROJECT, idRow.intValue());
                db.insert(TaskEntry.TABLE_NAME, null, parameters);
                parameters.clear();
            }
        }

        db.close();
        dbHelper.close();

    }

    @Override
    public void getTaskInProgress(String date) {
        initListModel();


        loadData(
                RelationProjectTask.QUERY_RELATION + " AND " + TaskEntry.COLUMN_NAME_STATUS + " = ?" + " AND " + TaskEntry.COLUMN_NAME_DATE + " = ?",
                    new String[]{String.valueOf(TasksModel.STATUS_IN_PROGRESS), date}
                );

        iHomeInformationDelegate.setTaskInProgress(modelList);
    }

    @Override
    public void getTaskComplete(String date) {
        initListModel();

        loadData(
                RelationProjectTask.QUERY_RELATION + " AND " + TaskEntry.COLUMN_NAME_STATUS + " = ?" + " AND " + TaskEntry.COLUMN_NAME_DATE + " = ?",
                new String[]{String.valueOf(TasksModel.STATUS_COMPLETE), date}
        );


        iHomeInformationDelegate.setTaskComplete(modelList);
    }

    @SuppressLint("Range")
    private void loadData(String query, String[] selectionArgs){
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