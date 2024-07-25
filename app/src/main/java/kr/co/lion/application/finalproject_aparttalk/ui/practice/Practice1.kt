package kr.co.lion.application.finalproject_aparttalk.ui.practice

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

//fun main() = runBlocking<Unit>{
//    val networkDeferred: Deferred<String> = async(Dispatchers.IO) {
//        delay(1000L) //네트워크 요청
//        return@async "Dummy Response"  //문자열 반환
//    }
//    val result = networkDeferred.await()  //networkDeferred로부터 결과 값이 반환될 때까지 대기
//
//    println(result)
//}

fun main() = runBlocking<Unit> {
    val result: String = withContext(Dispatchers.IO){
        delay(1000L)
        return@withContext "Dummy Response"
    }
    println(result)
}
