package com.example.javedak09.myworkmanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javedak09.myworkmanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static String MESSAGE_STATUS = "i m main activity message";
    ActivityMainBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    public void cmdFetchData(View view) {
        Toast.makeText(this, "I m click", Toast.LENGTH_SHORT).show();


        final Data data = new Data.Builder().putString(MESSAGE_STATUS, "The task data passed from MainActivity").build();

        final WorkManager work = WorkManager.getInstance();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(WorkManagerSync.class).setInputData(data).build();
        work.enqueue(workRequest);


        work.getWorkInfoByIdLiveData(workRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(@Nullable WorkInfo workInfo) {
                if (workInfo != null) {

                    WorkInfo.State state = workInfo.getState();

                    //bi.txtmsg.append(state.toString() + "\n");

                    bi.txtmsg.append(workInfo.getOutputData() + "\n");

                    //bi.txtmsg.append(state.getDeclaringClass().getName() + "\n");
                }
            }
        });

    }

    public void cmdFetchData1() {
        Toast.makeText(this, "I m click", Toast.LENGTH_SHORT).show();
    }
}