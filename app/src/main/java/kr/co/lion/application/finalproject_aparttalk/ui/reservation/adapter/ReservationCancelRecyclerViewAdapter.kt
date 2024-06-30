package kr.co.lion.application.finalproject_aparttalk.ui.reservation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowReservationCancelItemBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowReservationItemBinding
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel
import kr.co.lion.application.finalproject_aparttalk.ui.reservation.ReservationViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.reservation.ReserveActivity
import kr.co.lion.application.finalproject_aparttalk.util.ReserveFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.setImage

class ReservationCancelRecyclerViewAdapter: ListAdapter<FacilityResModel, ReservationCancelRecyclerViewAdapter.ReservationCancelViewHolder>(FacilityResModelDiffCallback()) {

    inner class ReservationCancelViewHolder(val binding: RowReservationCancelItemBinding) : RecyclerView.ViewHolder(binding.root){
            fun bind(item:FacilityResModel){
                binding.apply {
                    root.context.setImage(reservationCancelImageView, item.imageRes)
                    reservationCancelTextViewDate.text = item.reservationDate
                    textViewReservationCancelLabelEtc.text = if (item.reservationState == false) "예약취소" else ""
                    reservationCancelTextViewPrice.text = item.usePrice
                    reservationCancelTextViewTime.text = item.useTime
                    reservationCancelTextViewFacility.text = item.titleText

                }
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReservationCancelViewHolder {
        val binding = RowReservationCancelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReservationCancelViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ReservationCancelViewHolder, position: Int
    ) {
        holder.bind(currentList[position])
    }

    class FacilityResModelDiffCallback : DiffUtil.ItemCallback<FacilityResModel>() {
        override fun areItemsTheSame(oldItem: FacilityResModel, newItem: FacilityResModel): Boolean {
            return oldItem.userUid == newItem.userUid
        }

        override fun areContentsTheSame(oldItem: FacilityResModel, newItem: FacilityResModel): Boolean {
            return oldItem == newItem
        }
    }
}