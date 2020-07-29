package com.nenamja.volunteer.net

/**
* 응답객체 내의 데이터 객체
* @AUTHOR 박기완
* @VERSION
* @DATE 2020/07/25 1:24 PM
**/
data class NenamjaResponseData<T>(
    var outputTextList: ArrayList<String?>? = null,
    var result: T? = null,
    val intent: String? = null,
    val entities: HashMap<String?, String?>? = null,
    val action: String? = null,
    val botName: String? = null
) {
}