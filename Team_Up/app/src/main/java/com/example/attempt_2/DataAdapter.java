package com.example.attempt_2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<ViewHolder> { //Этот адаптер отвечает за заполнение сообщений

    ArrayList<String> messages, numbers;
    DatabaseReference mgroup = FirebaseDatabase.getInstance().getReference();
    LayoutInflater inflater;
    Context context;
    private String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference schoolRef;
    String sSchool, sClass, group;

    public DataAdapter(Context ctx, ArrayList<String> messages, ArrayList<String> task1, String task2, String task3, String task4, String task5) {
        this.messages = messages;
        this.inflater = LayoutInflater.from(ctx);
        this.context = ctx;
        this.numbers = task1;
        this.group = task4;
    } //здесь мы получаем всю информацию для сообщений

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        String msg = messages.get(position);
        final String mes[] = msg.split("\n");
        holder.message.setText(mes[2]);
        holder.time.setText(mes[1]);

        if(uid.equals(mes[0])) holder.constraintLayout.setBackgroundResource(R.drawable.messageviewuser); //Проверяю этот ли пользователь написал это сообщение, если да то окрашиваю сообщение в другой цвет

        schoolRef = database.getReference("school/" + uid);

        schoolRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String sSchool = task.getResult().getValue().toString();

                mgroup.child("users").child(sSchool).child(mes[0]).child("avatar").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) { //Загружаю аватар пользователя
                        String mAvatar = task.getResult().getValue().toString();
                        Glide.with(context).load(mAvatar).into(holder.avatar);
                    }
                });
            }
        });

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //переход на экран настройки сообщения
                Intent intent = new Intent(context, MessageSettingsActivity.class);
                intent.putExtra("group", group);
                intent.putExtra("number", numbers.get(position));
                intent.putExtra("msg", messages.get(position));
                intent.putExtra("uid", uid);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
