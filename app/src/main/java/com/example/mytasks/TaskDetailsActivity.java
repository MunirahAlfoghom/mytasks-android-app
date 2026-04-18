package com.example.mytasks;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailsActivity extends AppCompatActivity implements DeleteTaskDialogFragment.DeleteTaskListener {

    TextView tvDetailsTitle, tvDetailsDescription, tvDetailsDate, tvDetailsStatus;
    Button btnDeleteTask;

    int taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        tvDetailsTitle = findViewById(R.id.tvDetailsTitle);
        tvDetailsDescription = findViewById(R.id.tvDetailsDescription);
        tvDetailsDate = findViewById(R.id.tvDetailsDate);
        tvDetailsStatus = findViewById(R.id.tvDetailsStatus);
        btnDeleteTask = findViewById(R.id.btnDeleteTask);

        taskId = getIntent().getIntExtra("id", -1);
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String date = getIntent().getStringExtra("date");
        String status = getIntent().getStringExtra("status");

        tvDetailsTitle.setText(title);
        tvDetailsDescription.setText(description);
        tvDetailsDate.setText(date);
        tvDetailsStatus.setText(status);

        btnDeleteTask.setOnClickListener(view -> {
            DeleteTaskDialogFragment dialogFragment = new DeleteTaskDialogFragment(taskId, TaskDetailsActivity.this);
            dialogFragment.show(getSupportFragmentManager(), "deleteDialog");
        });
    }

    @Override
    public void onTaskDeleted() {
        finish();
    }
}