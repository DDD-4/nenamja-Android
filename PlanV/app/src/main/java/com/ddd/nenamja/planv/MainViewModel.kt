package com.ddd.nenamja.planv

import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _addressLocation: MutableLiveData<String> = MutableLiveData("")
    val addressLocation: LiveData<String> get() = _addressLocation

    private val mapToStateData = arrayOf(
        "서울",
        "부산",
        "대구",
        "인천",
        "광주",
        "대전",
        "울산",
        "경기",
        "강원",
        "충북",
        "충남",
        "전북",
        "전남",
        "경북",
        "경남",
        "제주"
    )

    fun getAddress(latitude: Double, longitude: Double, geoCoder: Geocoder) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = geoCoder.getFromLocation(latitude, longitude, 5)
            if (!result.isNullOrEmpty()) {
                val adminResult = result.filter { it.adminArea != null }
                Log.d("ironelder", "admin result = $adminResult")
                if(!adminResult.isNullOrEmpty()){
                    val admin = adminResult[0].adminArea
                    admin?.let { adminLocal ->
                        val location = mapToStateData.first { adminLocal.contains(it) }
                        if (location.isNotEmpty()) {
                            _addressLocation.postValue(location)
                        } else {
                            //충청남도, 충청북도, 전라북도, 전라남도, 경상북도, 경상남도
                            when{
                                adminLocal.contains("충청남") -> {
                                    _addressLocation.postValue("충남")
                                }
                                adminLocal.contains("충청북") -> {
                                    _addressLocation.postValue("충북")
                                }
                                adminLocal.contains("전라남") -> {
                                    _addressLocation.postValue("전남")
                                }
                                adminLocal.contains("전라북") -> {
                                    _addressLocation.postValue("전북")
                                }
                                adminLocal.contains("경상남") -> {
                                    _addressLocation.postValue("경남")
                                }
                                adminLocal.contains("경상북") -> {
                                    _addressLocation.postValue("경북")
                                }
                            }
                        }
                        Log.d("ironelder", "admin result = $adminLocal")
                    }
                }
            }
        }
    }
}