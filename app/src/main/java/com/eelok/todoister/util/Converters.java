package com.eelok.todoister.util;

import androidx.room.TypeConverter;

import com.eelok.todoister.entity.Priority;

import java.util.Date;

public class Converters {

    @TypeConverter
    public static String fromPriorityToString(Priority priority){
        return priority == null ? null : priority.name();
    }

    @TypeConverter
    public static Priority fromStringToPriority(String priority){
        return priority == null ? null : Priority.valueOf(priority);
    }

    @TypeConverter
    public static Long fromDateToTimestamp(Date date){
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Date fromTimestampToDate(Long value){
        return value == null ? null : new Date(value);
    }
}
