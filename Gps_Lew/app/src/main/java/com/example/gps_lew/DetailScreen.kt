package com.example.gps_lew

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.JobIntentService.enqueueWork
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.registerReceiver


@Composable
fun DetailScreen(myName: String?, applicationContext: Context){
    val sharedPrefUtil = SharedPrefUtil(LocalContext.current)

    if (myName != null && myName.isNotEmpty()) {
        // Iniciar el servicio aquí
        Intent(LocalContext.current, LocationService::class.java).apply {
            action = LocationService.ACTION_START
            ContextCompat.startForegroundService(LocalContext.current, this)
        }


    }



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Siguiendo", fontSize = 54.sp)
        Spacer(modifier = Modifier.height(45.dp))
        Text(text = "Usuario: ${myName}", fontSize = 45.sp)
        Text(text = "Usuario guardado: ${sharedPrefUtil.getData("user")}", fontSize = 33.sp)
    }

}

fun isLocationServiceRunning(context: Context): Boolean {
    val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    for (service in manager.getRunningServices(Int.MAX_VALUE)) {
        if (LocationService::class.java.name == service.service.className) {
            return true
        }
    }
    return false
}


