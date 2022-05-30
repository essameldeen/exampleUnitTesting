package com.example.exampleunittesting.data.local

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

  @TypeConverter
  fun fromTimestamp(value: Long?): Date? {
    return value?.let { Date(it) }
  }

  @TypeConverter
  fun dateToTimestamp(date: Date?): Long? {
    return date?.time
  }
}