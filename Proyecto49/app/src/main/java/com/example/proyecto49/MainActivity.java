package com.example.proyecto49;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Runnable {

    private TextView tv1;
    private long suma;
    private boolean procesando = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.tv1);
    }

    public void sumar(View v){
        if(procesando == false){
            procesando = true;
            tv1.setText("Espere un momento");
            Thread hilo = new Thread(this);
            hilo.start();
        }

    }

    @Override
    public void run() {
        suma = 0;
        for(long i = 0;i<= 2_000_000_000; i++){
            suma += i;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv1.setText("La suma de 1 a 2.000.000.000 es: "+ suma);
                procesando = false;
            }
        });

    }
}