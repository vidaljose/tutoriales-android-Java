package com.example.proyecto16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2;
    Spinner sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        sp1 = findViewById(R.id.sp1);

        String[] operaciones = {"sumar","restar","multiplicar","dividir"};
        ArrayAdapter<String> operador1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,operaciones);
        sp1.setAdapter(operador1);
    }

    public void mostrarResultado(View v){
        Intent intento1 = new Intent(this, Actividad2.class);

        int valor1 = Integer.parseInt(et1.getText().toString());
        int valor2 = Integer.parseInt(et2.getText().toString());
        String operacion = sp1.getSelectedItem().toString();

        intento1.putExtra("valor1",valor1);
        intento1.putExtra("valor2", valor2);
        intento1.putExtra("operacion", operacion);
        startActivity(intento1);
    }
}