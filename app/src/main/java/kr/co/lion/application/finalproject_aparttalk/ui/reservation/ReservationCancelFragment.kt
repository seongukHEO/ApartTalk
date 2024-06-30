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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentReservationCancelBinding
import kr.co.lion.application.finalproject_aparttalk.ui.reservation.adapter.ReservationCancelRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.reservation.adapter.ReservationCompleteRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.util.ReserveFragmentName


class ReservationCancelFragment : Fragment() {

    private lateinit var fragmentReservationCancelBinding: FragmentReservationCancelBinding

    lateinit var reserveActivity: ReserveActivity

    val viewModel : ReservationViewModel by viewModels()

    val resAdapter : ReservationCancelRecyclerViewAdapter by lazy {
        val adapter = ReservationCancelRecyclerViewAdapter()
        adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentReservationCancelBinding =
            FragmentReservationCancelBinding.inflate(inflater, container, false)
        reserveActivity = activity as ReserveActivity

        settingRecyclerview()
        gettingData()

        return fragmentReservationCancelBinding.root
    }

    private fun settingRecyclerview() {
        fragmentReservationCancelBinding.apply {
            recyclerViewTabReservationCancel.apply {
                adapter = resAdapter
                layoutManager = LinearLayoutManager(reserveActivity)
                val deco = MaterialDividerItemDecoration(
                    reserveActivity,
                    MaterialDividerItemDecoration.VERTICAL
                )
                addItemDecoration(deco)
            }
        }
    }

    //데이터 받아오기
    private fun gettingData(){
        viewLifecycleOwner.lifecycleScope.launch {
            val authUser = App.authRepository.getCurrentUser()
            if (authUser != null){
                var user = App.userRepository.getUser(authUser.uid)
                if (user != null){
                    viewModel.getFacilityResData(user.uid, false)
                    //viewModel.getTime()
                }
            }
        }
        viewModel.facilityGetList.observe(reserveActivity){value ->
            resAdapter.submitList(value)
            Log.e("testseong", "${value}")
        }
    }
}