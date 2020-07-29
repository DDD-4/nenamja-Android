package com.nenamja.volunteer

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission

class SplashActivity : AppCompatActivity() {

    val permissionlistener: PermissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }

        override fun onPermissionDenied(deniedPermissions: List<String>) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callPermissionAgreement()
    }

    private fun callPermissionAgreement() {
        TedPermission.with(this)
            .setPermissionListener(permissionlistener)
            .setDeniedMessage(resources.getString(R.string.app_name))
            .setPermissions(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .check();
    }
}