package com.example.attempt_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SettingsActivity extends AppCompatActivity { //Экран настроек

    Button btnAva, btnBack, btnreport;
    String sSchool;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dRef, schoolRef;
    private String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btnAva = findViewById(R.id.btnChanAV);
        btnBack = findViewById(R.id.btnBackGR);
        btnreport = findViewById(R.id.btn_glitchreport);
        schoolRef = database.getReference("school/" + uid);


        schoolRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                sSchool = task.getResult().getValue().toString();

                dRef = database.getReference("users/" + sSchool + "/" + uid);

                btnreport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { //Переход на экран написания репорта о баге
                        Intent intent = new Intent(SettingsActivity.this, GlitchReportActivity.class);
                        intent.putExtra("uid", uid);
                        intent.putExtra("school", sSchool);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });

        btnAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //переход на экран смены аватара
                Intent intent = new Intent(SettingsActivity.this,ChangeAvatarActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //переход на экран смены заднего фона
                Intent intent = new Intent(SettingsActivity.this,BackgroundChangeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent(SettingsActivity.this,MainMenuActivity.class);
        startActivity(intent);
        finish();
    }

}
