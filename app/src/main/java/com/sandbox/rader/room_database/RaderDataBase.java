package com.sandbox.rader.room_database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sandbox.rader.model.User;
import com.sandbox.rader.room_database.dao.UserDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
        User.class,
}, version = 1)
public abstract class RaderDataBase extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile RaderDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

   public static RaderDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RaderDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RaderDataBase.class, "rader_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

