package com.androiddevs.mvvmnewsapp.data.local.room

import androidx.room.TypeConverter
import com.androiddevs.mvvmnewsapp.data.remote.response.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source) = source.name

    @TypeConverter
    fun toSource(name: String) = Source(name, name)

}