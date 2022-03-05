package com.example.hidroist1453uygulamam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapters.UserAdapter;
import Models.KullanicilarModels;

public class EkipUyeleriActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    List<String> userKeysList;
    RecyclerView userListRecyclerview;
    UserAdapter userAdapter;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekip_uyeleri);
        tanimla();
        kullanicilariGetir();
    }

    public void tanimla() {
        userKeysList = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
        userListRecyclerview = (RecyclerView) findViewById(R.id.userListRecyclerview);
        RecyclerView.LayoutManager mng = new GridLayoutManager(EkipUyeleriActivity.this, 1);
        userListRecyclerview.setLayoutManager(mng);
        userAdapter = new UserAdapter(userKeysList, EkipUyeleriActivity.this, EkipUyeleriActivity.this);
        userListRecyclerview.setAdapter(userAdapter);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

    }

    public void kullanicilariGetir() {
        reference.child("Kullanicilar").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshots, @Nullable String previousChildName) {
                reference.child("Kullanicilar").child(snapshots.getKey()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        KullanicilarModels kl = snapshot.getValue(KullanicilarModels.class);
                        if (!kl.getIsim().equals("null") && !snapshot.getKey().equals(user.getUid())) {
                            userKeysList.add(snapshot.getKey());
                            userAdapter.notifyDataSetChanged();//adapterımızı hep güncelleyeceğiz.
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
