package com.example.attempt_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class FullGroupInfoActivity extends AppCompatActivity { //Экран информации о группе со списком пользователей

    String sSchool, uid, sClass, group, groupname;
    ArrayList<String> users = new ArrayList<>();
    TextView tv;
    EditText et;
    Button btn;
    RecyclerView rv;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef, nameRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_group_info);
        tv = findViewById(R.id.t_groupnumfull);
        et = findViewById(R.id.et_groupnamefull);
        btn = findViewById(R.id.btn_changegroupnamefull);
        rv = findViewById(R.id.rec_groupusers);

        getData();

        tv.setText(group); //Выставляем номер группы

        final GroupUserAdapter adapter = new GroupUserAdapter(this, users, sSchool, sClass, group); //Создаём адаптер для отображения пользователей группы

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        nameRef = database.getReference("users/" + sSchool + "/" + uid + "/names");
        usersRef = database.getReference("groups/" + sSchool + "/" + sClass + "/" + group + "/users");

        nameRef.child(group).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                groupname = task.getResult().getValue().toString();
                et.setText(groupname); //Получаем имя группы для пользователя из БД
            }
        });

        usersRef.addChildEventListener(new ChildEventListener() { //Получаем список пользователей
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String tmp = snapshot.getValue().toString();
                users.add(tmp);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Меняем имя группы для пользователя
                String tmp = et.getText().toString();
                if (tmp.length() > 25){
                    Toast.makeText(getApplicationContext(),"Group name should be shorter than 25 signs!",Toast.LENGTH_SHORT).show();
                }else{
                    HashMap map = new HashMap();
                    map.put(group,tmp);
                    nameRef.updateChildren(map);
                }
            }
        });
    }

    public void getData(){
        sSchool = getIntent().getStringExtra("school");
        uid = getIntent().getStringExtra("uid");
        sClass = getIntent().getStringExtra("class");
        group = getIntent().getStringExtra("group");
    }
}
