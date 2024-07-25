package kr.co.lion.application.finalproject_aparttalk.ui.practice

import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield


//fun main(){
//    routine()
//}
//
//
//fun routine(){
//    routineA()
//    routineB()
//}
//
//fun routineA(){
//
//}
//
//fun routineB(){
//
//}



//fun main() = runBlocking<Unit> {
//    launch {
//        while (true){
//            println("자식 코루틴에서 작업 실행중")
//            yield()
//        }
//    }
//    while (true){
//        println("부모 코루틴에서 작업 실행중")
//        yield()
//    }
//}

fun getElapsedTimeInMillis(startTime: Long): Long {
    val endTime = System.currentTimeMillis()
    return endTime - startTime
}

//fun main() = runBlocking<Unit> {
//    val startTime = System.currentTimeMillis()
//    repeat(10){repeatTime ->
//        launch {
//            delay(1000L)  //1초 동안 코루틴 일시 중단
//            println("[${getElapsedTimeInMillis(startTime)}] 코루틴 실행 완료")
//        }
//    }
//}

//fun main() = runBlocking<Unit> {
//    val startTime = System.currentTimeMillis()
//    repeat(10){repeatTime ->
//        launch {
//           Thread.sleep(1000L) // 1초 동안 스레드 블로킹
//            println("[${getElapsedTimeInMillis(startTime)}] 코루틴 실행 완료")
//        }
//    }
//}


//fun main() = runBlocking<Unit> {
//    val job = launch {
//        println("1, launch 코루틴 시작")
//        delay(1000L)
//        println("2, launch 코루틴 작업이 완료됐습니다")
//    }
//    println("3, runBlocking 코루틴이 곧 일시 중단 되고 메인 스레드가 양보됩니다")
//    job.join()
//    println("4, runBlocking이 메인 스레드에 분배돼 작업이 다시 재개됩니다")
//}

//
//fun main() = runBlocking<Unit> {
//    val job1 = launch {
//        while (this.isActive){
//            println("작업중")
//        }
//    }
//    delay(100L)
//    job1.cancel()
//}


fun main() = runBlocking<Unit> {
    val job1 = launch {
        while (this.isActive){
            println("작업중")
            yield()  //스레드 양보
        }
    }
    delay(100L)
    job1.cancel()
}




















