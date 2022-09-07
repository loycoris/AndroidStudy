package com.android.kotlincoroutine.CoroutineFlow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.plus
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CoroutineTest01 {
    @Test
    fun `test flow builder`() = runBlocking {
        flowOf("one", "two", "three")
            .onEach { delay(1000) }
            .collect { value -> println(value) }
        (1..5).asFlow().collect { value ->
            println(value)
        }
    }

    fun simpleFlow() = flow<Int> {
        for (i in 1..3) {
            delay(100)
            emit(i)
            println("Emitting $i at ${System.currentTimeMillis()}")
        }
    }

    @Test
    fun `test emit time`() = runBlocking {
//        simpleFlow().collect { println(it) }

        flowOf("one", "two", "three")
            .onEach {
                delay(100)
                println("Emitting $it at ${System.currentTimeMillis()}")
            }
            .collect { value -> println(value) }
    }
}