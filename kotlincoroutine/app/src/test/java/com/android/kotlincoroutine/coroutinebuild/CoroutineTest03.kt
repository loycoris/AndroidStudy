package com.android.kotlincoroutine.coroutinebuild

import kotlinx.coroutines.*
import org.junit.Test

class CoroutineTest03 {
    @Test
    fun `test release resources`() = runBlocking {
        val job = launch {
            try {
                repeat(5) { i ->
                    println("job:I'm sleeping $i ...")
                    delay(500)
                }
            } finally {
                println("job:I'm running finally")
            }
        }
        delay(1300)
        println("main:I'm tired of waiting!")
        job.cancelAndJoin()
        println("main:Now I can quit")
    }

    @Test
    fun `test use function`() = runBlocking {

    }
}