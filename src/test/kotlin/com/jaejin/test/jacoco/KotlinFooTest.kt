package com.jaejin.test.jacoco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class KotlinFooTest {

    private val kotlinFoo = KotlinFoo()

    @Test
    fun `partially covered hello method test`() {
        val actual = kotlinFoo.hello("Hello")
        //kotlinFoo.callMe()
        assertEquals(actual, "world")
    }
/*
    @Test
    fun `test hello method with name "펭"`() {
        val actual = kotlinFoo.hello("펭")
        kotlinFoo.callMe()
        assertEquals(actual, "하")
    }*/
/*
    @Test
    fun `test hello method with name longer than 5 characters`() {
        val actual = kotlinFoo.hello("HelloWorld")
        kotlinFoo.callMe()
        assertEquals(actual, "TOO LONG")
    }

    @Test
    fun `test hello method with other names`() {
        val actual = kotlinFoo.hello("Other")
        kotlinFoo.callMe()
        assertEquals(actual, "no one")
    }*/

}