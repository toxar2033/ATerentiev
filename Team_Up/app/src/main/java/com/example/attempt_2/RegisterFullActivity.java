package com.example.attempt_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterFullActivity extends AppCompatActivity { // Второй экран регистрации

    EditText e_name, e_surname, e_lastname, e_school, e_class;
    Button btn_apply;
    String sUID, sMail;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference regRef, schoolRef, groupRef;
    String sName, sSurname, sLastname, sSchool, sClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_full);

        e_name = findViewById(R.id.e_name);
        e_surname = findViewById(R.id.e_surname);
        e_lastname = findViewById(R.id.e_lastname);
        e_school = findViewById(R.id.e_school);
        e_class = findViewById(R.id.e_class);
        btn_apply = findViewById(R.id.btn_applyregister);

        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Дополнение информации о пользователе в БД

                getData();

                sName = e_name.getText().toString();
                sSurname = e_surname.getText().toString();
                sLastname = e_lastname.getText().toString();
                sSchool = e_school.getText().toString();
                sClass = e_class.getText().toString();

                if(TextUtils.isEmpty(sName)){
                    e_name.setError("Enter your Name");
                    return;
                }
                else if(TextUtils.isEmpty(sSurname)){
                    e_surname.setError("Enter your Surname");
                    return;
                }
                else if(TextUtils.isEmpty(sSchool)){
                    e_school.setError("Enter your School");
                    return;
                }
                else if(TextUtils.isEmpty(sClass)){
                    e_class.setError("Enter your Class");
                    return;
                }else{
                    sSurname = sSurname.trim();
                    sName = sName.trim();
                    sLastname = sLastname.trim();
                    sSchool = sSchool.trim();
                    sClass = sClass.trim();

                    groupRef = database.getReference("groups/" + sSchool + "/" + sClass + "/0/users");
                    groupRef.push().setValue(sUID);

                    regRef = database.getReference("users/" + sSchool + "/" + sUID);
                    schoolRef = database.getReference("school");

                    HashMap map = new HashMap();
                    map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/common_avatar.png?alt=media&token=d6f193c9-d22e-46d6-920a-a42117b03d6d");
                    map.put("mail", sMail);
                    map.put("FIO", sSurname + " " + sName + " " + sLastname);
                    map.put("class", sClass);
                    HashMap groups = new HashMap();
                    groups.put("zzzzzzzzzzzzz", "0");
                    HashMap names = new HashMap();
                    names.put("0", "");
                    map.put("names",names);
                    map.put("groups", groups);
                    map.put("background", "No");
                    regRef.setValue(map);

                    map.clear();
                    map.put(sUID,sSchool);
                    schoolRef.updateChildren(map);
                    Intent intent = new Intent(RegisterFullActivity.this,MainMenuActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void getData(){
        if(getIntent().hasExtra("uid") && getIntent().hasExtra("mail")){
            sUID = getIntent().getStringExtra("uid");
            sMail = getIntent().getStringExtra("mail");
        }
    }

    public void onBackPressed(){
        Toast.makeText(getApplicationContext(), "Пожалуйста, закончите регистрацию! \nЕсли вы не завершите регистрацию, то не сможете использовать приложение", Toast.LENGTH_SHORT).show();
    }
}
