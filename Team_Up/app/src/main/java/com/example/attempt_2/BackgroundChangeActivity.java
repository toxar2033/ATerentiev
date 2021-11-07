package com.example.attempt_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class BackgroundChangeActivity extends AppCompatActivity { // Экран для смены заднего фона в чате

    Button bFirst, bSecond, bThird, bFourth, bFifth, bSixth, bSeventh, bEighth, bNinth, bTenth, bEleventh, bTwelfth, b13, b14, b15;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dRef,schoolRef;
    private String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_change);
        bFirst = findViewById(R.id.btn_1);
        bSecond = findViewById(R.id.btn_2);
        bThird = findViewById(R.id.btn_3);
        bFourth = findViewById(R.id.btn_4);
        bFifth = findViewById(R.id.btn_5);
        bSixth = findViewById(R.id.btn_6);
        bSeventh = findViewById(R.id.btn_7);
        bEighth = findViewById(R.id.btn_8);
        bNinth = findViewById(R.id.btn_9);
        bTenth = findViewById(R.id.btn_10);
        bEleventh = findViewById(R.id.btn_11);
        bTwelfth = findViewById(R.id.btn_12);
        b13 = findViewById(R.id.btn_13);
        b14 = findViewById(R.id.btn_14);
        b15 = findViewById(R.id.btn_15);

        schoolRef = database.getReference("school/" + uid);

        schoolRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String sSchool = task.getResult().getValue().toString(); //получаю из БД школу пользователя

                dRef = database.getReference("users/" + sSchool + "/" + uid);

                bFirst.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("background", "no");
                        dRef.updateChildren(map);
                        Toast.makeText(BackgroundChangeActivity.this,"Background color successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BackgroundChangeActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                bSecond.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("background", "back_1");
                        dRef.updateChildren(map);
                        Toast.makeText(BackgroundChangeActivity.this,"Background color successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BackgroundChangeActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                bThird.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("background", "back_2");
                        dRef.updateChildren(map);
                        Toast.makeText(BackgroundChangeActivity.this,"Background color successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BackgroundChangeActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                bFourth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("background", "back_3");
                        dRef.updateChildren(map);
                        Toast.makeText(BackgroundChangeActivity.this,"Background color successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BackgroundChangeActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                bFifth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("background", "back_4");
                        dRef.updateChildren(map);
                        Toast.makeText(BackgroundChangeActivity.this,"Background color successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BackgroundChangeActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                bSixth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("background", "back_5");
                        dRef.updateChildren(map);
                        Toast.makeText(BackgroundChangeActivity.this,"Background color successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BackgroundChangeActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                bSeventh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("background", "back_6");
                        dRef.updateChildren(map);
                        Toast.makeText(BackgroundChangeActivity.this,"Background color successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BackgroundChangeActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                bEighth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("background", "back_7");
                        dRef.updateChildren(map);
                        Toast.makeText(BackgroundChangeActivity.this,"Background color successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BackgroundChangeActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                bNinth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("background", "back_8");
                        dRef.updateChildren(map);
                        Toast.makeText(BackgroundChangeActivity.this,"Background color successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BackgroundChangeActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                bTenth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("background", "back_9");
                        dRef.updateChildren(map);
                        Toast.makeText(BackgroundChangeActivity.this,"Background color successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BackgroundChangeActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                bEleventh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("background", "back_10");
                        dRef.updateChildren(map);
                        Toast.makeText(BackgroundChangeActivity.this,"Background color successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BackgroundChangeActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                bTwelfth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("background", "back_11");
                        dRef.updateChildren(map);
                        Toast.makeText(BackgroundChangeActivity.this,"Background color successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BackgroundChangeActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                b13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("background", "back_12");
                        dRef.updateChildren(map);
                        Toast.makeText(BackgroundChangeActivity.this,"Background color successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BackgroundChangeActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                b14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("background", "back_13");
                        dRef.updateChildren(map);
                        Toast.makeText(BackgroundChangeActivity.this,"Background color successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BackgroundChangeActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                b15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("background", "back_14");
                        dRef.updateChildren(map);
                        Toast.makeText(BackgroundChangeActivity.this,"Background color successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BackgroundChangeActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });

    }

    public void onBackPressed(){
        Intent intent = new Intent(BackgroundChangeActivity.this,SettingsActivity.class);
        startActivity(intent);
        finish();
    }

}
