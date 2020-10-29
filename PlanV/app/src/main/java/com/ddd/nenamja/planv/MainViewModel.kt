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

    fun getAddress(latitude:Double, longitude:Double, geoCoder: Geocoder){
        viewModelScope.launch(Dispatchers.IO) {
            val result = geoCoder.getFromLocation(latitude,longitude, 1)
            if(!result.isNullOrEmpty()){
                val admin = result[0].adminArea
                Log.d("ironelder", "admin result = $admin")
            }
        }
    }
}