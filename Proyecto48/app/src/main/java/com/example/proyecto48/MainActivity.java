package com.example.proyecto48;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Runnable {

    private TextView tv1;
    private long suma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.tv1);
    }

    public void sumar(View v){
        tv1.setText("Espere un momento estamos sumando");
        Thread hilo = new Thread(this);
        hilo.start(); // Esto llama al run
    }
    public void salir(View v){
        finish();
    }

    @Override
    public void run() {
        // Algoritmo que requiere de mucho tiempo
        suma = 0;
        for (long i =0;i<=2_000_000_000;i++){
            suma += i;
        }
        tv1.post(new Runnable() {
            @Override
            public void run() {
                tv1.setText("El resultado de la suma es "+ suma);

            }
        });
    }
}