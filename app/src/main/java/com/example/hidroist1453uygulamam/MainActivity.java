package com.example.hidroist1453uygulamam;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ViewFlipper;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private AppBarConfiguration mAppBarConfiguration;
    ViewFlipper v_flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.degerler, R.id.cikisYap,R.id.hesabim,R.id.ekip,R.id.sohbet,R.id.galeri)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.cikisYap){
                    auth.signOut();
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent1);
                    finish();
                }else if (id==R.id.degerler){
                    Intent intent2 = new Intent(getApplicationContext(), CekilenDegerlerActivity.class);
                    startActivity(intent2);
                }else if (id==R.id.hesabim){
                    Intent intent2 = new Intent(getApplicationContext(), HesabimActivity.class);
                    startActivity(intent2);
                }else if (id==R.id.ekip){
                    Intent intent2 = new Intent(getApplicationContext(), EkipUyeleriActivity.class);
                    startActivity(intent2);
                }else if (id==R.id.galeri){
                    Intent intent2 = new Intent(getApplicationContext(), GaleriActivity.class);
                    startActivity(intent2);
                }else if (id==R.id.sohbet){
                    Intent intent2 = new Intent(getApplicationContext(), SohbetActivity.class);
                    startActivity(intent2);
                }
                return true;
            }
        });
    }

    private void tanimla(){
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        if (user==null){
            Intent intent=new Intent(MainActivity.this,LogInActivity.class);
            startActivity(intent);
            finish();
        }
        v_flipper=(ViewFlipper)findViewById(R.id.v_flipper);

        int images[]={R.drawable.araba2,R.drawable.araba3,R.drawable.araba4,R.drawable.araba5,R.drawable.araba6,R.drawable.araba7,R.drawable.araba8};
        for (int image: images){
            flipperImages(image);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void flipperImages(int image){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);
        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(3000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }
}
