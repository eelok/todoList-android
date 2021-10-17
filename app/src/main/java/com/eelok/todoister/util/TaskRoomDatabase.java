package com.eelok.todoister.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.eelok.todoister.data.TaskDao;
import com.eelok.todoister.entity.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class TaskRoomDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "task_database";

    public abstract TaskDao taskDao();

    private static volatile TaskRoomDatabase INSTANCE;
    public static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TaskRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            TaskRoomDatabase.class,
                            DATABASE_NAME
                    ).addCallback(roomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    public static final RoomDatabase.Callback roomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    databaseWriteExecutor.execute(() -> {
//                        TaskDao taskDao = INSTANCE.taskDao();
//                        taskDao.deleteAll();
//
//                        Task task = new Task(
//                                "Learn android",
//                                Priority.HIGH,
//                                new Date(2021, 10, 16),
//                                new Date(2021, 8, 2),
//                                false);
//
//                        taskDao.insert(task);
                    });
                }
            };
}
