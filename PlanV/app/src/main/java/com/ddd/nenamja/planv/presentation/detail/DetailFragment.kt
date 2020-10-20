package com.ddd.nenamja.planv.presentation.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ddd.nenamja.planv.R
import com.kakao.sdk.navi.NaviClient
import kotlinx.android.synthetic.main.fragment_detail.*
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import org.koin.android.ext.android.inject


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by inject()
    private var phoneNumberScheme: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val key = arguments?.getString("keyArg")
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            pb_loading.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
        viewModel.detail.observe(viewLifecycleOwner, Observer { detailData ->
            viewModel.getAddressData(detailData.addr, detailData.organNm)
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
            phoneNumberScheme = String.format(
                resources.getString(R.string.call_scheme),
                detailData.tel.replace("-", "").trim()
            )
            btn_call.setOnClickListener {
                if (!phoneNumberScheme.isNullOrEmpty()) {
                    val alertDialogBuilder = AlertDialog.Builder(requireContext())
                    with(alertDialogBuilder) {
                        setTitle(resources.getString(R.string.call_alert_title))
                        setMessage(
                            String.format(
                                resources.getString(R.string.call_alert_message),
                                detailData.tel
                            )
                        )
                        setPositiveButton(
                            resources.getString(R.string.call_confirm)
                        ) { _, _ ->
                            startActivity(
                                Intent(
                                    "android.intent.action.CALL",
                                    Uri.parse(phoneNumberScheme)
                                )
                            )
                        }
                        setNeutralButton(resources.getString(R.string.call_cancel)) { _, _ ->

                        }
                    }.create().show()
                }
            }
        })
        viewModel.mapCoodi.observe(viewLifecycleOwner, Observer { coodinate ->
            Log.d("ironelder", "coodi = $coodinate")
            if (coodinate.first != "0" && coodinate.second != "0") {
                initializeMap(coodinate.first, coodinate.second, coodinate.third)
            }
        })
        viewModel.getDetailData(key = key ?: "")
    }

    private fun initializeMap(x: String, y: String, dest:String) {
        val latitude = x.toDouble()
        val longitude = y.toDouble()
        val mapView = MapView(requireActivity())
        mapView.setDaumMapApiKey("afccb3e4d2081161b4a1570ed23a0ea7")
        mapView.mapType = MapView.MapType.Standard
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);
        val marker = MapPOIItem()
        marker.itemName = dest
        marker.tag = 0
        marker.mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
        marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.
        marker.selectedMarkerType =
            MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker)
        mapView.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        (map_view as ViewGroup).addView(mapView)
    }

}