package com.example.mathmaster;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Rajat Jain
 */

//basically table
@Entity(tableName = "streaks")
public class StreakEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String mId;

    @NonNull
    @ColumnInfo(name = "streakCount")
    private int mStreak;

    @NonNull
    @ColumnInfo(name = "computationType")
    private String mComputationType;

    @NonNull
    @ColumnInfo(name = "StreakRecordedDate")
    private long mStreakRecordedDateInMillis;

    public StreakEntity(@NonNull String id, int streak, @NonNull String computationType, long streakRecordedDateInMillis) {
        mId = id;
        mStreak = streak;
        mComputationType = computationType;
        mStreakRecordedDateInMillis = streakRecordedDateInMillis;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @NonNull
    public int getStreak() {
        return mStreak;
    }

    @NonNull
    public String getComputationType() {
        return mComputationType;
    }

    public long getStreakRecordedDateInMillis() {
        return mStreakRecordedDateInMillis;
    }
}
