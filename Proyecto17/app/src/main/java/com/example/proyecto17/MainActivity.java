package com.example.proyecto17;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.tv1);
    }

    public void login(View v){
        Intent intento1 = new Intent(this, Actividad2.class);
        startActivityForResult(intento1,100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            if(resultCode == Activity.RESULT_OK){
                Bundle datos = data.getExtras();
                tv1.setText(datos.getString("usuario"));
            }
            if(resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(this,"Se presiono el boton cancelar",Toast.LENGTH_SHORT).show();
                tv1.setText("No se reconoce el usuario");
            }
        }
    }
}