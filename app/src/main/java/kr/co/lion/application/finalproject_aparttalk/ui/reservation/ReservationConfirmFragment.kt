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
        //settingButton()
        //observeSelectedReservation()
        //observeReservationCompletion()
        bindReservationData()

        return fragmentReservationConfirmBinding.root
    }

    private fun settingToolbar() {
        fragmentReservationConfirmBinding.apply {
            reservationConfirmToolbar.apply {
                textViewReservationConfirmToolbarTitle.text = "예약내역"
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    reserveActivity.removeFragment(ReserveFragmentName.RESERVATION_CONFIRM_FRAGMENT)
                }
            }
        }
    }

//    private fun settingButton() {
//        fragmentReservationConfirmBinding.apply {
//            reservationConfirmButton.setOnClickListener {
//                reservationViewModel.resetReservationCompleted()
//                reserveActivity.finish()
//            }
//        }
//    }

//    private fun observeSelectedReservation() {
//        reservationViewModel.selectedReservation.observe(viewLifecycleOwner, Observer { reservation ->
//            if (reservation != null) {
//                bindReservationData(reservation)
//            }
//        })
//    }

//    private fun observeReservationCompletion() {
//        reservationViewModel.isReservationCompleted.observe(viewLifecycleOwner, Observer { isCompleted ->
//            if (isCompleted == true) {
//                fragmentReservationConfirmBinding.reservationConfirmButton.isEnabled = false
//            }
//        })
//    }

    private fun bindReservationData() {
        val facilityResIdx = arguments?.getInt("facilityResIdx", 0)
       // Log.d("test1234", "${facilityResIdx}")
        viewLifecycleOwner.lifecycleScope.launch {
            val facilityInfo = viewModel.getDataByIdx(facilityResIdx?:-1)

        }



    }


//        fragmentReservationConfirmBinding.apply {
//            reservationConfirmTextViewName.text = reservation.userName ?: "이름 없음"
//            reservationConfirmTextViewPhoneNumber.text = reservation.userNumber ?: "전화번호 없음"
//            reservationConfirmTextViewDate.text = reservation.reservationDate
//            reservationConfirmTextViewFacility.text = reservation.titleText
//            reservationConfirmTextViewReservedDate.text = reservation.reservationDate
//            reservationConfirmTextViewTime.text = reservation.useTime
//            reservationConfirmTextViewPrice.text = reservation.usePrice
//        }
//    }
}