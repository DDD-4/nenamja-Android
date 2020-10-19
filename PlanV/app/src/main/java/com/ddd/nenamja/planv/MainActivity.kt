package com.ddd.nenamja.planv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

}