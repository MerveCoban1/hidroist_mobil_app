package com.example.hidroist1453uygulamam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Models.KullanicilarModels;
import de.hdodenhof.circleimageview.CircleImageView;

public class OtherProfileActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    Bundle intent;
    String kullaniciOtherId;
    CircleImageView profile_image2;
    TextView isim, departman;
    FirebaseDatabase database;
    DatabaseReference reference, referenceArkadaslik;
    ImageView mesajAt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);
        tanimla();
        action();
    }

    public void tanimla() {
        intent = getIntent().getExtras();
        kullaniciOtherId = intent.getString("id");

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        referenceArkadaslik = database.getReference().child("ArkadaslikIstegi");

        profile_image2 = (CircleImageView) findViewById(R.id.profile_image);
        isim = (TextView) findViewById(R.id.isim);
        departman = (TextView) findViewById(R.id.departman);
        mesajAt = (ImageView) findViewById(R.id.mesajAt);

    }

    public void action() {
        reference.child("Kullanicilar").child(kullaniciOtherId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                KullanicilarModels klm = snapshot.getValue(KullanicilarModels.class);
                isim.setText(klm.getIsim());
                departman.setText(klm.getDepartman());
                if (!klm.getResim().equals("null")) {
                    profile_image2.setImageBitmap(stringToBitmap(klm.getResim()));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        mesajAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OtherProfileActivity.this, ChatActivity.class);
                intent.putExtra("kisiIsmi", isim.getText().toString());
                intent.putExtra("digerKisiId", kullaniciOtherId);
                startActivity(intent);
            }
        });

    }

    public Bitmap stringToBitmap(String str) {
        byte[] encodeByte = Base64.decode(str, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        return bitmap;
    }
}
