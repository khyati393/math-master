package com.example.mathmaster;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * Created by Rajat Jain
 */
//write queries here
@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(NoteEntity note);

    //        @Query("SELECT * FROM notes")
    @Query("SELECT * FROM notes ORDER BY streakCount DESC")
    List<NoteEntity> getAllNotes();

}
