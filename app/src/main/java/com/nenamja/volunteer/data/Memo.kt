package com.nenamja.volunteer.data

/**
* 데이터 클래스
* @AUTHOR 박기완
* @VERSION
* @DATE 2020/07/25 1:20 PM
**/
data class Memo(
        var memoId:String? = "",//"":"20190717092522758eda7985784ea5",
        var memoTypeCode:String? = "",//":"NORMAL",
        var memoContent:String? = "",//:"메모 등록111",
        var recipeId:String? = "",//:null,
        var recipeName:String? = "",//:null,
        var registDateTime:String? = "",//:"2019-07-17 09:25:22",
        var lastEditDateTime:String? = ""//:"2019-07-17 09:25:22"
    ) {
}