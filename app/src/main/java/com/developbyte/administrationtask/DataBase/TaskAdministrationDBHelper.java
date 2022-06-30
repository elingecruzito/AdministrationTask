package com.developbyte.administrationtask.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.developbyte.administrationtask.DataBase.Entrys.ProjectEntry;
import com.developbyte.administrationtask.DataBase.Entrys.TaskEntry;

public class TaskAdministrationDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "taskAdministration.db";


    public TaskAdministrationDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ProjectEntry.CREATE_TABLE);
        sqLiteDatabase.execSQL(TaskEntry.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ProjectEntry.DELETE_TABLE);
        sqLiteDatabase.execSQL(TaskEntry.DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
