package com.android.kotlincoroutine.coroutineexception

import kotlinx.coroutines.*
import org.junit.Test
import java.lang.ArithmeticException
import java.lang.IllegalStateException
import java.lang.IndexOutOfBoundsException

class CoroutineTest01 {
    @Test
    fun `test coroutine context`() = runBlocking<Unit> {
        launch(Dispatchers.Default + CoroutineName("test")) {
            println("I'm working in thread ${Thread.currentThread().name}")
        }
    }

    @Test
    fun `test coroutine context extend`() = runBlocking<Unit> {
        val scope = CoroutineScope(Job() + Dispatchers.IO + CoroutineName("test"))
        val job = scope.launch {
            println("${coroutineContext[Job]} ${Thread.currentThread().name}")
            val result = async {
                println("${coroutineContext[Job]} ${Thread.currentThread().name}")
                "OK"
            }.await()
        }
        job.join()
    }

    @Test
    fun `test coroutine context extend2`() = runBlocking<Unit> {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception")
        }
        val scope = CoroutineScope(Job() + Dispatchers.Main + coroutineExceptionHandler)

        val job = scope.launch(Dispatchers.IO) {
            println("done")
        }
    }

    @Test
    fun `test exception progaration`() = runBlocking<Unit> {
        val job = GlobalScope.launch {
            try {
                throw IndexOutOfBoundsException()
            } catch (e: Exception) {
                println("Caught IndexOutOfBoundsException")
            }
        }

        val deferred = GlobalScope.async {
            throw ArithmeticException()
        }

        try {
            deferred.await()
        } catch (e: Exception) {
            println("Caught ArithmeticException")
        }
    }

    @Test
    fun `test exception progaration2`() = runBlocking<Unit> {
        val scope = CoroutineScope(Job())
        val job = scope.launch {
            async {
                throw IllegalStateException()
            }
        }
        job.join()
    }

    @Test
    fun `test SupervisorJob`() = runBlocking<Unit> {
        val scope = CoroutineScope(SupervisorJob())
        val job1 = scope.launch {
            delay(100)
            println("Job1 finished")
            throw IllegalStateException()
        }

        val job2 = scope.launch {
            try {
                delay(Long.MAX_VALUE)
            } finally {
                println("Job2 finished")
            }
        }
        delay(200)
        scope.cancel()
        joinAll(job1, job2)
    }

    @Test
    fun `test SupervisorScope`() = runBlocking<Unit> {
        supervisorScope {
            launch {
                delay(100)
                println("Job1 finished")
                throw IllegalStateException()
            }

            try {
                delay(Long.MAX_VALUE)
            } finally {
                println("Job2 finished")
            }
        }
    }
}