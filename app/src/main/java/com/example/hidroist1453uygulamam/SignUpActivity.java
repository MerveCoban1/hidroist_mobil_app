package com.example.hidroist1453uygulamam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private EditText sifreEditText,emailEditText;
    private TextView logInTextView;
    private Button signUpButton;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tanimla();
    }

    private void tanimla(){
        sifreEditText=(EditText)findViewById(R.id.sifreEditText);
        emailEditText=(EditText)findViewById(R.id.emailEditText);
        logInTextView=(TextView) findViewById(R.id.logInTextView);
        signUpButton=(Button) findViewById(R.id.signUpButton);
        auth=FirebaseAuth.getInstance();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action();
            }
        });

        logInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUpActivity.this,LogInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void action(){
        String mail=emailEditText.getText().toString();
        String sifre=sifreEditText.getText().toString();
        if (!mail.equals("") && !sifre.equals("")) {
            emailEditText.setText("");
            sifreEditText.setText("");
            auth.createUserWithEmailAndPassword(mail,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        firebaseDatabase=FirebaseDatabase.getInstance();
                        reference=firebaseDatabase.getReference().child("Kullanicilar").child(auth.getUid());
                        Map map=new HashMap();
                        map.put("resim","null");
                        map.put("isim","null");
                        map.put("departman","null");
                        reference.setValue(map);
                        Toast.makeText(getApplicationContext(),"Kayıt Basarılı",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(SignUpActivity.this,MailDogrulamaActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"Kayıt olurken bir hata olustu",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else{
            Toast.makeText(getApplicationContext(), "Bilgileri Bos Giremezsin", Toast.LENGTH_SHORT).show();
        }
    }
}
