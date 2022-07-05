package com.developbyte.administrationtask.DataBase.Entrys;

public final class RelationProjectTask {

    public static final String QUERY_RELATION = "SELECT " +
            TaskEntry.COLUMN_NAME_ID +", " +
            TaskEntry.COLUMN_NAME_TASK +", " +
            ProjectEntry.TABLE_NAME + "." + ProjectEntry.COLUMN_NAME_ID + ", " +
            ProjectEntry.COLUMN_NAME_PROJECT + ", " +
            TaskEntry.COLUMN_NAME_DATE +", " +
            TaskEntry.COLUMN_NAME_HOUR +", " +
            TaskEntry.COLUMN_NAME_STATUS +
            " FROM " + TaskEntry.TABLE_NAME + ", " + ProjectEntry.TABLE_NAME +
            " WHERE " + TaskEntry.TABLE_NAME + "." + TaskEntry.COLUMN_NAME_ID_PROJECT + " = " + ProjectEntry.TABLE_NAME + "." + ProjectEntry.COLUMN_NAME_ID;

}
