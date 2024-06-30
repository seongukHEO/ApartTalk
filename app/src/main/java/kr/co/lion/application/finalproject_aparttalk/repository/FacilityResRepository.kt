package kr.co.lion.application.finalproject_aparttalk.repository

import kr.co.lion.application.finalproject_aparttalk.db.remote.FacilityResDataSource
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel

class FacilityResRepository {

    private var facilityResDataSource = FacilityResDataSource()

    suspend fun getFacilityResSequence() = facilityResDataSource.getResSequence()

    suspend fun updateFacilityResSequence(facilityResSequence:Int) = facilityResDataSource.updateFacilitySequence(facilityResSequence)


    suspend fun insertResData(facilityResModel: FacilityResModel) = facilityResDataSource.insertResInfo(facilityResModel)

    suspend fun getFacilityInfoData(userUid:String, reservationState: Boolean) = facilityResDataSource.getFacilityResInfo(userUid, reservationState)


    suspend fun getDataByIdx(facilityResIdx:Int) = facilityResDataSource.getDataByIdx(facilityResIdx)

    suspend fun updateResState(facilityResIdx: Int) = facilityResDataSource.updateRes(facilityResIdx)


}