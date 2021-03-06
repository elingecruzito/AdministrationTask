package com.developbyte.administrationtask.DataBase.Entrys;

import android.provider.BaseColumns;

public final class ProjectEntry implements BaseColumns{

    public static final String TABLE_NAME = "projects";
    public static final String COLUMN_NAME_ID = "id_project";
    public static final String COLUMN_NAME_PROJECT = "project_name";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( "
            + COLUMN_NAME_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_NAME_PROJECT + " TEXT"
            +" );";
    public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public static final String[] projection = {
            ProjectEntry.COLUMN_NAME_ID,
            ProjectEntry.COLUMN_NAME_PROJECT
    };

    public static final String sortOrder = ProjectEntry.COLUMN_NAME_PROJECT + " DESC";
}
