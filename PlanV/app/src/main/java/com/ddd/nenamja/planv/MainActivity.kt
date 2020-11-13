package com.ddd.nenamja.planv

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.gms.maps.model.LatLng
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.security.MessageDigest
import java.util.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val PERMISSION_GRANT_CODE = 8080
    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }
    private val locationManager by lazy {
        getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }
    private val viewModel by viewModel<MainViewModel>()
    var latitude = 0.0
    var longitude = 0.0


    override fun onSupportNavigateUp() = navController.navigateUp()

    private fun permissionCheck() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                PERMISSION_GRANT_CODE
            )
            Log.d("ironelder", "permissionCheck not grant")
        } else {

            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            location?.let {
                latitude = it.latitude
                longitude = it.longitude
                val geoCoder = Geocoder(applicationContext, Locale.KOREA)
                viewModel.getAddress(
                    latitude = latitude,
                    longitude = longitude,
                    geoCoder = geoCoder
                )
                val result = geoCoder.getFromLocation(latitude, longitude, 1)
                Log.d("ironelder", "result = $result")
            }
            Log.d("ironelder", "permissionCheck all grant")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionCheck()
        getAppKeyHash()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_GRANT_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    Toast.makeText(this, "Call Permission", Toast.LENGTH_LONG).show()
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }

                if ((grantResults.isNotEmpty() &&
                            grantResults[1] == PackageManager.PERMISSION_GRANTED)
                ) {
                    Toast.makeText(this, "Location Permission", Toast.LENGTH_LONG).show()
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }

                if ((grantResults.isNotEmpty() &&
                            grantResults[2] == PackageManager.PERMISSION_GRANTED)
                ) {
                    Toast.makeText(this, "Course Permission", Toast.LENGTH_LONG).show()
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }

    }

    private fun getAppKeyHash() {
        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (i in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(i.toByteArray())

                val something = String(Base64.encode(md.digest(), 0)!!)
//                Log.e("Debug key", something)
            }
        } catch (e: Exception) {
            Log.e("Not found", e.toString())
        }
    }


    private fun getAddress(position: LatLng) {
        val geoCoder = Geocoder(this@MainActivity, Locale.getDefault())
        val address =
            geoCoder.getFromLocation(position.latitude, position.longitude, 1).first()
                .getAddressLine(0)

        Log.e("ironelder", "Address = $address")
    }

}