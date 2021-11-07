package com.example.attempt_2;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileOutputStream;
import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity { // Первый экран регистрации
    private EditText emailEt,passwordEt1,passwordEt2;
    private Button SignUpButton;
    private TextView SignInTv;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private String FILELOGIN = "file_login";
    private String FILEPASS = "file_pass";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        firebaseAuth = FirebaseAuth.getInstance();
        emailEt = findViewById(R.id.email);
        passwordEt1 = findViewById(R.id.password);
        passwordEt2 = findViewById(R.id.password2);
        SignUpButton = findViewById(R.id.register);
        progressDialog = new ProgressDialog(this);
        SignInTv = findViewById(R.id.signInTv);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
        SignInTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //регистрация в БД
                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void Register(){ //функция для регистрации нового пользователя в БД
        final String email = emailEt.getText().toString();
        final String password1 = passwordEt1.getText().toString();
        String password2 = passwordEt2.getText().toString();
        if(TextUtils.isEmpty(email)){
            emailEt.setError("Enter your Email");
            return;
        }
        else if(TextUtils.isEmpty(password1)){
            passwordEt1.setError("Enter your password");
            return;
        }
        else if(TextUtils.isEmpty(password2)){
            passwordEt2.setError("Confirm your password");
            return;
        }
        else if(!password1.equals(password2)){
            passwordEt2.setError("Different password");
            return;
        }
        else if(password1.length() < 4){
            passwordEt1.setError("Length of the password should be more than 4 characters");
            return;
        }
        else if(!isValidEmail(email)){
            emailEt.setError("Invalid email");
            return;
        }

        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUpActivity.this,"Successfully registrated",Toast.LENGTH_LONG).show();
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    dRef = database.getReference("users/" + uid);
                    Intent intent = new Intent(SignUpActivity.this,RegisterFullActivity.class);
                    intent.putExtra("uid", uid);
                    intent.putExtra("mail", email);
                    try {
                        FileOutputStream fos = openFileOutput(FILELOGIN, Context.MODE_PRIVATE);
                        String mystring = String.valueOf(email);
                        fos.write(mystring.getBytes());
                        fos.close();
                    }catch (Throwable t){
                    }
                    try {
                        FileOutputStream fos = openFileOutput(FILEPASS, Context.MODE_PRIVATE);
                        String mystring = String.valueOf(password1);
                        fos.write(mystring.getBytes());
                        fos.close();
                    }catch (Throwable t){
                    }
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(SignUpActivity.this,"Sign up fail!",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
    private Boolean isValidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
