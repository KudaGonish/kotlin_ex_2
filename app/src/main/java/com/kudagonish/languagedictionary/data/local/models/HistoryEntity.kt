package com.kudagonish.languagedictionary.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kudagonish.languagedictionary.AppState
import com.kudagonish.languagedictionary.DataModel
import org.koin.core.definition.indexKey

@Entity(
    indices = [Index(value = ["word"], unique = true)], tableName = "History"

)
data class HistoryEntity(
    @PrimaryKey @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "description") val description: String? = null
) {
    fun toDomainModel() = DataModel(
        text = word, null
    )
}

fun AppState.toEntity() = when (this) {
    is AppState.Success -> {
        val searchResult = this.data
        if (searchResult.isEmpty()) {
            null
        } else {
            HistoryEntity(searchResult.first().text.orEmpty(), null)
        }
    }
    else -> null
}

