package com.example.proyecto002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et1,et2;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        tv1 = findViewById(R.id.tv1);

    }

    public void sumar(View v){
        String s1 = et1.getText().toString();
        String s2 = et2.getText().toString();
        int v1 = Integer.parseInt(s1);
        int v2 = Integer.parseInt(s2);

        int res = v1 + v2;
        tv1.setText("El resultado de la suma es: "+ res);
    }
}