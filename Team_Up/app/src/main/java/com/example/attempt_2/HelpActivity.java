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

public class HelpActivity extends AppCompatActivity { //Здесь пользователь отправляет запрос на восстановление аккаунта

    EditText mail, msg;
    Button send;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference helpRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        mail = findViewById(R.id.et_mailhelp);
        msg = findViewById(R.id.et_helptext);
        send = findViewById(R.id.btn_sendhelp);

        helpRef = database.getReference("help");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sMail = mail.getText().toString();
                String sMSG = msg.getText().toString();
                if(!sMail.isEmpty() && !sMSG.isEmpty()){
                    String tmp = sMail + "/" + sMSG;
                    helpRef.push().setValue(tmp);
                    Toast.makeText(getApplicationContext(), "Заявка отправлена", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HelpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else{
                    Toast.makeText(getApplicationContext(), "Заявка не может быть пустой!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
