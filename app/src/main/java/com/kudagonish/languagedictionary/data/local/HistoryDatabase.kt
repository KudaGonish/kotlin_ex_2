package com.kudagonish.languagedictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kudagonish.languagedictionary.data.local.models.HistoryEntity


@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun  historyDao() : HistoryDao
}