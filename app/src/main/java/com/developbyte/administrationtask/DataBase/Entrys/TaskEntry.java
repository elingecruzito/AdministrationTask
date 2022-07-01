package com.developbyte.administrationtask.DataBase.Entrys;

import android.provider.BaseColumns;

public final class TaskEntry implements BaseColumns{
    public static final String TABLE_NAME = "task";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_TASK = "task_name";
    public static final String COLUMN_NAME_DATE = "task_date";
    public static final String COLUMN_NAME_HOUR = "task_hour";
    public static final String COLUMN_NAME_STATUS = "task_status";
    public static final String COLUMN_NAME_ID_PROJECT = "id_project";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( "
            + COLUMN_NAME_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_NAME_TASK + " TEXT, "
            + COLUMN_NAME_DATE + " TEXT, "
            + COLUMN_NAME_HOUR + " TEXT, "
            + COLUMN_NAME_STATUS + " INTEGER, "
            + COLUMN_NAME_ID_PROJECT + " INTEGER"
            + " );";
    public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
}
