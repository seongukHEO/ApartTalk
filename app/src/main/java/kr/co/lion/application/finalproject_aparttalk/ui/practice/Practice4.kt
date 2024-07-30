package kr.co.lion.application.finalproject_aparttalk.ui.practice

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fun main() = runBlocking<Unit> {
//    launch(Dispatchers.Unconfined + CoroutineName("coroutine#2")) {
//        println("launch 코루틴 실행 스레드: ${Thread.currentThread().name} ${coroutineContext[CoroutineName]?.name}")
//    }
//}

//fun main() = runBlocking<Unit>(Dispatchers.IO) {
//    println("runBlocking 코루틴 실행 스레드: ${Thread.currentThread().name}") // runBlocking 코루틴이 실행되는 스레드 출력
//    launch(Dispatchers.Unconfined) { // Dispatchers.Unconfined를 사용해 실행되는 코루틴
//        println("launch 코루틴 실행 스레드: ${Thread.currentThread().name}") // launch 코루틴이 실행되는 스레드 출력
//    }
//}

//fun main() = runBlocking<Unit>(Dispatchers.IO) {
//    println("runBlocking 코루틴 실행 스레드: ${Thread.currentThread().name}") // runBlocking 코루틴이 실행되는 스레드 출력
//    launch { // Dispatchers.Unconfined를 사용해 실행되는 코루틴
//        println("launch 코루틴 실행 스레드: ${Thread.currentThread().name}") // launch 코루틴이 실행되는 스레드 출력
//    }
//}

//fun main() = runBlocking<Unit> {
//    println("작업1")
//    launch(Dispatchers.Unconfined) { // Dispatchers.Unconfined를 사용해 실행되는 코루틴
//        println("작업2")
//    }
//    println("작업3")
//}

fun main() = runBlocking<Unit> {
    launch(Dispatchers.Unconfined) {
        println("일시 중단 전 실행 스레드: ${Thread.currentThread().name}")
        delay(100L)
        println("일시 중단 후 실행 스레드: ${Thread.currentThread().name}")
    }
}