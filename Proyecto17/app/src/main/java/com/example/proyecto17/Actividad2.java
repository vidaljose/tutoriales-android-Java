package com.example.proyecto17;

import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Actividad2 extends AppCompatActivity {

    EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
    }

    public void cancelar(View v){
        Intent intento1 = new Intent();
        setResult(Activity.RESULT_CANCELED,intento1);
        finish();
    }

    public void confirmar(View v){
        Intent intento1 = new Intent();
        intento1.putExtra("usuario",et1.getText().toString());
        intento1.putExtra("clave",et2.getText().toString());
        setResult(Activity.RESULT_OK, intento1);
        finish();
    }
}