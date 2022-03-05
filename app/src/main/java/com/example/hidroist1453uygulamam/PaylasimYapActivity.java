package com.example.hidroist1453uygulamam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import Models.KullanicilarModels;

public class PaylasimYapActivity extends AppCompatActivity {
    EditText yorumPaylasimlaraEditText;
    ImageView eklePaylasimlaraImageView;
    Button eklePaylasimlaraButton;
    Bitmap bitmap;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    Uri uri;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paylasim_yap);
        tanimla();
        kullaniciIsmi();
        action();
    }

    public void tanimla() {
        yorumPaylasimlaraEditText = (EditText) findViewById(R.id.yorumPaylasimlaraEditText);
        eklePaylasimlaraImageView = (ImageView) findViewById(R.id.eklePaylasimlaraImageView);
        eklePaylasimlaraButton = (Button) findViewById(R.id.eklePaylasimlaraButton);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        firebaseFirestore = FirebaseFirestore.getInstance();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("Kullanicilar");

    }

    public void action() {
        eklePaylasimlaraImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galeriAc();
            }
        });

        eklePaylasimlaraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uri != null) {
                    UUID uuid = UUID.randomUUID();
                    final String imageName = "images/" + uuid + "jpg";
                    storageReference.child(imageName).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //resmi kaydettik ama urlsini alıp veritabanına kaydetmemiz gerekiyor.
                            StorageReference newReference = FirebaseStorage.getInstance().getReference(imageName);
                            newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //şuan resmimizin url'ini aldık
                                    String downloadUrl = uri.toString();
                                    veritabaninaKaydet(downloadUrl);
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Resim Yüklenemedi", Toast.LENGTH_LONG).show();

                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Resim Yok", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    //bu iki fonksiyon galeriyi açıyor.
    public void galeriAc() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    //galeriden fotoğraf seçildikten sonra nolcak onu yazıcaz
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            uri = data.getData();
            try {
                if (Build.VERSION.SDK_INT >= 28) {
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), uri);
                    bitmap = ImageDecoder.decodeBitmap(source);
                    eklePaylasimlaraImageView.setImageBitmap(bitmap);
                } else {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    eklePaylasimlaraImageView.setImageBitmap(bitmap);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void veritabaninaKaydet(String downloadUrl) {

        String mail = user.getEmail();
        String yorum = yorumPaylasimlaraEditText.getText().toString();
        UUID uuid = UUID.randomUUID();
        String gonderiId = String.valueOf(uuid);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("username", userName);
        hashMap.put("yorum", yorum);
        hashMap.put("mail", mail);
        hashMap.put("resim", downloadUrl);
        hashMap.put("gonderiid", gonderiId);
        hashMap.put("tarih", FieldValue.serverTimestamp());


        firebaseFirestore.collection("Posts").document(gonderiId).set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent intent = new Intent(PaylasimYapActivity.this, GaleriActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//açık olan tüm aktivitileri kapat demek oluyor bu.
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Veritabanina veri eklenemedi", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void kullaniciIsmi() {

        reference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                KullanicilarModels kl = snapshot.getValue(KullanicilarModels.class);
                userName = kl.getIsim();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
