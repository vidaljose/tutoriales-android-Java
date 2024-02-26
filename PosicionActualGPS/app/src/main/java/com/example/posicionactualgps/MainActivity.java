package com.example.posicionactualgps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    Button getLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private TextView tv1, tv2;
    private final static int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv2 = findViewById(R.id.tv2);
    }

    public void getLocation(View v){
        // Primero solicitar y revisar permisos

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            retornoLocacion();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},200);
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

        if(requestCode == 200 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Entra en validador", Toast.LENGTH_SHORT).show();
            retornoLocacion();
        }else{
            tv2.setText("No poseo permisos para mostrar la informacion");
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}