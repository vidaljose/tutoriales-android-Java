package com.example.gpslew;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class JustLocation implements LocationListener {

    public void getLocation(View v){
        // Primero solicitar y revisar permisos
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
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
