package com.ddd.nenamja.planv.data.remote.model.detail

data class VolunteerDetailModel(
    val response: Response
)

data class Response(
    val body: Body,
    val header: Header
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

data class Items(
    val item: Item
)

data class Item(
    val addr: String,
    val info1: String,
    val info2: String,
    val managerNm: String,
    val organNm: String,
    val pgmNm: String,
    val place: String,
    val price: String,
    val sdate: String,
    val target: String,
    val tel: String,
    val zip: String
)