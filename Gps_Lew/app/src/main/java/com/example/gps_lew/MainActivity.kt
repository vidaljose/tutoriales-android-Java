package com.example.gps_lew

import android.Manifest
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.example.gps_lew.ui.theme.Gps_LewTheme

import android.provider.Settings
import android.net.Uri



class MainActivity : ComponentActivity() {


    private val br = BootReceiver()
    @RequiresApi(Build.VERSION_CODES.Q)
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

        setContent {
            Gps_LewTheme {
                Nav(applicationContext)

            }
        }
        // Verificar si el permiso de ubicación en segundo plano está otorgado
        if (!hasLocationPermission()) {
            // Si no está otorgado, abrir la configuración de la aplicación para que el usuario pueda otorgar el permiso
            navigateToLocationSettings()
        }


    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun navigateToLocationSettings() {
        // Crear un Intent para abrir la pantalla de detalles de la aplicación en la configuración del sistema
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri

        // Iniciar la actividad con el Intent
        startActivity(intent)
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun hasLocationPermission(): Boolean {
        return checkSelfPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION) ==
                PackageManager.PERMISSION_GRANTED
    }



}

