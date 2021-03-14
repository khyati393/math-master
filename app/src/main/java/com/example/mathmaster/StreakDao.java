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
public interface StreakDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(StreakEntity streakEntity);

    // @Query("SELECT * FROM notes")
    // @Query("SELECT * FROM notes where streakCount>0 ORDER BY streakCount DESC ")
    @Query("SELECT * FROM streaks ORDER BY streakCount DESC")
    List<StreakEntity> getAllStreaks();

}
