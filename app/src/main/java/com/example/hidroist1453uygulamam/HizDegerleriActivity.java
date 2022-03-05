package com.example.hidroist1453uygulamam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Adapters.type1Adapter;
import Models.type1Pojo;
import RestApi.ManagerAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HizDegerleriActivity extends AppCompatActivity {
    ListView listeHizDegerleri;
    List<type1Pojo> list;
    type1Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiz_degerleri);
        tanimla();
        istekAt();
    }

    public void tanimla() {
        listeHizDegerleri = (ListView) findViewById(R.id.listeHizDegerleri);
    }

    public void istekAt() {
        list = new ArrayList<>();
        Call<List<type1Pojo>> val = ManagerAll.getInstance().gosterHiz();
        val.enqueue(new Callback<List<type1Pojo>>() {
            @Override
            public void onResponse(Call<List<type1Pojo>> call, Response<List<type1Pojo>> response) {
                list = response.body();
                adapter = new type1Adapter(list, getApplicationContext());
                listeHizDegerleri.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<type1Pojo>> call, Throwable t) {

            }
        });
    }
}
