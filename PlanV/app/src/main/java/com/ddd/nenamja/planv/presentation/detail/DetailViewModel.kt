package com.ddd.nenamja.planv.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.nenamja.planv.data.remote.model.detail.Item
import com.ddd.nenamja.planv.data.remote.model.detail.VolunteerDetailModel
import com.ddd.nenamja.planv.data.remote.repository.PlanVRepository
import com.google.gson.Gson
import fr.arnaudguyon.xmltojsonlib.XmlToJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val planVRepository: PlanVRepository) : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

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
}