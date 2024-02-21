package com.example.proyecto18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
    }

    public void grabar(View v){
        String nombreArchivo = et1.getText().toString();
        String contenido = et2.getText().toString();
        // Crear archivo de texto
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(nombreArchivo, Context.MODE_PRIVATE));
            archivo.write(contenido); //Graba
            archivo.flush(); // Liberar memoria
            archivo.close(); // Cerrar
            et1.setText("");
            et2.setText("");
            Toast.makeText(this,"Los datos se grabaron", Toast.LENGTH_SHORT);
        }catch (IOException e){
            Toast.makeText(this,"No se pudo crear el archivo",Toast.LENGTH_SHORT).show();
        }

    }

    public void recuperar(View v){
        String nombreArchivo = et1.getText().toString();
        try{
            InputStreamReader archivo = new InputStreamReader(openFileInput(nombreArchivo));
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();
            String contenido = "";
            while (linea != null){
                contenido = contenido + linea + "\n";
                linea = br.readLine();
            }
            br.close();
            archivo.close();
            et2.setText(contenido);
        }catch (IOException e){
            Toast.makeText(this,"No se encontro el archivo",Toast.LENGTH_SHORT);
        }

    }

}