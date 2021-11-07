package com.example.attempt_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity { //Экран входа и первый экран, который видит пользователь

    private EditText emailEt,passwordEt;
    private Button SignInButton;
    private TextView SignUpTv, t_help;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    public String email_all;
    private String FILELOGIN = "file_login";
    private String FILEPASS = "file_pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        emailEt = findViewById(R.id.email);
        passwordEt = findViewById(R.id.password);
        SignInButton = findViewById(R.id.login);
        t_help = findViewById(R.id.t_help);
        progressDialog = new ProgressDialog(this);
        SignUpTv = findViewById(R.id.signUpTv);

        try {
            InputStream inputStream = openFileInput(FILELOGIN);
            if (inputStream != null){
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                String tmp = reader.readLine();
                emailEt.setText(tmp);
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } //Если пользователь до этого входил, то этот код автоматически вставит его логин в полее ввода

        try {
            InputStream inputStream = openFileInput(FILEPASS);
            if (inputStream != null){
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                String tmp = reader.readLine();
                passwordEt.setText(tmp);
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } //Если пользователь до этого входил, то этот код автоматически вставит его пароль в полее ввода

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        SignUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Вход в приложение
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
        t_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Переход на экран проблем с регистрацией(помощь)
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void Login(){ //метод для входа в приложение
        String email = emailEt.getText().toString();
        email_all = email;
        final String password = passwordEt.getText().toString();
        if(TextUtils.isEmpty(email)){
            emailEt.setError("Enter your Email");
            return;
        }
        else if(TextUtils.isEmpty(password)){
            passwordEt.setError("Enter your password");
            return;
        }

        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Successfully registrated",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,MainMenuActivity.class);
                    try {
                        FileOutputStream fos = openFileOutput(FILELOGIN, Context.MODE_PRIVATE);
                        String mystring = String.valueOf(email_all);
                        fos.write(mystring.getBytes());
                        fos.close();
                    }catch (Throwable t){
                    }
                    try {
                        FileOutputStream fos = openFileOutput(FILEPASS, Context.MODE_PRIVATE);
                        String mystring = String.valueOf(password);
                        fos.write(mystring.getBytes());
                        fos.close();
                    }catch (Throwable t){
                    }
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this,"Sign in fail!",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
    private Boolean isValidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
