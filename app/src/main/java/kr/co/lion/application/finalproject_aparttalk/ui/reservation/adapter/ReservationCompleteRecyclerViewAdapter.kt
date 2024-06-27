package kr.co.lion.application.finalproject_aparttalk.ui.reservation.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowMylikeTabLikeBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowParkingReserveBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowReservationItemBinding
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.ui.mywrite.adapter.MyLikeRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.reservation.ReservationViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.reservation.ReserveActivity
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.ReserveFragmentName

class ReservationCompleteRecyclerViewAdapter : ListAdapter<FacilityResModel, ReservationCompleteRecyclerViewAdapter.ReservationCompleteViewHolder>(FacilityResModelDiffCallback()) {

    inner class ReservationCompleteViewHolder(val binding: RowReservationItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: FacilityResModel){
            binding.apply {
                reservationTextViewDate.text = item.reservationDate
                textViewReservationLabelEtc.text = if (item.reservationState) "예약완료" else "예약취소"
                reservationTextViewPrice.text = item.usePrice
                reservationTextViewTime.text = item.useTime
                reservationTextViewFacility.text = item.titleText
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReservationCompleteViewHolder {
        return ReservationCompleteViewHolder(RowReservationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: ReservationCompleteViewHolder, position: Int
    ) {
       holder.bind(currentList[position])
    }

    class FacilityResModelDiffCallback : DiffUtil.ItemCallback<FacilityResModel>() {
        override fun areItemsTheSame(oldItem: FacilityResModel, newItem: FacilityResModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FacilityResModel, newItem: FacilityResModel): Boolean {
            return oldItem == newItem
        }
    }
}