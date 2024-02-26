package com.example.proyectofetch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.tv1);


    }

    public void hacerGet(View v){
        Toast.makeText(this,"Inicio del fetch",Toast.LENGTH_SHORT).show();
        String url = "http://190.246.216.236:3033/login";

        OkHttpClient client = new OkHttpClient();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, "{\n" +
                "\t\"user\": \"josey\",\n" +
                "\t\"password\": \"123456\"\n" +
                "}");

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv1.setText("Error al hacer el post");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String myResponse = response.body().string();

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv1.setText(myResponse);
                        }
                    });
                }else{
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv1.setText("Error al hacer el post pero pasa por la respusta");
                        }
                    });
                }
            }
        });

    }

}