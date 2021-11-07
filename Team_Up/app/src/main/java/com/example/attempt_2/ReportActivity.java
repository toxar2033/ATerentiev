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

public class ReportActivity extends AppCompatActivity { //Экран жалобы на сообщение

    Button send;
    EditText report;
    String sSchool, sClass, group, uid, number, msg;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reportRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        send = findViewById(R.id.btn_sendreport);
        report = findViewById(R.id.et_reporttext);

        getData();

        reportRef = database.getReference("reports");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Отправка жалобы на сообщение
                String tmp = report.getText().toString();
                if (!tmp.equals("")){
                    String report = uid + "/" + tmp + "/" + sSchool + "/" + sClass + "/" + group + "/" + number + "/" + msg;
                    reportRef.push().setValue(report);
                    Toast.makeText(getApplicationContext(), "Жалоба отправлена и скоро будет рассмотрена", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ReportActivity.this, MainMenuActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Пожалуйста, введите жалобу!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getData(){
        sSchool = getIntent().getStringExtra("school");
        sClass = getIntent().getStringExtra("class");
        group = getIntent().getStringExtra("group");
        msg = getIntent().getStringExtra("msg");
        uid = getIntent().getStringExtra("uid");
        number = getIntent().getStringExtra("number");
    }
}
