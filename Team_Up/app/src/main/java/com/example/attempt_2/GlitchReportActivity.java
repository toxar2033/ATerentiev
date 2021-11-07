package com.example.attempt_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GlitchReportActivity extends AppCompatActivity { //Экран отправления репорта о баге

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference glitchRef;
    Button send;
    EditText text, et_mail;
    String sSchool, uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glitch_report);
        send = findViewById(R.id.btn_glitchsend);
        text = findViewById(R.id.et_glitchtext);
        et_mail = findViewById(R.id.et_reportmail);

        getData();

        glitchRef = database.getReference("glitchreports");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Отправление репорта о баге
                String tmp = text.getText().toString();
                String mail = et_mail.getText().toString();
                if(!tmp.isEmpty() && !mail.isEmpty()){
                    String msg = sSchool + "/" + uid + "/" + mail + "/" + tmp;
                    glitchRef.push().setValue(msg);
                    Intent intent = new Intent(GlitchReportActivity.this, MainMenuActivity.class);
                    startActivity(intent);
                    finish();
                }else if (tmp.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Введите текст обращения!",Toast.LENGTH_SHORT).show();
                } else if (mail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Введите почту для обратной связи!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getData(){
        uid = getIntent().getStringExtra("uid");
        sSchool = getIntent().getStringExtra("school");
    }
}
