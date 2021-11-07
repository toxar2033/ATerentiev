package com.example.attempt_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainMenuActivity extends AppCompatActivity { //Главное меню с кнопками настройки и чатов, также здесь отображается аватар пользователя

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Button open_chats, settings;
    TextView hi_msg;
    String mAvatar;
    ImageView iAva;
    private String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference avaRef, schoolRef, nameRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        open_chats = findViewById(R.id.open);
        settings = findViewById(R.id.settings);
        hi_msg = findViewById(R.id.hiMessage);
        iAva = findViewById(R.id.avatar);

        schoolRef = database.getReference("school/" + uid);

        schoolRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String tmp = task.getResult().getValue().toString();

                avaRef = database.getReference("users/" + tmp + "/" + uid + "/avatar");
                nameRef = database.getReference("users/" + tmp + "/" + uid + "/FIO");

                avaRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) { //Получениа аватара из БД
                        mAvatar = task.getResult().getValue().toString();
                        Glide.with(getApplicationContext()).load(mAvatar).into(iAva);
                    }
                });

                nameRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) { //Получение имени из БД
                        String tmp1 = task.getResult().getValue().toString();
                        String tmp2[] = tmp1.split(" ");
                        hi_msg.setText(tmp2[1]);
                    }
                });
            }
        });

        open_chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Кнопка для открытия экрана групп
                Intent intent = new Intent(MainMenuActivity.this,GroupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Кнопка для открытия экрана настроек
                Intent intent = new Intent(MainMenuActivity.this,SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
