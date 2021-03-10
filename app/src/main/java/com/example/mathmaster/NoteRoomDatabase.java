package com.example.mathmaster;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by Rajat Jain.
 */
@Database(entities = NoteEntity.class, version = 1)
public abstract class NoteRoomDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();

    private static volatile NoteRoomDatabase mNoteRoomDatabaseInstance;

    static NoteRoomDatabase getDatabase(final Context context) {
        if (mNoteRoomDatabaseInstance == null) {
            synchronized (NoteRoomDatabase.class) {
                if (mNoteRoomDatabaseInstance == null) {
                    mNoteRoomDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(), NoteRoomDatabase.class, "note_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return mNoteRoomDatabaseInstance;
    }

}
