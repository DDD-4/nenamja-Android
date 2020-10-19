package com.ddd.nenamja.planv.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.nenamja.planv.data.remote.model.Item
import com.ddd.nenamja.planv.data.remote.model.VolunteerListModel
import com.ddd.nenamja.planv.data.remote.repository.PlanVRepository
import com.google.gson.Gson
import fr.arnaudguyon.xmltojsonlib.XmlToJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(private val planVRepository: PlanVRepository) : ViewModel() {


    private var page:Int = 1

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _refreshing = MutableLiveData<Boolean>(false)
    val refreshing: LiveData<Boolean> get() = _refreshing

    private val _volunteerDataList: MutableLiveData<List<Item>> =
        MutableLiveData(
            emptyList()
        )
    val volunteerDataList: LiveData<List<Item>> get() = _volunteerDataList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.KOREA)
            val todayString = dateFormat.format(calendar.time)
            withContext(Dispatchers.IO){
                val model = planVRepository.getVolunteerList(
                    page = page,
                    date = todayString,
                    location = ""
                )
                val xmlToJson = XmlToJson.Builder(model).build()
                val volunteerListModel = Gson().fromJson(xmlToJson.toJson().toString(), VolunteerListModel::class.java)
                if(volunteerListModel.response.body.items.item.isNotEmpty()){
                    _volunteerDataList.postValue(volunteerListModel.response.body.items.item)
                }
                _isLoading.postValue(false)
            }
        }
    }
}