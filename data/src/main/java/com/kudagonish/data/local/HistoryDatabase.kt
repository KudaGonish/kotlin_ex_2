package com.kudagonish.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kudagonish.data.local.model.HistoryEntity


@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun  historyDao() : HistoryDao
}