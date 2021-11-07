package com.example.attempt_2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView message, time;
    ImageView avatar;
    ConstraintLayout constraintLayout;

    public ViewHolder(View itemView){
        super(itemView);
        message = itemView.findViewById(R.id.message_item);
        time = itemView.findViewById(R.id.time_date);
        avatar = itemView.findViewById(R.id.avatar_img);
        constraintLayout = itemView.findViewById(R.id.cl_message);
    }
}
