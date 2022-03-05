package com.example.hidroist1453uygulamam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class IlkGiris extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilk_giris);
        Thread thread=new Thread(){
            @Override
            public void run() {
                try{
                   sleep(1000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent intent=new Intent(IlkGiris.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();
    }
}
