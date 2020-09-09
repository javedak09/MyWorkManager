package com.example.javedak09.myworkmanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javedak09.myworkmanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static String MESSAGE_STATUS;
    ActivityMainBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);


        Data data = new Data.Builder().putString(WorkManagerSync.TASK_DESC, "The task data passed from MainActivity").build();

        final OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(WorkManagerSync.class).setInputData(data).build();

        findViewById(R.id.btn_Continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManagerSync.getInstance().enqueue(workRequest);
            }
        });


        final TextView textView = findViewById(R.id.txtmsg);
        WorkManagerSync.getInstance().getWorkInfoByIdLiveData(workRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workInfo) {
                        textView.append(workInfo.getState().name() + "\n");

                    }
                });

    }

    public void cmdFetchData(View view) {
        Toast.makeText(this, "I m click", Toast.LENGTH_SHORT).show();
    }

    public void cmdFetchData1() {
        Toast.makeText(this, "I m click", Toast.LENGTH_SHORT).show();
    }
}