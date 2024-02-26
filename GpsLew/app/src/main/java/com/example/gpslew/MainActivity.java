package com.example.gpslew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private TextView tv1, tv2;
    private final static int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv2 = findViewById(R.id.tv2);

        if(!foregroundServiceRunning()){
            Intent serviceIntent = new Intent(this, MyForegroundService.class);
            startService(serviceIntent); // Background service
            startForegroundService(serviceIntent);
        }
    }
    public boolean foregroundServiceRunning(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service: activityManager.getRunningServices(Integer.MAX_VALUE)){
            if(MyForegroundService.class.getName().equals(service.service.getClassName())){
                return true;
            }
        }
        return false;
    }

     public void getLocation(View v){
        // Primero solicitar y revisar permisos
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            retornoLocacion();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},200);
            // ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 200);
        }

    }

    @SuppressLint("MissingPermission")
    private void retornoLocacion() {
        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,this);
        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if(location != null){
            double latitud = location.getLatitude();
            double longitud = location.getLongitude();

            tv2.setText("La latitud es: " + latitud + "\n"+"La longitud es: "+longitud );
            Toast.makeText(this, "La latitud es: " + latitud + "\n"+"La longitud es: "+longitud, Toast.LENGTH_SHORT).show();
        }else{
            tv2.setText("Sin posicion" );
            Toast.makeText(this, "No me da posiciones porque patata", Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 200) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // El usuario concedió el permiso
                Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
                retornoLocacion();
            } else {
                // El usuario no concedió el permiso
                tv2.setText("No poseo permisos para mostrar la información");
            }
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}