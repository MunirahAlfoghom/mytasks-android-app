package com.example.mytasks;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DeleteTaskDialogFragment extends DialogFragment {

    public interface DeleteTaskListener {
        void onTaskDeleted();
    }

    private int taskId;
    private DeleteTaskListener listener;

    public DeleteTaskDialogFragment(int taskId, DeleteTaskListener listener) {
        this.taskId = taskId;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        builder.setTitle("Delete Task")
                .setMessage("Are you sure you want to delete this task?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
                        boolean deleted = databaseHelper.deleteTask(taskId);

                        if (deleted) {
                            Toast.makeText(getActivity(), "Task deleted successfully", Toast.LENGTH_SHORT).show();
                            if (listener != null) {
                                listener.onTaskDeleted();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Failed to delete task", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null);

        return builder.create();
    }
}