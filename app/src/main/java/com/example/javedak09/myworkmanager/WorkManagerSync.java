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

    public static final String TASK_DESC = "task_desc";

    public WorkManagerSync(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    public static WorkContinuation getInstance() {
        return ;
    }

    @NonNull
    @Override
    public Result doWork() {
        String taskDesc = getInputData().getString(TASK_DESC);
        displayNotification("My Worker", taskDesc);
        return Result.success();
    }


    private void displayNotification(String title, String task) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("simplifiedcoding", "simplifiedcoding", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "simplifiedcoding")
                .setContentTitle(title)
                .setContentText(task)
                .setSmallIcon(R.mipmap.ic_launcher);

        notificationManager.notify(1, notification.build());
    }

}