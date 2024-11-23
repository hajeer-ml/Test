package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class trv_Adapter extends RecyclerView.Adapter<trv_Adapter.MyViewHolder> {
    Context context;
    ArrayList<Test> test ;
    public trv_Adapter(Context context , ArrayList<Test> test){
        this.context =context;
        this.test = test;
    }

    @NonNull
    @Override
    public trv_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_row, parent, false);

        return new trv_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull trv_Adapter.MyViewHolder holder, int position) {
        holder.tvname.setText(test.get(position).getName());
        holder.imageView.setImageResource(test.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return test.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvname = itemView.findViewById(R.id.textView4);
        }
    }
}
