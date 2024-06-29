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
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentReservationCompleteBinding
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel
import kr.co.lion.application.finalproject_aparttalk.ui.reservation.adapter.ReservationCompleteRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.util.ReserveFragmentName


class ReservationCompleteFragment : Fragment() {

    private lateinit var fragmentReservationCompleteBinding: FragmentReservationCompleteBinding
    lateinit var reserveActivity: ReserveActivity

    val viewModel : ReservationViewModel by viewModels()

    val resAdapter : ReservationCompleteRecyclerViewAdapter by lazy {
        val adapter = ReservationCompleteRecyclerViewAdapter()
        adapter.serRecyclerviewClick(object : ReservationCompleteRecyclerViewAdapter.ItemOnResClickListener{
            override fun recyclerviewClickListener() {
                reserveActivity.replaceFragment(ReserveFragmentName.RESERVATION_CONFIRM_FRAGMENT, true, true, null)
            }
        })
        adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentReservationCompleteBinding =
            FragmentReservationCompleteBinding.inflate(inflater, container, false)
        reserveActivity = activity as ReserveActivity

        settingRecyclerview()
        gettingData()

        return fragmentReservationCompleteBinding.root
    }

    private fun settingRecyclerview() {
        fragmentReservationCompleteBinding.apply {
            recyclerViewTabReservationComplete.apply {
                adapter = resAdapter
                layoutManager = LinearLayoutManager(reserveActivity)
                val deco = MaterialDividerItemDecoration(reserveActivity, MaterialDividerItemDecoration.VERTICAL)
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
                    viewModel.getFacilityResData(user.uid, true)
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