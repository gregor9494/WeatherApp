package com.example.grzesiek.myapplication.data

import com.example.grzesiek.myapplication.UnitTest
import com.example.grzesiek.myapplication.data.models.error.NoLocationFoundException
import com.example.grzesiek.myapplication.data.models.error.WeatherUpdateError
import com.example.grzesiek.myapplication.data.repository.WeatherErrorParser
import org.amshove.kluent.mock
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test
import retrofit2.HttpException

/**
 * Created by ggaworowski on 30.03.2019.
 */
class WeatherErrorParserTest : UnitTest() {
    @Test
    fun `test error type when network exception`() {
        val exception = mock(HttpException::class)
        val error = WeatherErrorParser.parse(exception)
        error shouldBeInstanceOf WeatherUpdateError.NetworkError::class.java
    }

    @Test
    fun `test error type when permission exception`() {
        val exception = mock(SecurityException::class)
        val error = WeatherErrorParser.parse(exception)
        error shouldBeInstanceOf WeatherUpdateError.PermissionError::class.java
    }

    @Test
    fun `test error type when location exception`() {
        val exception = mock(NoLocationFoundException::class)
        val error = WeatherErrorParser.parse(exception)
        error shouldBeInstanceOf WeatherUpdateError.LocationError::class.java
    }

    @Test
    fun `test error type when unknown exception`() {
        val exception = mock(Exception::class)
        val error = WeatherErrorParser.parse(exception)
        error shouldBeInstanceOf WeatherUpdateError.UnknownError::class.java
    }
}