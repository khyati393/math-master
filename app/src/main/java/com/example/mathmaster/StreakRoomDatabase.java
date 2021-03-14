package com.example.mathmaster;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by Rajat Jain.
 */
@Database(entities = StreakEntity.class, version = 1)
public abstract class StreakRoomDatabase extends RoomDatabase {

    public abstract StreakDao streakDao();

    private static volatile StreakRoomDatabase mStreakRoomDatabaseInstance;

    static StreakRoomDatabase getDatabase(final Context context) {
        if (mStreakRoomDatabaseInstance == null) {
            synchronized (StreakRoomDatabase.class) {
                if (mStreakRoomDatabaseInstance == null) {
                    mStreakRoomDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(), StreakRoomDatabase.class, "streak_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return mStreakRoomDatabaseInstance;
    }

}
