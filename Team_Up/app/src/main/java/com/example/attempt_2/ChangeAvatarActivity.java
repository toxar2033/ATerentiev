package com.example.attempt_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ChangeAvatarActivity extends AppCompatActivity { //Экран для смены аватара пользователя

    Button com, fir, sec, thir, four, fiv, six, sev, eight, nine, ten, eleven, twelve, thirt, fourt,
            fift, sixt, sevent, eightt, ninet, twent, twone, twtwo, twthree;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dRef , schoolRef;
    private String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_avatar);
        com = findViewById(R.id.common);
        fir = findViewById(R.id.btn_first);
        sec = findViewById(R.id.btn_second);
        thir = findViewById(R.id.btn_third);
        four = findViewById(R.id.btn_fourth);
        fiv = findViewById(R.id.btn_fivth);
        six = findViewById(R.id.btn_sixth);
        sev = findViewById(R.id.btn_seven);
        eight = findViewById(R.id.btn_8);
        nine = findViewById(R.id.btn_9);
        ten = findViewById(R.id.btn_10);
        eleven = findViewById(R.id.btn_11);
        twelve = findViewById(R.id.btn_12);
        thirt = findViewById(R.id.btn_13);
        fourt = findViewById(R.id.btn_14);
        fift = findViewById(R.id.btn_15);
        sixt = findViewById(R.id.btn_16);
        sevent = findViewById(R.id.btn_17);
        eightt = findViewById(R.id.btn_18);
        ninet = findViewById(R.id.btn_19);
        twent = findViewById(R.id.btn_20);
        twone = findViewById(R.id.btn_21);
        twtwo = findViewById(R.id.btn_22);
        twthree = findViewById(R.id.btn_23);

        schoolRef = database.getReference("school/" + uid);

        schoolRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String sSchool = task.getResult().getValue().toString(); //получаю из БД школу пользователя

                dRef = database.getReference("users/" + sSchool + "/" + uid);

                com.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/common_avatar.png?alt=media&token=d6f193c9-d22e-46d6-920a-a42117b03d6d");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                fir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_1.png?alt=media&token=c81d5284-ae1c-4e97-a9b2-5980568381f1");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                sec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_2.png?alt=media&token=bf336516-b01b-40c8-96be-11c4a2c828f0");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                thir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_3.png?alt=media&token=72e7fabd-999f-4cef-8a17-4a1a82ebeea5");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                four.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_4.jpg?alt=media&token=63b54de0-aeaf-485c-bb0d-2eba06807199");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                fiv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_5.jpg?alt=media&token=f3062b44-086b-4862-abd0-7efc78c034d0");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                six.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_6.jpg?alt=media&token=0e759b17-63f9-4adc-989c-03b604f37956");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                sev.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_7.jpg?alt=media&token=ddf2cfec-dc67-426c-9ed6-9f88649f727b");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                eight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_8.jpg?alt=media&token=2a4c81f9-43e4-417d-ad43-ea9a69a3acba");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                nine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_9.jpg?alt=media&token=ca203887-5cd1-4a80-a94d-cf40f2d6c69a");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                ten.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_10.jpg?alt=media&token=ce5d5559-4dbc-4576-8df2-4a0ac6d87a12");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                eleven.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_11.jpg?alt=media&token=aa5ef42f-7342-4168-97b1-ca8c54a0f7c9");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                twelve.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_12.jpg?alt=media&token=3b62b39d-725c-4f0d-ae36-9dc586196e25");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                thirt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_13.jpg?alt=media&token=37ded6da-5cb5-47dc-8480-370f28d3355c");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                fourt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_14.jpg?alt=media&token=9ea11200-92c1-43ea-92fa-8e36bbb18043");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                fift.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_15.jpg?alt=media&token=5bfa76fb-286f-47a5-83d9-6190e397ca98");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                sixt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_16.png?alt=media&token=04821aa4-1533-4774-8618-8dedcb609ae2");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                sevent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_17.png?alt=media&token=4ba837ca-1fbe-4224-8a7d-5ae905c4c353");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                eightt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_18.jpg?alt=media&token=d81793f6-489e-491a-815f-3e0142477218");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                ninet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_19.jpg?alt=media&token=99f14d6b-8ae3-4c26-a55e-ffd63fe7576b");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                twent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_20.jpg?alt=media&token=c644bf97-4949-4de3-b94c-9c7aefa01bea");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                twone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_21.jpg?alt=media&token=2c8ee166-2959-415f-8be6-7971c6c3eaf0");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                twtwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_22.png?alt=media&token=6c177ddb-1442-43f5-a91f-eb054258bf11");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                twthree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap map = new HashMap();
                        map.put("avatar", "https://firebasestorage.googleapis.com/v0/b/attempt-1-1901c.appspot.com/o/avatar_23.jpg?alt=media&token=5f8efd31-9cb1-4236-8d9d-e8972f9063b2");
                        dRef.updateChildren(map);
                        Toast.makeText(ChangeAvatarActivity.this,"Avatar successfully changed!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangeAvatarActivity.this,MainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent(ChangeAvatarActivity.this,SettingsActivity.class);
        startActivity(intent);
        finish();
    }

}
