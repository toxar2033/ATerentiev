package com.example.attempt_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DemoGroupInfoActivity extends AppCompatActivity { //Экран информации о группе без списка пользователей

    EditText group_name;
    TextView group_num;
    Button btn_name;
    String sSchool, uid, sClass, group, groupname;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference nameRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_group_info);
        group_name = findViewById(R.id.et_groupnamefull);
        group_num = findViewById(R.id.groupname);
        btn_name = findViewById(R.id.btn_changename);

        getData();

        group_num.setText(group); //Выставляем номер группы

        nameRef = database.getReference("users/" + sSchool + "/" + uid + "/names");

        nameRef.child(group).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                groupname = task.getResult().getValue().toString();
                group_name.setText(groupname); //Получаем имя группы для пользователя из БД
            }
        });

        btn_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Меняем имя группы для пользователя
                String tmp;
                tmp = group_name.getText().toString();
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
