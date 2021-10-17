package com.eelok.todoister.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.eelok.todoister.R;
import com.eelok.todoister.entity.Task;
import com.eelok.todoister.util.Utils;
import com.google.android.material.chip.Chip;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<Task> taskList;


    public RecyclerViewAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.todo.setText(task.getTask());
        holder.todayChip.setText(Utils.formatDate(task.getDueDate()));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView todo;
        public AppCompatRadioButton radioButton;
        public Chip todayChip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            todo = itemView.findViewById(R.id.todo_row_todo);
            radioButton = itemView.findViewById(R.id.todo_radio_button);
            todayChip = itemView.findViewById(R.id.todo_row_chip);
        }
    }
}
