package com.ddd.nenamja.planv.data.remote.model

data class VolunteerListModel(
    val response: Response
)

data class Response(
    val body: Body,
    val header: Header
)

data class Items(
    val item: List<Item>
)

data class Item(
    val key1: String,
    val organNm: String,
    val pgmNm: String,
    val price: String,
    val sdate: String,
    val target: String
)

data class Header(
    val resultCode: String,
    val resultMsg: String
)

data class Body(
    val items: Items,
    val numOfRows: String,
    val pageNo: String,
    val totalCount: String
)