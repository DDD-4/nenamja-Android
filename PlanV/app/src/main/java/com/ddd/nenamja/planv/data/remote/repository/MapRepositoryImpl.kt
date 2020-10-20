package com.ddd.nenamja.planv.data.remote.repository

import com.ddd.nenamja.planv.data.remote.model.map.MapModel
import com.ddd.nenamja.planv.data.remote.source.MapDataSource

class MapRepositoryImpl(private val mapDataSource: MapDataSource) : MapRepository {
    override suspend fun getMapData(address: String): MapModel {
        return mapDataSource.getMapData(address = address)
    }
}