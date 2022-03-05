package com.example.hidroist1453uygulamam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Adapters.type2Adapter;
import Models.type2Pojo;
import RestApi.ManagerAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HucreGerilimActivity extends AppCompatActivity {
    ListView listeHucreGerilimi;
    List<type2Pojo> list;
    type2Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hucre_gerilim);
        tanimla();
        istekAt();
    }

    public void tanimla() {
        listeHucreGerilimi = (ListView) findViewById(R.id.listeHucreGerilimi);
    }

    public void istekAt() {
        list = new ArrayList<>();
        Call<List<type2Pojo>> val = ManagerAll.getInstance().gosterHucreGerilim();
        val.enqueue(new Callback<List<type2Pojo>>() {
            @Override
            public void onResponse(Call<List<type2Pojo>> call, Response<List<type2Pojo>> response) {
                list = response.body();
                adapter = new type2Adapter(list, getApplicationContext());
                listeHucreGerilimi.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<type2Pojo>> call, Throwable t) {

            }
        });
    }
}
