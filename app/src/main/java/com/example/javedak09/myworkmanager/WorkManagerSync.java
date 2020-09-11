package com.example.javedak09.myworkmanager;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Collection;

public class WorkManagerSync extends Worker {

    private String TAG;
    private String syncClass, url, tableName, updateSyncClass;
    private Class contractClass;
    private Collection dbData;

    public static final String TASK_DESC = "I am Work Manager variable task_desc";

    public WorkManagerSync(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    @NonNull
    @Override
    public Result doWork() {
        Data data = getInputData();
        //String taskDesc = data.getString(MainActivity.MESSAGE_STATUS);

        Data outputData = new Data.Builder().putString(TASK_DESC + " - " + data, "My String is here").build();
        return Result.success(outputData);
    }

}