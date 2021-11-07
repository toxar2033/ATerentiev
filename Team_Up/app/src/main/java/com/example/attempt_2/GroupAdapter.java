package com.example.attempt_2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupHolder> {

    ArrayList<String> groups;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String sClass, sSchool, sAccess, uid, name = "";
    DatabaseReference classRef, schoolRef, accessRef, nameRef;
    Context context;

    public GroupAdapter(Context ctx, ArrayList<String> gr, final String task1){ //Получаем на вход список групп
        this.groups = gr;
        this.context = ctx;
        this.uid = task1;

        schoolRef = database.getReference("school/" + uid);

        schoolRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                sSchool = task.getResult().getValue().toString();

                classRef = database.getReference("users/" + sSchool + "/" + uid + "/class");

                classRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task2) {
                        sClass = task2.getResult().getValue().toString();
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.groupitem, parent, false);
        return new GroupHolder(view);
    }

    @Override
    public void onBindViewHolder(final @NonNull GroupHolder holder,final int position) { //Выводим список групп
        holder.num.setText(groups.get(position));

        nameRef = database.getReference("users/" + sSchool + "/" + uid + "/names/" + groups.get(position));

        nameRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                name = task.getResult().getValue().toString();
                if(name.equals("")){
                    holder.num.setText(groups.get(position));
                }else{
                    holder.num.setText(name);
                }
            }
        });

        holder.cL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Переход в группу по нажатию на неё
                Intent intent = new Intent(context, DashboardActivity.class);
                intent.putExtra("group", groups.get(position));
                context.startActivity(intent);
            }
        });

        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Переход в настройки группы по нажатию на кнопку

                accessRef = database.getReference("groups/" + sSchool + "/" + sClass + "/" + groups.get(position) + "/showusers");
                accessRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        sAccess = task.getResult().getValue().toString();

                        switch (sAccess){
                            case "false":
                                Intent intent = new Intent(context, DemoGroupInfoActivity.class);
                                intent.putExtra("group", groups.get(position));
                                intent.putExtra("school", sSchool);
                                intent.putExtra("uid", uid);
                                intent.putExtra("class", sClass);
                                context.startActivity(intent);
                                break;
                            case "true":
                                Intent fintent = new Intent(context, FullGroupInfoActivity.class);
                                fintent.putExtra("group", groups.get(position));
                                fintent.putExtra("school", sSchool);
                                fintent.putExtra("uid", uid);
                                fintent.putExtra("class", sClass);
                                context.startActivity(fintent);
                                break;
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class GroupHolder extends RecyclerView.ViewHolder {

        TextView num;
        ConstraintLayout cL;
        ImageView info;

        public GroupHolder(@NonNull View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.group_num);
            cL = itemView.findViewById(R.id.gr_cl);
            info = itemView.findViewById(R.id.btn_groupinfo);
        }
    }
}
