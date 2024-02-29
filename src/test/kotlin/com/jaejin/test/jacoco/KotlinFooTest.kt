package com.jaejin.test.jacoco

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class KotlinFooTest {

    private val kotlinFoo = KotlinFoo()

    @Test
    fun `partially covered hello method test`() {
        val actual = kotlinFoo.hello("펭")
        assertEquals(actual, "하")
    }

    @Test
    fun `partially covered hello method test2`() {
        val actual = kotlinFoo.hello("Hello")
        assertEquals(actual, "world")
    }

    @Test
    fun `partially covered hello method test3`() {
        val actual = kotlinFoo.hello("1111")
        assertEquals(actual, "no one")
    }

}