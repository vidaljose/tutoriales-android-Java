package com.example.gps_lew

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.PowerManager
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class LocationService: Service()  {
    private var wakeLock: PowerManager.WakeLock? = null
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var locationClient: LocationClient

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        locationClient = DefaultLocationClient(
            applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext)
        )

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }


    private fun start() {
        val sharedPrefUtil = SharedPrefUtil(applicationContext)
        val usuario = sharedPrefUtil.getData("user")
        val notification = NotificationCompat.Builder(this,"location")
            .setContentTitle("Siguiendote ${usuario}")
            .setContentText("Ubicacion: null")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setOngoing(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        locationClient.getLocationUpdates(20000L)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                val lat = location.latitude.toString()
                val lon = location.longitude.toString()
                enviarDatos(lat,lon)
                val updateNotification = notification.setContentText("latitud ${lat} longitud ${lon}")
                notificationManager.notify(1,updateNotification.build())
            }
            .launchIn(serviceScope)
        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        //wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "LocationService:WakeLock")
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP, "LocationService:WakeLock")
        wakeLock?.acquire()
        startForeground(1,notification.build())
    }

    private fun stop() {
        wakeLock?.release()
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    companion object{
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"

    }

    fun enviarDatos(lat:String,lon:String) {
        val sharedPrefUtil = SharedPrefUtil(applicationContext)
        val url = "http://190.246.216.236:3033/setlocation"
        val usuario = sharedPrefUtil.getData("user")
        val json = "{\"usuario\": \"${usuario}\", \"latitud\": \"${lat}\", \"longitud\": \"${lon}\"}"  // Reemplaza esto con tu información JSON
        Log.e("LocationService", "usuario: ${usuario}")
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = json.toRequestBody(mediaType)

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                // Manejar la falla aquí si es necesario
                println("Error: ${e.message}")
                Log.e("LocationService", "Error: ${e.message}")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                // No necesitas hacer nada con la respuesta si no te importa
                Log.d("LocationService", "Respuesta: ${response.body?.string()}")
                response.close()

            }
        })
    }
}