package com.ddd.nenamja.planv.data.remote.source

import com.ddd.nenamja.planv.data.remote.api.KakaoMapSearchApi
import com.ddd.nenamja.planv.data.remote.model.map.MapModel

class MapDataSourceImpl(private val kakaoMapSearchApi: KakaoMapSearchApi) : MapDataSource {
    override suspend fun getMapData(address: String): MapModel {
        return kakaoMapSearchApi.getMapData(query = address)
    }
}