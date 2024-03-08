package com.example.gps_lew

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            // Se ejecutará cuando el dispositivo se reinicie o encienda
            val serviceIntent = Intent(context, LocationService::class.java)
            serviceIntent.action = LocationService.ACTION_START
            context?.startService(serviceIntent)

        }
    }
}