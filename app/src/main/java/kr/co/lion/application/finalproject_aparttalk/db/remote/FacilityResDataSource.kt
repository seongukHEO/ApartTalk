package kr.co.lion.application.finalproject_aparttalk.db.remote

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel

class FacilityResDataSource {

    private val db = Firebase.firestore

    //예약 정보를 저장한다
    suspend fun insertResInfo(facilityResModel: FacilityResModel){
        val job1 = CoroutineScope(Dispatchers.IO).launch {
            val collectionReference = db.collection("FacilityResInfo")
            collectionReference.add(facilityResModel)
        }
        job1.join()
    }

    //userUid 값으로 정보를 가져온다
    suspend fun getFacilityResInfo(userUid:String, reservationState:Boolean) : List<FacilityResModel> {
        return try {
            val querySnapshot = db.collection("FacilityResInfo")
                .whereEqualTo("userUid", userUid)
                .whereEqualTo("reservationState", reservationState)
                .get().await()
            querySnapshot.toObjects(FacilityResModel::class.java)
        }catch (e:Exception){
            emptyList()
        }
    }

    //시간순으로 데이터 받아오기
    suspend fun getTime():List<FacilityResModel> {
        return try {
            val querySnapshot = db.collection("FacilityResInfo")
                .orderBy("reserveTime", Query.Direction.DESCENDING)
                .get().await()
            querySnapshot.toObjects(FacilityResModel::class.java)
        }catch (e:Exception){
            emptyList()
        }
    }

}