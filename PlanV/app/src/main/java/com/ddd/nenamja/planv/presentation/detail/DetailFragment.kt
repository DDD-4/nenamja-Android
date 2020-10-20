package com.ddd.nenamja.planv.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ddd.nenamja.planv.R
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.ext.android.inject

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val key = arguments?.getString("keyArg")
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            pb_loading.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
        viewModel.detail.observe(viewLifecycleOwner, Observer { detailData ->
            val peopleInfo = detailData.target.split(" ")
            val maxPeople = peopleInfo.last().replace("(", "").replace(")", "")
            val targetPeople =
                if (peopleInfo.first() == "") resources.getString(R.string.target_all) else peopleInfo.first()
            tv_price.text =
                if (detailData.price == "0") resources.getString(R.string.price_free) else resources.getString(
                    R.string.price_not_free
                )
            tv_program.text = detailData.pgmNm
            tv_max_people.text =
                String.format(resources.getString(R.string.max_people), maxPeople)
            tv_target_people.text = String.format(
                resources.getString(R.string.target_people),
                targetPeople
            )
            tv_information1.text =
                String.format(resources.getString(R.string.volunteer_info1, detailData.info1))
            val addInfo =
                if (detailData.info2.isNotEmpty()) detailData.info2 else resources.getString(R.string.not_more_info)
            tv_information2.text = addInfo
            tv_organize.text =
                String.format(resources.getString(R.string.organize_name), detailData.organNm)
            tv_manager.text =
                String.format(resources.getString(R.string.organize_manager), detailData.managerNm)
            val address = String.format(
                resources.getString(R.string.organize_address),
                detailData.zip,
                detailData.addr
            )
            tv_address.text = address
        })
        viewModel.getDetailData(key = key ?: "")
    }

}