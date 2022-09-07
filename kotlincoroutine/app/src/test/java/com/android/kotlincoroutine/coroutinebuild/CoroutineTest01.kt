package com.android.kotlincoroutine.coroutinebuild

import kotlinx.coroutines.*
import org.junit.Test
import kotlin.system.measureTimeMillis

class CoroutineTest01 {
    @Test
    fun `test coroutine builder`() = runBlocking {
        val job1 = launch {
            delay(200)
            println("Job1 finished.")
        }

        val job2 = async {
            delay(200)
            println("Job2 finished.")
            "job2 result"
        }

        println(job2.await())
    }

    @Test
    fun `test coroutine join`() = runBlocking {
        val job1 = launch {
            delay(2000)
            println("One")
        }
        job1.join()

        val job2 = launch {
            delay(200)
            println("Two")
        }

        val job3 = launch {
            delay(200)
            println("Three")
        }
    }

    @Test
    fun `test coroutine async`() = runBlocking {
        val job1 = async {
            delay(2000)
            println("Job1 finished.")
        }
        job1.join()

        val job2 = async {
            delay(200)
            println("Job2 finished.")
        }

        val job3 = async {
            delay(200)
            println("Job3 finished.")
        }
    }

    @Test
    fun `test async`() = runBlocking {
        val time = measureTimeMillis {
            val one = doOne()
            val two = doTwo()
            println("The result:${one + two}")
        }
        println("Completed in $time ms")
    }

    @Test
    fun `test combine async`() = runBlocking {
        val time = measureTimeMillis {
            val one = async { doOne() }
            val two = async { doTwo() }
            println("The result:${one.await() + two.await()}")
        }
        println("Completed in $time ms")
    }


    private suspend fun doOne(): Int {
        delay(1000)
        return 14
    }

    private suspend fun doTwo(): Int {
        delay(1000)
        return 25
    }

    @Test
    fun `test test mode`() = runBlocking {
        val job1 = launch(start = CoroutineStart.DEFAULT) {
            delay(10000)
            println("job1 finished")
        }
        delay(1000)
//        cancel()
    }

    //作用域构建器
    @Test
    fun `test coroutine scope`() = runBlocking {
        coroutineScope {
            val job1 = launch {
                delay(200)
                println("Job1 finished.")
            }

            val job2 = async {
                delay(200)
                println("Job2 finished.")
                "job2 result"
            }
        }
    }

    @Test
    fun `test supervisor scope`() = runBlocking {
        supervisorScope {

        }
    }
}