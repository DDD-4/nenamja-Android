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

    private val mapToStateData = arrayOf("서울", "부산", "대구", "인천", "광주", "대전", "울산", "경기", "강원", "충북", "충남", "전북", "전남", "경북", "경남", "제주")

    fun getAddress(latitude:Double, longitude:Double, geoCoder: Geocoder){
        viewModelScope.launch(Dispatchers.IO) {
            val result = geoCoder.getFromLocation(latitude,longitude, 1)
            if(!result.isNullOrEmpty()){
                val admin = result[0].adminArea
                val location = mapToStateData.first { admin.contains(it) }
                if(location.isNotEmpty()){
                    _addressLocation.postValue(location)
                } else {
                    //충청남도 , 충청북도, 
                }
                Log.d("ironelder", "admin result = $admin")
            }
        }
    }
}