package com.school.mrind.flightsim.DBFiles;

import android.arch.persistence.room.*;
import android.content.Context;

@Database(entities = {User.class}, version  = 2)

public abstract class DB extends RoomDatabase{
    private static DB INSTANCE;
    public abstract UserDao userDao();
    public static DB getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), DB.class, "user-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
