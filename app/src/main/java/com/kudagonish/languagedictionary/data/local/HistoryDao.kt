package com.kudagonish.languagedictionary.data.local

import androidx.room.*
import com.kudagonish.languagedictionary.data.local.models.HistoryEntity

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