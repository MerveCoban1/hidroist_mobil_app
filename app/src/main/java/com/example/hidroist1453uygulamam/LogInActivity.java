package com.example.hidroist1453uygulamam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {
    private EditText sifreEditText,emailEditText;
    private TextView signUpTextView,sifremiUnuttumTextView;
    private Button logInButton;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        tanimla();
    }

    private void tanimla(){
        sifreEditText=(EditText)findViewById(R.id.sifreEditText);
        emailEditText=(EditText)findViewById(R.id.emailEditText);
        signUpTextView=(TextView) findViewById(R.id.signUpTextView);
        sifremiUnuttumTextView=(TextView) findViewById(R.id.sifremiUnuttumTextView);
        logInButton=(Button) findViewById(R.id.logInButton);
        auth=FirebaseAuth.getInstance();

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action();
            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LogInActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        sifremiUnuttumTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diyalogAc();
            }
        });
    }

    private void action(){
        String mail=emailEditText.getText().toString();
        String sifre=sifreEditText.getText().toString();
        if (!mail.equals("") && !sifre.equals("")) {
            auth.signInWithEmailAndPassword(mail,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Intent intent=new Intent(LogInActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"bir hata olustu",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void diyalogAc(){
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.sifre_sifirla_alert_layout,null);
        Button evet=(Button)view.findViewById(R.id.evet);
        final EditText grlnMail=(EditText)view.findViewById(R.id.grlnMail);
        AlertDialog.Builder alert=new AlertDialog.Builder(LogInActivity.this);
        alert.setView(view);
        final AlertDialog dialog=alert.create();
        evet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                auth.sendPasswordResetEmail(grlnMail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Sifirlama Linki Gonderildi",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LogInActivity.this, LogInActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
        dialog.show();
    }
}
