package kr.co.lion.application.finalproject_aparttalk.ui.practice

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

//fun main() = runBlocking<Unit> {
//    println("[${Thread.currentThread().name}] runBlocking 블록 실행")
//    withContext(Dispatchers.IO){
//        println("[${Thread.currentThread().name}] withContext 블록 실행")
//    }
//}

//
//fun main() = runBlocking<Unit> {
//    println("[${Thread.currentThread().name}] runBlocking 블록 실행")
//    async(Dispatchers.IO) {
//        println("[${Thread.currentThread().name}] async 블록 실행")
//    }.await()
//}
//

//3
//
//fun getElapsedTimeInMillis(startTime: Long): Long {
//    val endTime = System.currentTimeMillis()
//    return endTime - startTime
//}

//suspend fun main() {
//    val startTime = System.currentTimeMillis()
//    val helloString = withContext(Dispatchers.IO) {
//        delay(1000L)
//        return@withContext "Hello"
//    }
//
//    val worldString = withContext(Dispatchers.IO) {
//        delay(1000L)
//        return@withContext "World"
//    }
//
//    println("[${getElapsedTimeInMillis(startTime)}] $helloString $worldString")
//}
//
//
//fun main() = runBlocking {
//    val startTime = System.currentTimeMillis()
//    val helloDeferred = async(Dispatchers.IO) {
//        delay(1000L)
//        "Hello"
//    }
//
//    val worldDeferred = async(Dispatchers.IO) {
//        delay(1000L)
//        "World"
//    }
//
//    val results = awaitAll(helloDeferred, worldDeferred)
//
//    println("[${getElapsedTimeInMillis(startTime)}] ${results[0]} ${results[1]}")
//}


//private val myDispatcher1 = newSingleThreadContext("MyThread1")
//private val myDispatcher2 = newSingleThreadContext("MyThread2")
//
//suspend fun main() {
//    println("[${Thread.currentThread().name}] 코루틴 실행")
//
//    withContext(myDispatcher1) {
//        println("[${Thread.currentThread().name}] 코루틴 실행")
//        withContext(myDispatcher2) {
//            println("[${Thread.currentThread().name}] 코루틴 실행")
//        }
//        println("[${Thread.currentThread().name}] 코루틴 실행")
//    }
//
//    println("[${Thread.currentThread().name}] 코루틴 실행")
//}

//1
//fun main(){
//    val coroutineScope = CoroutineScope(Dispatchers.IO)
//    coroutineScope.launch {
//        delay(100L)
//        println("[${Thread.currentThread().name}] 코루틴 실행 완료")
//    }
//    Thread.sleep(1000L)
//}


//2

@OptIn(ExperimentalStdlibApi::class)
fun main(){
    val newScope = CoroutineScope(CoroutineName("MyCoroutine") + Dispatchers.IO)

    newScope.launch(CoroutineName("LaunchCoroutine")) {
        //LaunchCoroutine의 실행 환경을 CoroutineScope를 통해 접근
        this.coroutineContext

        this.launch {
            //CoroutineScope로 부터 LaunchCoroutine의 실행환경을 제공받아 코루틴 실행
        }
        println(this.coroutineContext[CoroutineName])
        println(this.coroutineContext[CoroutineDispatcher])

        val launchJob = this.coroutineContext[Job]
        val newScopeJob = newScope.coroutineContext[Job]
        println("launchJob?.parent === newScopeJob >> ${launchJob?.parent === newScopeJob}")
    }
    Thread.sleep(1000L)
}



//3
//fun main() = runBlocking<Unit> {
//    launch(CoroutineName("Coroutine1")) {
//        launch(CoroutineName("Coroutine3")) {
//            println("[${Thread.currentThread().name}] 코루틴 실행")
//        }
//        launch(CoroutineName("Coroutine4")) {
//            println("[${Thread.currentThread().name}] 코루틴 실행")
//        }
//    }
//    launch(CoroutineName("Coroutine2")) {
//        println("[${Thread.currentThread().name}] 코루틴 실행")
//    }
//}


//4
//fun main() = runBlocking<Unit> {
//    launch(CoroutineName("Coroutine1")) {
//        launch(CoroutineName("Coroutine3")) {
//            println("[${Thread.currentThread().name}] 코루틴 실행")
//        }
//        CoroutineScope(Dispatchers.IO).launch(CoroutineName("Coroutine4")){
//            println("[${Thread.currentThread().name}] 코루틴 실행")
//        }
//    }
//    launch(CoroutineName("Coroutine2")) {
//        println("[${Thread.currentThread().name}] 코루틴 실행")
//    }
//}
//
//class CustomCoroutineScope() : CoroutineScope {
//    override val coroutineContext: CoroutineContext =
//        Job() + newSingleThreadContext("CustomScopeThread")
//
//}
//
//
//fun main(){
//    val coroutineScope = CustomCoroutineScope()  //CustomCoroutineScope 인스턴스화
//    coroutineScope.launch {
//        delay(100L)
//        println("[${Thread.currentThread().name}] 코루틴 실행완료")
//    }
//    Thread.sleep(1000L)
//}
//














