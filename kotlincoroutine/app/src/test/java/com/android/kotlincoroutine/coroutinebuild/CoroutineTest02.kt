package com.android.kotlincoroutine.coroutinebuild

import kotlinx.coroutines.*
import org.junit.Test

class CoroutineTest02 {
    @Test
    fun `test scope cancel`() = runBlocking<Unit> {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            delay(1000)
            println("job1")
        }

        scope.launch {
            delay(1000)
            println("job2")
        }
        delay(2000)
    }

    @Test
    fun `test brother cancel`() = runBlocking<Unit> {
        val scope = CoroutineScope(Dispatchers.Default)
        val job1 = scope.launch {
            delay(1000)
            println("job1")
        }

        val job2 = scope.launch {
            delay(1000)
            println("job2")
        }
        delay(100)
        job1.cancel()
        delay(2000)
    }

    @Test
    fun `test CancellationException cancel`() = runBlocking<Unit> {
        val job1 = GlobalScope.launch {
            delay(1000)
            println("job1")
        }
        delay(100)
        /*job1.cancel()
        job1.join()*/
        job1.cancelAndJoin()
    }

    @Test
    fun `test cancel cpu task by isActive`() = runBlocking<Unit> {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (i < 5 && isActive) {
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job:I'm sleeping ${i++}...")
                    nextPrintTime += 500
                }
            }
        }
        delay(1300)
        println("main:I'm tired of waiting!")
        job.cancelAndJoin()
        println("main:Now I can quit")
    }

    @Test
    fun `test cancel cpu task by ensureActive`() = runBlocking<Unit> {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (i < 5) {
                ensureActive()
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job:I'm sleeping ${i++}...")
                    nextPrintTime += 500
                }
            }
        }
        delay(1300)
        println("main:I'm tired of waiting!")
        job.cancelAndJoin()
        println("main:Now I can quit")
    }

    @Test
    fun `test cancel cpu task by yield`() = runBlocking<Unit> {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (i < 5) {
                yield()
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job:I'm sleeping ${i++}...")
                    nextPrintTime += 500
                }
            }
        }
        delay(1300)
        println("main:I'm tired of waiting!")
        job.cancelAndJoin()
        println("main:Now I can quit")
    }
}