package com.nenamja.volunteer

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import com.nenamja.volunteer.util.SharedPreferencesManager
import com.nenamja.volunteer.util.VolunteerUtil

/**
 * 전역 앱 객체. sharedPref, log, util 을 여기서 관리함
 * @AUTHOR 박기완
 * @VERSION
 * @DATE 2020/07/25 1:31 PM
 **/
class App : Application() {

    companion object {
        lateinit var pref: SharedPreferencesManager
        lateinit var dlog: Dlog
        lateinit var util: VolunteerUtil
        var DEBUG: Boolean = false
    }

    override fun onCreate() {
        pref = SharedPreferencesManager(applicationContext)
        DEBUG = isDebuggable(this)
        util = VolunteerUtil(applicationContext)
        dlog = Dlog()
        super.onCreate()
    }

    fun isDebuggable(context: Context): Boolean {
        var debuggable = false
        val pm: PackageManager = context.getPackageManager()
        try {
            val appinfo = pm.getApplicationInfo(context.getPackageName(), 0)
            debuggable = 0 != appinfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        } catch (e: PackageManager.NameNotFoundException) { /* debuggable variable will remain false */
        }
        return debuggable
    }

    class Dlog {
        /** Log Level Error **/
        fun e(message: String? = "") {
            if (DEBUG) Log.e(buildLogMsg(), message ?: "");
        }

        /** Log Level Warning **/
        fun w(message: String? = "") {
            if (DEBUG) Log.w(buildLogMsg(), message ?: "");
        }

        /** Log Level Information **/
        fun i(message: String? = "") {
            if (DEBUG) Log.i(buildLogMsg(), message ?: "");
        }

        /** Log Level Debug **/
        fun d(message: String? = "") {
            if (DEBUG) Log.d(buildLogMsg(), message ?: "");
        }

        /** Log Level Verbose **/
        fun v(message: String? = "") {
            if (DEBUG) Log.v(buildLogMsg(), buildLogMsg(message ?: ""));
        }

        private fun buildLogMsg(): String {
            return buildLogMsg("")
        }

        private fun buildLogMsg(message: String): String {

            var ste: StackTraceElement = Thread.currentThread().stackTrace[4];
            var sb: StringBuilder = StringBuilder();

            sb.append("[");
            sb.append(ste.fileName.replace(".java", ""));
            sb.append("::");
            sb.append(ste.methodName);
            sb.append("]");

            return sb.toString();
        }
    }
}