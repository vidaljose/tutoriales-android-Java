package com.example.proyecto50;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv1, tv2;
    private EditText et1;
    private Handler manejador = new Handler(Looper.getMainLooper());
    private Runnable tiempo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 =  findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        et1 = findViewById(R.id.et1);
        manejador.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv1.setText("Pasaron 3 segundos");
            }
        },3000);
        manejador.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv1.setText("Pasaron 6 segundos");
            }
        },6000);
        manejador.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv1.setText("Pasaron 9 segundos");
            }
        },9000);

        tiempo = new Runnable() {
            @Override
            public void run() {
                int valor = Integer.parseInt(tv2.getText().toString());
                if (valor == 0){
                    finish();
                }else{
                    valor--;
                    tv2.setText(String.valueOf(valor));
                    manejador.postDelayed(this,1000);
                }

            }
        };

        manejador.postDelayed(tiempo,0);

    }

    public void ingresar(View v){
        if(et1.getText().toString().equals("123")){
            manejador.removeCallbacks(tiempo);
        }
    }
}