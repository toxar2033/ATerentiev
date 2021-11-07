package com.example.attempt_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GroupUserAdapter extends RecyclerView.Adapter<GroupUserAdapter.GroupUserHolder> {

    String sSchool, sClass, group;
    ArrayList<String> users;
    Context context;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference fioRef;

    public GroupUserAdapter(Context ctx, ArrayList<String> task1, String task2, String task3, String task4){ //Получаем список пользователей
        this.context = ctx;
        this.users = task1;
        this.sSchool = task2;
        this.sClass = task3;
        this.group = task4;
    }

    @NonNull
    @Override
    public GroupUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.useritem, parent, false);
        return new GroupUserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GroupUserHolder holder, int position) { //Выводим список участников группы
        fioRef = database.getReference("users/" + sSchool + "/" + users.get(position) + "/FIO");

        fioRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String tmp1 = task.getResult().getValue().toString();
                String tmp2[] = tmp1.split(" ");
                holder.tSurname.setText(tmp2[0]);
                holder.tName.setText(tmp2[1]);
                holder.tLastname.setText(tmp2[2]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class GroupUserHolder extends RecyclerView.ViewHolder {

        TextView tName, tSurname, tLastname;

        public GroupUserHolder(@NonNull View itemView) {
            super(itemView);
            tName = itemView.findViewById(R.id.t_username);
            tSurname = itemView.findViewById(R.id.t_usersurname);
            tLastname = itemView.findViewById(R.id.t_userlastname);
        }
    }
}
