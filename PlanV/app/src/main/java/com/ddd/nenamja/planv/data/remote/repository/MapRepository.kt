package com.ddd.nenamja.planv.data.remote.repository

import com.ddd.nenamja.planv.data.remote.model.map.MapModel

interface MapRepository {
    suspend fun getMapData(address: String): MapModel
}