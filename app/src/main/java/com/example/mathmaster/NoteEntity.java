package com.example.mathmaster;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Rajat Jain
 */

//basically table name
@Entity(tableName = "notes")
public class NoteEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String mId;

    @NonNull
    @ColumnInfo(name = "note")
    private int mNote;

    public NoteEntity(@NonNull String id, @NonNull int note) {
        mId = id;
        mNote = note;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @NonNull
    public int getNote() {
        return mNote;
    }

    @Override
    public String toString() {
        return "NoteEntity{" + "mNote='" + mNote + '\'' + '}';
    }
}
