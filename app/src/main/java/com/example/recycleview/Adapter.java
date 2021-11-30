package com.example.recycleview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList foodIdArray, foodTitleArray;

    public Adapter(Activity activity, Context context, ArrayList foodIdArray, ArrayList foodTitle){
        this.activity = activity;
        this.context = context;
        this.foodIdArray = foodIdArray;
        this.foodTitleArray = foodTitle;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_view_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.food_title.setText((String.valueOf(foodTitleArray.get(position))));
        holder.mainLayout.setOnClickListener(view -> {
            Toast.makeText(context, String.valueOf(foodTitleArray.get(position)), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("foodId", String.valueOf(foodIdArray.get(position)));
            intent.putExtra("foodTitle", String.valueOf(foodTitleArray.get(position)));
            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return foodTitleArray.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView food_title;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageView food_image = itemView.findViewById(R.id.food_image);
            food_image.setClipToOutline(true);
            food_title = itemView.findViewById(R.id.food_title);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
