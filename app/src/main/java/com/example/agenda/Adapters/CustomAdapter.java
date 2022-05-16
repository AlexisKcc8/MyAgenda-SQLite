package com.example.agenda.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.R;
import com.example.agenda.UpdateActivity;

import java.text.BreakIterator;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final Context context;
    private final Activity activity;
    private ArrayList user_id, user_name, user_last, user_category, user_phone, user_date, user_time;

    public CustomAdapter(Activity activity, Context context, ArrayList user_id, ArrayList user_name, ArrayList user_last,
                         ArrayList user_phone, ArrayList user_category,  ArrayList user_date, ArrayList user_time){
        this.activity = activity;
        this.context = context;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_last = user_last;
        this.user_phone = user_phone;
        this.user_category = user_category;
        this.user_date = user_date;
        this.user_time = user_time;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return  new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final  int position) {

        holder.user_id_txt.setText(String.valueOf(user_id.get(position)));
        holder.user_name_txt.setText(String.valueOf(user_name.get(position)));
        holder.user_last_txt.setText(String.valueOf(user_last.get(position)));
        holder.user_phones_txt.setText(String.valueOf(user_phone.get(position)));
        holder.user_category_txt.setText(String.valueOf(user_category.get(position)));
        holder.user_date_txt.setText(String.valueOf(user_date.get(position)));
        holder.user_time_txt.setText(String.valueOf(user_time.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                //Toast.makeText(context, "id" + user_id.get(position), Toast.LENGTH_SHORT).show();
                intent.putExtra("id", String.valueOf(user_id.get(position)));
                intent.putExtra("name", String.valueOf(user_name.get(position)));
                intent.putExtra("last", String.valueOf(user_last.get(position)));
                intent.putExtra("phone", String.valueOf(user_phone.get(position)));
                intent.putExtra("category", String.valueOf(user_category.get(position)));
                intent.putExtra("date", String.valueOf(user_date.get(position)));
                intent.putExtra("time", String.valueOf(user_time.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return user_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView user_id_txt, user_name_txt, user_last_txt, user_phones_txt, user_category_txt, user_date_txt, user_time_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user_id_txt = itemView.findViewById(R.id.user_id_txt);
            user_name_txt = itemView.findViewById(R.id.user_name_txt);
            user_last_txt = itemView.findViewById(R.id.user_last_txt);
            user_phones_txt = itemView.findViewById(R.id.user_phone_txt);
            user_category_txt = itemView.findViewById(R.id.user_category_txt);
            user_date_txt = itemView.findViewById(R.id.user_date_txt);
            user_time_txt = itemView.findViewById(R.id.user_time_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
           /* Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);*/
        }

    }

}
