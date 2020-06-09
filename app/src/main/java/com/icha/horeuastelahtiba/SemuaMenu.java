package com.icha.horeuastelahtiba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class SemuaMenu extends AppCompatActivity {

    //variabel
    Button ke_venue, ke_photo, ke_catering, ke_invite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_semua_menu);

        ke_venue = findViewById(R.id.btn_venue);
        ke_venue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //login diganti halaman venue
                Intent intent = new Intent(SemuaMenu.this,LoadImageVenue.class);
                startActivity(intent);
            }

        });

        ke_photo = findViewById(R.id.btn_photo);
        ke_photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //login diganti halaman photo
                Intent intent = new Intent(SemuaMenu.this,LoadImagePhoto.class);
                startActivity(intent);
            }

        });

        ke_catering = findViewById(R.id.btn_catering);
        ke_catering.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //login diganti halaman catering
                Intent intent = new Intent(SemuaMenu.this,LoadImageCatering.class);
                startActivity(intent);
            }

        });

        ke_invite = findViewById(R.id.btn_invite);
        ke_invite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //login diganti halaman invite
                Intent intent = new Intent(SemuaMenu.this,LoadImageInvite.class);
                startActivity(intent);
            }

        });


    }
}
