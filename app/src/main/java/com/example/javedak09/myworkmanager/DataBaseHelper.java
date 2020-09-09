package com.example.javedak09.myworkmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.example.javedak09.myworkmanager.CreateTable.SQL_CREATE_USERS;

public class DataBaseHelper extends SQLiteOpenHelper {

    private final String TAG = "DatabaseHelper";

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int syncUser(JSONArray userList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UsersContract.singleUser.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            JSONArray jsonArray = userList;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                UsersContract user = new UsersContract();
                user.Sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(UsersContract.singleUser.ROW_USERNAME, user.getUserName());
                values.put(UsersContract.singleUser.ROW_PASSWORD, user.getPassword());
                values.put(UsersContract.singleUser.ROW_FULLNAME, user.getFullName());
//                values.put(singleUser.REGION_DSS, user.getREGION_DSS());
                long rowID = db.insert(UsersContract.singleUser.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;
            }


        } catch (Exception e) {
            Log.d(TAG, "syncUser(e): " + e);
        } finally {
            db.close();
            return insertCount;
        }
    }
}