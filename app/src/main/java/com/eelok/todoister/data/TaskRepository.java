package com.eelok.todoister.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.eelok.todoister.entity.Task;
import com.eelok.todoister.util.TaskRoomDatabase;

import java.util.List;

public class TaskRepository {

    private final TaskDao taskDao;
    private final LiveData<List<Task>> allTasks;

    public TaskRepository(Application application) {
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        taskDao = db.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    public LiveData<List<Task>> getAllData(){
        return allTasks;
    }

    public LiveData<Task> getTask(long taskId){
        return taskDao.getTask(taskId);
    }

    public void createNewTask(Task task){
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.insert(task);
        });
    }

    public void deleteTask(Task task){
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.deleteTask(task);
        });
    }

    public void updateTask(Task task){
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.updateTask(task);
        });
    }
}
