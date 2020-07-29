package com.nenamja.volunteer.net

/**
* 전역 리스폰스 객체
* @AUTHOR 박기완
* @VERSION
* @DATE 2020/07/25 1:23 PM
**/
data class NenamjaResponse<T>(
    val status: String,
    val code: String,
    val message: String,
    var data: NenamjaResponseData<T>? = null
) {
    constructor() : this(
        Status.UNKNOWN.string(),
        Code.UNKNOWN.string(),
        ""
    )

    fun getResult(): T? {
        return this.data?.result
    }

    fun setResult(result: T) {
        this.data?.result = result
    }


    val isSuccess: Boolean
        get() = Status.SUCCESS.string() == status

    val isFailure: Boolean
        get() = !isSuccess

    private enum class Code(private val mCode: String) {
        SUCCESS("000"), UNKNOWN("UNKNOWN");

        fun string(): String {
            return mCode
        }
    }

    private enum class Status(private val mStatus: String) {
        SUCCESS("200"), UNKNOWN("UNKNOWN");

        fun string(): String {
            return mStatus
        }

    }
}