package com.example.coolimages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_main.*
import nabed.apps.services.utils.cachingUtils.SharedPreferencesUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigationComponents()
    }

    private fun setupNavigationComponents() {
        val navHostFragment = nav_host_fragment as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.main_navigation)


        if (SharedPreferencesUtils.getInstance().getBooleanValue("Loggedin",false)) {
            graph.startDestination = R.id.galleryFragment
            navHostFragment.navController.graph = graph
        } else {
            graph.startDestination = R.id.loginFragment
            navHostFragment.navController.graph = graph
        }
    }
}
