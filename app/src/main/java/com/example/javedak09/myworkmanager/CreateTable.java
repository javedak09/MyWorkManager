package com.example.javedak09.myworkmanager;


public final class CreateTable {

    public static final String DATABASE_NAME = "srcDadu052020.db";
    public static final String DB_NAME = "srcDadu052020_copy.db";
    public static final String PROJECT_NAME = "srcDadu052020";
    public static final int DATABASE_VERSION = 1;


    public static final String SQL_CREATE_USERS = "CREATE TABLE " + UsersContract.singleUser.TABLE_NAME + "("
            + UsersContract.singleUser._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersContract.singleUser.ROW_USERNAME + " TEXT,"
            + UsersContract.singleUser.ROW_PASSWORD + " TEXT,"
            + UsersContract.singleUser.ROW_FULLNAME + " TEXT"
            + " );";
}