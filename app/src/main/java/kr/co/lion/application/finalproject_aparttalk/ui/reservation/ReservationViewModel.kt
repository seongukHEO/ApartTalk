package kr.co.lion.application.finalproject_aparttalk.ui.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel
import kr.co.lion.application.finalproject_aparttalk.repository.FacilityResRepository

class ReservationViewModel : ViewModel() {

    private val facilityResRepository = FacilityResRepository()

    private val _facilityGetList = MutableLiveData<List<FacilityResModel>>()
    val facilityGetList : LiveData<List<FacilityResModel>> = _facilityGetList

    private val _reservations = MutableLiveData<List<FacilityResModel>>()
    val reservations: LiveData<List<FacilityResModel>> get() = _reservations

    private val _selectedReservation = MutableLiveData<FacilityResModel>()
    val selectedReservation: LiveData<FacilityResModel> get() = _selectedReservation

    private val _isReservationCompleted = MutableLiveData<Boolean>()
    val isReservationCompleted: LiveData<Boolean> get() = _isReservationCompleted

    private val firestore = FirebaseFirestore.getInstance()



    //예약 정보를 userUid값으로 가져온다
    suspend fun getFacilityResData(userUid:String, reservationState:Boolean) {
        val facilityInfo = facilityResRepository.getFacilityInfoData(userUid, reservationState)
        val facilityInfoList = mutableListOf<FacilityResModel>()

        facilityInfo.forEach { facilityInfoData ->
            facilityInfoList.add(facilityInfoData)

            _facilityGetList.value = facilityInfoList

        }
    }

    fun setSelectedReservation(reservation: FacilityResModel) {
        _selectedReservation.value = reservation
    }

    fun resetReservationCompleted() {
        _isReservationCompleted.value = false
    }
}