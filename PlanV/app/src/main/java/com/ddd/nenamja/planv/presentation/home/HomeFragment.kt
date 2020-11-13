package com.ddd.nenamja.planv.presentation.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ddd.nenamja.planv.MainViewModel
import com.ddd.nenamja.planv.R
import com.ddd.nenamja.planv.presentation.home.adapter.VolunteerListAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel by viewModel<HomeViewModel>()
    private val sharedViewModel by sharedViewModel<MainViewModel>()

    private var location = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requireActivity().window.statusBarColor =
                resources.getColor(R.color.colorStatusBar, null)
        } else {
            requireActivity().window.statusBarColor = resources.getColor(R.color.colorStatusBar)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(rv_list) {
            adapter = VolunteerListAdapter(object : VolunteerListAdapter.UserActionListener {
                override fun goToDetail(key: String) {
                    val bundle = bundleOf("keyArg" to key)
                    findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
                }

            })
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val size = (adapter as VolunteerListAdapter).itemCount
                    if (!srl_refresh.isRefreshing) {
                        if ((layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == size - 1) {
                            viewModel.loadMoreVolunteerList()
                        }
                    }
                }
            })
        }
        with(srl_refresh) {
            setOnRefreshListener {
                isRefreshing = true
                viewModel.resetVolunteerList(location)
            }
        }
        sharedViewModel.addressLocation.observe(viewLifecycleOwner, Observer { location ->
            this.location = location
            Log.d("ironelder", "home Location = $location")
            viewModel.resetVolunteerList(location = location)
        })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            pb_loading.visibility = if (isLoading == true) View.VISIBLE else View.GONE
        })
        viewModel.refreshing.observe(viewLifecycleOwner, Observer { refresh ->
            pb_loading.visibility = if (refresh == true) View.VISIBLE else View.GONE
        })
        viewModel.volunteerDataList.observe(viewLifecycleOwner, Observer { volunteerList ->
            if (volunteerList.isNotEmpty()) {
                if (srl_refresh.isRefreshing) {
                    (rv_list.adapter as VolunteerListAdapter).setVolunteerList(volunteerList)
                    srl_refresh.isRefreshing = false
                } else {
                    (rv_list.adapter as VolunteerListAdapter).addVolunteerList(volunteerList)
                }
            }
        })
    }

}