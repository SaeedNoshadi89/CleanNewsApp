package com.sn.cleannewsapp.data.cache

import androidx.room.TypeConverter
import com.sn.cleannewsapp.data.entities.Source

class Converter {

    @TypeConverter
    fun fromSource(source: Source) = source.name
    @TypeConverter
    fun toSource(name: String) = Source(name, name)
}