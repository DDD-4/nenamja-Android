package com.nenamja.volunteer.util

import android.content.Context
import android.content.SharedPreferences

/**
 * 전역으로 사용할 sharedPreferences 객체
 * 하나의 파일에서 관리하여 쉽고 정확한 사용/관리
 * @AUTHOR 박기완
 * @VERSION
 * @DATE 2020/07/25 1:30 PM
 **/
class SharedPreferencesManager(context: Context) {
    val PREFERENCES_FILE_NAME = "VOLUNTEER_PREFERENCES_FILE_NAME"
    val pref: SharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, 0)

    //key list
    private val MYNUMBER = "MYNUMBER"
    private val UUID = "UUID"
    private val IS_ONLINE = "IS_ONLINE"

    //value control
    var myNumber: Int?
        get() = pref.getInt(MYNUMBER, -1)
        set(value) = pref.edit().putInt(MYNUMBER, value ?: -1).apply()

    var uuid: String?
        get() = pref.getString(UUID, "") ?: ""
        set(value) = pref.edit().putString(UUID, value ?: "").apply()

    var isOnline: Boolean?
        get() = pref.getBoolean(IS_ONLINE, true) ?: true
        set(value) = pref.edit().putBoolean(IS_ONLINE, value ?: true).apply()
}