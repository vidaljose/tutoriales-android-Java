package com.example.gps_lew

import android.Manifest
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import com.example.gps_lew.ui.theme.Gps_LewTheme

class MainActivity : ComponentActivity() {

    private val br = BootReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ),
            0
        )
        // Habilitar el BootReceiver dinámicamente
        /*
        val bootReceiver = ComponentName(applicationContext, BootReceiver::class.java)
        packageManager.setComponentEnabledSetting(
            bootReceiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        ) */

        registerReceiver(
            br,
            IntentFilter(Intent.ACTION_BOOT_COMPLETED)
        )

        setContent {
            Gps_LewTheme {
                Nav(applicationContext)
                /*
                Intent(applicationContext, LocationService::class.java).apply {
                    action = LocationService.ACTION_START
                    startService(this)
                } */
            }
        }
    }
}

