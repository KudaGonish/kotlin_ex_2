package com.kudagonish.models

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("text")
    val text: String?,
    @SerializedName("meanings")
    val meaning: List<Meaning>?)


/** Проверка **/
data class Meaning(
    @SerializedName("translation")
    val translation: Translation?,
    @SerializedName("imageUrl")
    val imageUrl: String
)

data class Translation(
    @SerializedName("translation")
    val translation: String?
)