package com.ddd.nenamja.planv.presentation.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.nenamja.planv.data.remote.model.detail.Item
import com.ddd.nenamja.planv.data.remote.model.detail.VolunteerDetailModel
import com.ddd.nenamja.planv.data.remote.repository.MapRepository
import com.ddd.nenamja.planv.data.remote.repository.PlanVRepository
import com.google.gson.Gson
import fr.arnaudguyon.xmltojsonlib.XmlToJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(
    private val planVRepository: PlanVRepository,
    private val mapRepository: MapRepository
) : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _mapCoodi: MutableLiveData<Triple<String, String, String>> = MutableLiveData(Triple("0", "0", ""))
    val mapCoodi: LiveData<Triple<String, String, String>> get() = _mapCoodi

    private val _detail: MutableLiveData<Item> = MutableLiveData()
    val detail: LiveData<Item> get() = _detail

    init {
    }

    fun getDetailData(key: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (key.isNotEmpty()) {
                _isLoading.postValue(true)
                val result = planVRepository.getVolunteerDetail(key = key)
                val xmlToJson = XmlToJson.Builder(result).build()
                val volunteerDetailModel =
                    Gson().fromJson(xmlToJson.toJson().toString(), VolunteerDetailModel::class.java)
                _detail.postValue(volunteerDetailModel.response.body.items.item)
                _isLoading.postValue(false)
            }
        }
    }

    fun getAddressData(address: String, destName:String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (address.isNotEmpty()) {
                val result = mapRepository.getMapData(address = address)
                if (!result.documents.isNullOrEmpty()) {
                    val longitude = result.documents.first().x
                    val latitude = result.documents.first().y
                    _mapCoodi.postValue(Triple(latitude, longitude, destName))
                }
            }
        }
    }
}