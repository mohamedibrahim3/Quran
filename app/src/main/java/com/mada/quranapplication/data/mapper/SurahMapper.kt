package com.mada.quranapplication.data.mapper

import com.mada.quranapplication.data.local.entity.SurahEntity
import com.mada.quranapplication.domain.model.Surah

class SurahMapper {
    fun mapToDomain(entity: SurahEntity): Surah {
        return Surah(
            id = entity.id,
            arabicName = entity.arabicName,
            numberOfVerses = entity.numberOfVerses,
            revelationType = entity.revelationType,
            pageStart = entity.pageStart,
            pageEnd = entity.pageEnd,
            imagePath = entity.imagePath
        )
    }

    fun mapToEntity(domain: Surah): SurahEntity {
        return SurahEntity(
            id = domain.id,
            arabicName = domain.arabicName,
            numberOfVerses = domain.numberOfVerses,
            revelationType = domain.revelationType,
            pageStart = domain.pageStart,
            pageEnd = domain.pageEnd,
            imagePath = domain.imagePath
        )
    }
}
