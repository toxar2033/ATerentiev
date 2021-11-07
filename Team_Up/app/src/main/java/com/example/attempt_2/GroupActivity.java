package com.example.attempt_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

public class GroupActivity extends AppCompatActivity { //Экран со списком групп

    ArrayList<String> groups = new ArrayList<>();

    RecyclerView recView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference groupRef, schoolRef, nameRef;
    String sSchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        recView = findViewById(R.id.group_res);

        final GroupAdapter groupAdapter = new GroupAdapter(this, groups, uid); // адаптер для отображения сипска групп
        recView.setAdapter(groupAdapter);
        recView.setLayoutManager(new LinearLayoutManager(this));

        schoolRef = database.getReference("school/" + uid);

        schoolRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                sSchool = task.getResult().getValue().toString();

                groupRef = database.getReference("users/" + sSchool + "/" + uid + "/groups");

                nameRef = database.getReference("users/" + sSchool + "/" + uid + "/names");
                nameRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        groupAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        groupAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                        groupAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        groupAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        groupAdapter.notifyDataSetChanged();
                    }
                });

                groupRef.addChildEventListener(new ChildEventListener() { //Получаем список групп
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        String group = snapshot.getValue(String.class);
                        groups.add(group);
                        groupAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        groupAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                        groupAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        groupAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        groupAdapter.notifyDataSetChanged();
                    }
                });
            }
        });


    }

    public void onBackPressed(){
        Intent intent = new Intent(GroupActivity.this,MainMenuActivity.class);
        startActivity(intent);
        finish();
    }
}
