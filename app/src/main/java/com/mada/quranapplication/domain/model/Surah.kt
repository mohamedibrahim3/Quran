package com.mada.quranapplication.domain.model


//Domain Model
data class Surah(
    val id: Int,
    val arabicName: String,
    val numberOfVerses: Int,
    val revelationType: String,
    val pageStart: Int,
    val pageEnd: Int,
    val imagePath: String
)
