package com.example.attempt_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class NotesActivity extends AppCompatActivity { //Заметки о чате

    EditText etMainInfo, etCharacter, etInterests, etHobbys;
    Button btnSave;
    String  group, sSchool, sClass;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dRef, schoolRef, classRef;
    private String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        etMainInfo = findViewById(R.id.mainInfo_ET);
        etCharacter = findViewById(R.id.character_ET);
        etInterests = findViewById(R.id.interests_ET);
        etHobbys = findViewById(R.id.hobbys_ET);
        btnSave = findViewById(R.id.btnHobbys);

        getData();

        schoolRef = database.getReference("school/" + uid);

        schoolRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                sSchool = task.getResult().getValue().toString();

                classRef = database.getReference("users/" + sSchool + "/" + uid + "/class");

                classRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        sClass = task.getResult().getValue().toString();

                        dRef = database.getReference("groups/" + sSchool + "/" + sClass + "/" + group +"/notes/" + uid);

                        dRef.addListenerForSingleValueEvent(new ValueEventListener() { //Получение заметок из БД
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                dRef.child("maininfo").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        String text = task.getResult().getValue().toString();
                                        etMainInfo.setText(text);
                                    }
                                });

                                dRef.child("interests").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        String text = task.getResult().getValue().toString();
                                        etInterests.setText(text);
                                    }
                                });

                                dRef.child("character").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        String text = task.getResult().getValue().toString();
                                        etCharacter.setText(text);
                                    }
                                });

                                dRef.child("hobbys").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        String text = task.getResult().getValue().toString();
                                        etHobbys.setText(text);
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        btnSave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) { //Загрузка заметок в БД после нажатия пользователем кнопки
                                String text1 = etMainInfo.getText().toString();
                                String text2 = etCharacter.getText().toString();
                                String text3 = etInterests.getText().toString();
                                String text = etHobbys.getText().toString();
                                HashMap map = new HashMap();
                                map.put("maininfo", text1);
                                map.put("character", text2);
                                map.put("interests", text3);
                                map.put("hobbys", text);
                                dRef.updateChildren(map);
                            }
                        });
                    }
                });
            }
        });

    }

    public void onBackPressed(){
        Intent intent = new Intent(NotesActivity.this,GroupActivity.class);
        startActivity(intent);
        finish();
    }

    public void getData(){
        if(getIntent().hasExtra("group")){
            group = getIntent().getStringExtra("group");
        }
    }
}
