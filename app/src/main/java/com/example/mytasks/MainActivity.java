package com.example.mytasks;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewTasks;
    EditText etSearch;
    Button btnSearch, btnShowAll, btnAddTask;

    DatabaseHelper databaseHelper;
    ArrayList<Task> taskList;
    TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        btnShowAll = findViewById(R.id.btnShowAll);
        btnAddTask = findViewById(R.id.btnAddTask);

        databaseHelper = new DatabaseHelper(this);

        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));

        loadAllTasks();

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = etSearch.getText().toString().trim();

                if (TextUtils.isEmpty(keyword)) {
                    Toast.makeText(MainActivity.this, "Enter task title to search", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayList<Task> searchResults = databaseHelper.searchTasks(keyword);
                    taskAdapter.setTaskList(searchResults);
                    Toast.makeText(MainActivity.this, "Search completed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAllTasks();
                Toast.makeText(MainActivity.this, "All tasks loaded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAllTasks();
    }

    private void loadAllTasks() {
        taskList = databaseHelper.getAllTasks();
        taskAdapter = new TaskAdapter(this, taskList);
        recyclerViewTasks.setAdapter(taskAdapter);
    }
}