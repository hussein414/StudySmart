package com.example.studysmart.data.db.type

import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverter

class ColorListConverter {

    @TypeConverter
    fun fromColorList(colorList: List<Int>): String =
        colorList.joinToString("") { it.toString() }


    @TypeConverter
    fun toColorList(colorListString: String): List<Int> =
        colorListString.split(",").map { it.toInt() }
}

