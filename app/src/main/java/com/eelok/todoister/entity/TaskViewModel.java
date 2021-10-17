package com.eelok.todoister.entity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.eelok.todoister.data.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    public static TaskRepository repository;
    public final LiveData<List<Task>> allTasks;


    public TaskViewModel(@NonNull Application application) {
        super(application);

        repository = new TaskRepository(application);
        allTasks = repository.getAllData();
    }

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }

    public LiveData<Task> getTask(long taskId){
        return repository.getTask(taskId);
    }

    public void deleteTask(Task task) {
        repository.deleteTask(task);
    }

    public void createNewTask(Task task){
        repository.createNewTask(task);
    }

    public void updateTask(Task task){
        repository.updateTask(task);
    }

}
