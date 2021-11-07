package com.example.attempt_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity { // Экран чата

    private FirebaseAuth mAuth;
    private static int MAX_MESSAGE_LENGTH = 500;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    String group, sSchool, sClass, sAllow;
    DatabaseReference myRef, bkgrRef, schoolRef, classRef, allowRef;


    EditText editTextMessage;
    LinearLayout lBack;
    Button sendButton, btnNotes;
    RecyclerView messagesRecycler;
    ArrayList<String> messages = new ArrayList<>();
    ArrayList<String> numbers = new ArrayList<>();
    Date currentDate;
    DateFormat dateFormat;
    String dateText;
    String timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        btnNotes = findViewById(R.id.btn_notes);
        mAuth = FirebaseAuth.getInstance();
        sendButton = findViewById(R.id.send_message_b);
        editTextMessage = findViewById(R.id.message_input);
        lBack = findViewById(R.id.ll_chatbcgr);
        messagesRecycler = findViewById(R.id.message_recycler);

        getData();

        messagesRecycler.setLayoutManager(new LinearLayoutManager(this));

        final DataAdapter dataAdapter = new DataAdapter(this, messages, numbers, sSchool, sClass, group, uid); //создаю адаптер для отображения сообщений

        messagesRecycler.setAdapter(dataAdapter); //подключаю адаптер для отображения сообщений

        schoolRef = database.getReference("school/" + uid);

        schoolRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                sSchool = task.getResult().getValue().toString(); //получаю из БД школу пользователя

                classRef = database.getReference("users/" + sSchool + "/" + uid + "/class");

                classRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        sClass = task.getResult().getValue().toString(); //получаю из БД класс пользователя

                        allowRef = database.getReference("groups/" + sSchool + "/" + sClass + "/" + group + "/allowtowrite");

                        bkgrRef = database.getReference("users/" + sSchool + "/" + uid +"/background");

                        bkgrRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                String tmp = task.getResult().getValue().toString(); //получаю задний фон чата из БД
                                switch (tmp){
                                    case"no":
                                        lBack.setBackgroundColor(Integer.parseInt("#ffffff"));
                                        break;
                                    case "back_1":
                                        lBack.setBackgroundResource(R.drawable.background_1);
                                        break;
                                    case "back_2":
                                        lBack.setBackgroundResource(R.drawable.background_2);
                                        break;
                                    case "back_3":
                                        lBack.setBackgroundResource(R.drawable.background_3);
                                        break;
                                    case "back_4":
                                        lBack.setBackgroundResource(R.drawable.background_4);
                                        break;
                                    case "back_5":
                                        lBack.setBackgroundResource(R.drawable.background_5);
                                        break;
                                    case "back_6":
                                        lBack.setBackgroundResource(R.drawable.background_6);
                                        break;
                                    case "back_7":
                                        lBack.setBackgroundResource(R.drawable.background_7);
                                        break;
                                    case "back_8":
                                        lBack.setBackgroundResource(R.drawable.background_8);
                                        break;
                                    case "back_9":
                                        lBack.setBackgroundResource(R.drawable.background_9);
                                        break;
                                    case "back_10":
                                        lBack.setBackgroundResource(R.drawable.background_10);
                                        break;
                                    case "back_11":
                                        lBack.setBackgroundResource(R.drawable.background_11);
                                        break;
                                    case "back_12":
                                        lBack.setBackgroundResource(R.drawable.background_12);
                                        break;
                                    case "back_13":
                                        lBack.setBackgroundResource(R.drawable.background_13);
                                        break;
                                    case "back_14":
                                        lBack.setBackgroundResource(R.drawable.background_14);
                                        break;
                                }

                            }
                        });

                        // Текущее время
                        currentDate = new Date();
                        // Форматирование времени как "день.месяц.год"
                        dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                        dateText = dateFormat.format(currentDate);
                        // Форматирование времени как "часы:минуты:секунды"
                        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                        timeText = timeFormat.format(currentDate);

                        myRef = database.getReference("groups/" + sSchool + '/' + sClass + "/" + group + "/messages");
                        myRef.addChildEventListener(new ChildEventListener() { //получаю из БД сообщения этой группы
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                String msg = dataSnapshot.getValue(String.class);
                                String num = dataSnapshot.getKey().toString();
                                messages.add(msg);
                                numbers.add(num);
                                dataAdapter.notifyDataSetChanged();
                                messagesRecycler.smoothScrollToPosition(messages.size());
                            }
                            @Override
                            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                dataAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                                dataAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                dataAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                dataAdapter.notifyDataSetChanged();
                            }
                        });

                        sendButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) { // Загружаю сообщения в БД

                                allowRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        sAllow = task.getResult().getValue().toString(); //проверяю, может ли пользователь писать в чат
                                    }
                                });
                                
                                if (sAllow.equals("true")) {
                                    String msg = editTextMessage.getText().toString();
                                    if (msg.equals("")) {
                                        Toast.makeText(getApplicationContext(), "Введите сообщение!", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                    if (msg.length() > MAX_MESSAGE_LENGTH) {
                                        Toast.makeText(getApplicationContext(), "Слишком длинное сообщение!", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                    msg = uid + '\n' + dateText + '/' + timeText + '\n' + msg;
                                    myRef.push().setValue(msg); //здесь сообщение загружается в БД
                                    editTextMessage.setText("");
                                } else {
                                    Toast.makeText(getApplicationContext(), "Вы не можете отправлять сюда сообщения!(Заблокировано админом)", Toast.LENGTH_SHORT).show();
                                    editTextMessage.setText("");
                                }
                            }
                        });

                        btnNotes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) { // переход на экран заметок
                                if(sAllow.equals("true")) {
                                    Intent intent = new Intent(DashboardActivity.this, NotesActivity.class);
                                    intent.putExtra("group", group);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getApplicationContext(), "Вы не можете создавать/просматривать заметки для этого чата!(Заблокировано админом)", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });

    }

    public void onBackPressed(){
        Intent intent = new Intent(DashboardActivity.this,GroupActivity.class);
        startActivity(intent);
        finish();
    }

    public void getData(){
        if (getIntent().hasExtra("group")){
            group = getIntent().getStringExtra("group");
        }
    }
}
