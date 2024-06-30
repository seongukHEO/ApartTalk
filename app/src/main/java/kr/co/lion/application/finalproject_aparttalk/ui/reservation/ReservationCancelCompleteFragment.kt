package kr.co.lion.application.finalproject_aparttalk.ui.reservation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentReservationCancelCompleteBinding
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel
import kr.co.lion.application.finalproject_aparttalk.ui.facility.viewmodel.FacilityResInfoViewmodel
import kr.co.lion.application.finalproject_aparttalk.util.DialogConfirm
import kr.co.lion.application.finalproject_aparttalk.util.ReserveFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.setImage


class ReservationCancelCompleteFragment() : Fragment() {

    lateinit var fragmentReservationCancelCompleteBinding: FragmentReservationCancelCompleteBinding
    lateinit var reserveActivity: ReserveActivity
    val viewModel : ReservationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentReservationCancelCompleteBinding = FragmentReservationCancelCompleteBinding.inflate(inflater)
        reserveActivity = activity as ReserveActivity

        settingToolbar()
        settingButton()
        settingData()
        bindReservationData()

        return fragmentReservationCancelCompleteBinding.root
    }

    fun settingToolbar() {
        fragmentReservationCancelCompleteBinding.apply {
            reservationCancelToolbar.apply {
                textViewReservationCancelToolbarTitle.text = "예약취소"
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전화면으로 돌아가기.
                    reserveActivity.removeFragment(ReserveFragmentName.RESERVATION_CANCEL_COMPLETE_FRAGMENT)
                }
            }
        }
    }

    fun settingData() {
        fragmentReservationCancelCompleteBinding.apply {
            // 드롭다운 설정
            val typeArray = resources.getStringArray(R.array.type_reserve)
            val typeArrayAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner_reserve, typeArray)
            textViewReserveCancelAddType.setAdapter(typeArrayAdapter)
        }
    }

    fun settingButton() {
        fragmentReservationCancelCompleteBinding.apply {
            reservationCancelButton.setOnClickListener {



                // 키보드를 내리는 기능 추가
                val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)

                reserveActivity.replaceFragment(ReserveFragmentName.RESERVATION_FRAGMENT, true, true, null)
            }
        }
    }


    private fun bindReservationData() {
        val facilityResIdx = arguments?.getInt("facilityResIdx")
        require(facilityResIdx != null)
        viewLifecycleOwner.lifecycleScope.launch {
            val data = viewModel.getDataByIdx(facilityResIdx)
            require(data != null)

            fragmentReservationCancelCompleteBinding.apply {
                root.context.setImage(reservationCancelImageView, data.imageRes)
                reservationCancelTextViewReservedDate.setText(data.reservationDate)
                reservationCancelTextViewDate.text = data.reservationDate
                reservationCancelTextViewPrice.text = data.usePrice
                reservationCancelTextViewTime.text = data.useTime
                reservationCancelTextViewFacility.text = data.titleText
            }
        }
    }
}