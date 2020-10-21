package com.ddd.nenamja.planv.data.remote.source

import com.ddd.nenamja.planv.data.remote.model.map.MapModel

interface MapDataSource {
    suspend fun getMapData(address: String): MapModel
}