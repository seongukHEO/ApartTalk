package kr.co.lion.application.finalproject_aparttalk.ui.reservation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentReservationConfirmBinding
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel
import kr.co.lion.application.finalproject_aparttalk.model.UserModel
import kr.co.lion.application.finalproject_aparttalk.ui.info.UserViewModel
import kr.co.lion.application.finalproject_aparttalk.util.ReserveFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.setImage


class ReservationConfirmFragment : Fragment() {

    private lateinit var fragmentReservationConfirmBinding: FragmentReservationConfirmBinding
    private lateinit var reserveActivity: ReserveActivity

    val viewModel : ReservationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentReservationConfirmBinding =
            FragmentReservationConfirmBinding.inflate(inflater, container, false)
        reserveActivity = activity as ReserveActivity

        settingToolbar()
        settingButton()
        bindReservationData()

        return fragmentReservationConfirmBinding.root
    }

    private fun settingToolbar() {
        fragmentReservationConfirmBinding.apply {
            reservationConfirmToolbar.apply {
                textViewReservationConfirmToolbarTitle.text = "예약내역"
            }
        }
    }

    private fun settingButton() {
        fragmentReservationConfirmBinding.apply {
            reservationConfirmButton.setOnClickListener {
                reserveActivity.removeFragment(ReserveFragmentName.RESERVATION_CONFIRM_FRAGMENT)
            }
            reserveCancelButton.setOnClickListener {
                //예약 취소
                val facilityResIdx = arguments?.getInt("facilityResIdx", 0)
                require(facilityResIdx != null)
                val bundle = Bundle()
                bundle.putInt("facilityResIdx", facilityResIdx)
                reserveActivity.replaceFragment(ReserveFragmentName.RESERVATION_CANCEL_COMPLETE_FRAGMENT, true, true, bundle)
            }
        }
    }


    private fun bindReservationData() {
        val facilityResIdx = arguments?.getInt("facilityResIdx", 0)
       // Log.d("test1234", "${facilityResIdx}")
        viewLifecycleOwner.lifecycleScope.launch {
            val facilityInfo = viewModel.getDataByIdx(facilityResIdx?:-1)

            require(facilityInfo != null)

            fragmentReservationConfirmBinding.apply {
                root.context.setImage(reservationConfirmImageView, facilityInfo.imageRes)
                reservationConfirmTextViewName.text = facilityInfo.userName ?: "이름 없음"
                reservationConfirmTextViewPhoneNumber.text = facilityInfo.userNumber ?: "전화번호 없음"
                reservationConfirmTextViewDate.text = facilityInfo.reservationDate
                reservationConfirmTextViewFacility.text = facilityInfo.titleText
                reservationConfirmTextViewReservedDate.text = facilityInfo.reservationDate
                reservationConfirmTextViewTime.text = facilityInfo.useTime
                reservationConfirmTextViewPrice.text = facilityInfo.usePrice
            }

        }
    }
}