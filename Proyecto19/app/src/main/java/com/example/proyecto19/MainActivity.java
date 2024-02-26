package com.example.proyecto19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);


    }

    public void guardar(View v){
        SharedPreferences sp = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();

        String fecha = et1.getText().toString();
        String actividades = et2.getText().toString();
        // se agrega clave valor
        edit.putString(fecha,actividades);
        edit.commit();

        et1.setText("");
        et2.setText("");

        Toast.makeText(this,"Se guardo el contenido",Toast.LENGTH_SHORT).show();

    }

    public void recuperar(View v){
        SharedPreferences sp = getSharedPreferences("agenda",Context.MODE_PRIVATE);
        String datos = sp.getString(et1.getText().toString(),"");
        if(datos.equals("")){
            et2.setText("");
            Toast.makeText(this,"No se encontro tareas para la fecha",Toast.LENGTH_SHORT).show();
        }else{
            et2.setText(datos);
        }
    }


}