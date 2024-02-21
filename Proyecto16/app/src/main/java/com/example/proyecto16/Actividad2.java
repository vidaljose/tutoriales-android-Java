package com.example.proyecto16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Actividad2 extends AppCompatActivity {

    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        tv1 = findViewById(R.id.tv1);
        Bundle datos = getIntent().getExtras();
        int valor1 = datos.getInt("valor1");
        int valor2 = datos.getInt("valor2");
        String operacion = datos.getString("operacion");

        switch (operacion){
            case "sumar":
                int suma = valor1 + valor2;
                tv1.setText(valor1 + " + "+ valor2 + " = " + suma);
                break;
            case "restar":
                int resta = valor1 - valor2;
                tv1.setText(valor1 + " - "+ valor2 + " = " + resta);
                break;
            case "multiplicar":
                int multiplica = valor1 * valor2;
                tv1.setText(valor1 + " * "+ valor2 + " = " + multiplica);
                break;
            case "dividir":
                int divide = valor1 / valor2;
                tv1.setText(valor1 + " / "+ valor2 + " = " + divide);
                break;
        }
    }

    public void retornar(View v){
        finish();
    }
}