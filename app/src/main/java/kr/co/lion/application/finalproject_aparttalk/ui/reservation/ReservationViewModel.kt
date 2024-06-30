package kr.co.lion.application.finalproject_aparttalk.ui.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel
import kr.co.lion.application.finalproject_aparttalk.repository.FacilityResRepository

class ReservationViewModel : ViewModel() {

    private val facilityResRepository = FacilityResRepository()

    private val _facilityGetList = MutableLiveData<List<FacilityResModel>>()
    val facilityGetList: LiveData<List<FacilityResModel>> get() = _facilityGetList



    // 예약 정보를 userUid값으로 가져온다
    suspend fun getFacilityResData(userUid: String, reservationState: Boolean) {
        val facilityInfo = facilityResRepository.getFacilityInfoData(userUid, reservationState)
        val facilityInfoList = mutableListOf<FacilityResModel>()

        facilityInfo.forEach { facilityInfoData ->
            facilityInfoList.add(facilityInfoData)
        }

        // LiveData 값을 메인 스레드에서 업데이트
        withContext(Dispatchers.Main) {
            _facilityGetList.value = facilityInfoList
        }
    }

    //facilityResIdx 값으로 데이터 가져오기
    suspend fun getDataByIdx(facilityResIdx:Int): FacilityResModel? {
        return facilityResRepository.getDataByIdx(facilityResIdx)
    }

    suspend fun updateResState(facilityResIdx: Int){
        facilityResRepository.updateResState(facilityResIdx)
    }
}
