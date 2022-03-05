package com.example.hidroist1453uygulamam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CekilenDegerlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cekilen_degerler);
    }

    public void bataryaSicakligiTikla(View v)
    {
        Intent intent=new Intent(CekilenDegerlerActivity.this,BataryaSicakligiActivity.class);
        startActivity(intent);
    }
    public void HizDegerleriTikla(View v)
    {
        Intent intent=new Intent(CekilenDegerlerActivity.this,HizDegerleriActivity.class);
        startActivity(intent);
    }
    public void HucreGerilimTikla(View v)
    {
        Intent intent=new Intent(CekilenDegerlerActivity.this,HucreGerilimActivity.class);
        startActivity(intent);
    }
    public void KalanEnerjiMiktariTikla(View v)
    {
        Intent intent=new Intent(CekilenDegerlerActivity.this,KalanEnerjiMiktariActivity.class);
        startActivity(intent);
    }
    public void MotorGerilimiTikla(View v)
    {
        Intent intent=new Intent(CekilenDegerlerActivity.this,MotorGerilimiActivity.class);
        startActivity(intent);
    }
    public void MotorSicakligiTikla(View v)
    {
        Intent intent=new Intent(CekilenDegerlerActivity.this,MotorSicakligiActivity.class);
        startActivity(intent);
    }
    public void SocTikla(View v)
    {
        Intent intent=new Intent(CekilenDegerlerActivity.this,SocActivity.class);
        startActivity(intent);
    }
}
