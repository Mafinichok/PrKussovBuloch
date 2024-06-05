package com.example.prkussovbuloch;

import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
public class adapterUb extends RecyclerView.Adapter<adapterUb.ViewHolder> {
    private List<String> items;
    final private Drawable[] pictures;
    private Context context;

    public adapterUb(List<String> items, Drawable[] pictures, Context context) {
        this.items = items;
        this.pictures = pictures;
        this.context = context;
    }
    @NonNull
    @Override
    public adapterUb.ViewHolder onCreateViewHolder(@NonNull
                                                   ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.list_itemubavit, parent, false);
        return new adapterUb.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull adapterUb.ViewHolder
                                         holder, int position) {

         DatabaseReference mDataBase2;
         String comp = "Complite";

        Drawable id = null;
        String item = items.get(position);


        if(position < pictures.length) {
            id = pictures[position];
        }
        holder.textView.setText(item);
        if(id != null) {
            holder.imageView.setImageDrawable(id);
        }
        mDataBase2 = FirebaseDatabase.getInstance().getReference(comp);



        holder.knopka.setOnClickListener(v -> {

            String aboba1 = holder.editText.getText().toString();
            if(holder.editText.getText().toString().isEmpty()){

            }
            else {
                String aboba = (items.get(position) + " -" + holder.editText.getText().toString());
                mDataBase2.push().setValue(aboba);
            }

            // Логика обработки нажатия кнопки


        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        public EditText editText;
        TextView textView;
        ImageView imageView;
        Button knopka;

        ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.item_nazvan2);
            imageView = view.findViewById(R.id.imageView22);
            knopka = itemView.findViewById(R.id.buttonRes2);
            editText = itemView.findViewById(R.id.editCounter);
        }
    }

}
