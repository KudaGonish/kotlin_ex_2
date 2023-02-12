package com.kudagonish.data.local

import androidx.room.*
import com.kudagonish.data.local.model.HistoryEntity

@Dao
interface HistoryDao{
    @Query("Select * from History")
    suspend fun all(): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: HistoryEntity)

    @Update
    suspend fun update(entity: HistoryEntity)

    @Delete
    suspend fun delete(entity: HistoryEntity)

}