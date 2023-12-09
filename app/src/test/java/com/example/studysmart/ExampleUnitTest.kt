package com.example.studysmart

import com.example.studysmart.utils.toHours
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val milliseconds = 7200000L
        val expectedHours = 2.0f
        val result = milliseconds.toHours()
        assertEquals(expectedHours, result, 0.01f)
    }
}