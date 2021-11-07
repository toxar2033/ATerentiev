package com.example.attempt_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MessageSettingsActivity extends AppCompatActivity {

    String sSchool, sClass, group, msg, number, uid;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mesRef, schoolRef, classRef;
    Button btn_delete, btn_change, btn_report;
    EditText et_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Настройки сообщений
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_settings);
        btn_delete = findViewById(R.id.btn_msgdelete);
        btn_change = findViewById(R.id.btn_msgchange);
        btn_report = findViewById(R.id.btn_msgreport);
        et_msg = findViewById(R.id.et_msgtext);

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

                        mesRef = database.getReference("groups/" + sSchool + "/" + sClass + "/" + group + "/messages");
                        final String message[] = msg.split("\n");

                        et_msg.setText(message[2]);

                        btn_delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) { //Удаление сообщения, если этот пользователь его написал
                                if(uid.equals(message[0])){
                                    mesRef.child(number).removeValue();
                                    et_msg.setText("");
                                    Intent intent = new Intent(MessageSettingsActivity.this, DashboardActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else{
                                    Toast.makeText(getApplicationContext(), "Вы не можете удалять чужие сообщения!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        btn_change.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) { //Изменение сообщения, если этот пользователь его написал
                                if(uid.equals(message[0])){
                                    String tmp = et_msg.getText().toString();
                                    if(tmp.equals("")){
                                        Toast.makeText(getApplicationContext(), "Введите сообщение!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        tmp = message[0] + '\n' + message[1] + '\n' + tmp;
                                        HashMap map = new HashMap();
                                        map.put(number, tmp);
                                        mesRef.updateChildren(map);
                                    }
                                } else{
                                    Toast.makeText(getApplicationContext(), "Вы не можете изменять чужие сообщения!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        btn_report.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) { //Написание жалобы на это сообщение
                                Intent intent = new Intent(MessageSettingsActivity.this, ReportActivity.class);
                                intent.putExtra("school", sSchool);
                                intent.putExtra("class", sClass);
                                intent.putExtra("msg", msg);
                                intent.putExtra("number", number);
                                intent.putExtra("uid", uid);
                                intent.putExtra("group", group);
                                startActivity(intent);
                            }
                        });
                    }
                });
            }
        });
    }

    public void getData(){
        group = getIntent().getStringExtra("group");
        msg = getIntent().getStringExtra("msg");
        number = getIntent().getStringExtra("number");
        uid = getIntent().getStringExtra("uid");
    }

    public void onBackPressed(){
        Intent intent = new Intent(MessageSettingsActivity.this,MainMenuActivity.class);
        startActivity(intent);
        finish();
    }
}
