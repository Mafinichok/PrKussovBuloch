package com.example.prkussovbuloch;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prkussovbuloch.ui.reflow.ReflowViewModel;

import java.util.List;

public class adapterQs extends RecyclerView.Adapter<adapterQs.ViewHolder> {
    private List<String> items;
    final private Drawable[] pictures;
    private Context context;

    public adapterQs(List<String> items, Drawable[] pictures, Context context ) {
        this.items = items;
        this.pictures = pictures;
        this.context = context;
    }
    @NonNull
    @Override
    public adapterQs.ViewHolder onCreateViewHolder(@NonNull
                                                       ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.list_itemdobavit, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder
                                         holder, int position) {

        Drawable id = null;
        String item = items.get(position);
        if(position < pictures.length) {
            id = pictures[position];
        }
        holder.textView.setText(item);
        if(id != null) {
            holder.imageView.setImageDrawable(id);
        }


        holder.knopka.setOnClickListener(v -> {


            Toast.makeText(context, "Нажата кнопка в элементе " + position, Toast.LENGTH_SHORT).show();
            // Логика обработки нажатия кнопки
            Intent intent = new Intent(context, ActivityQs2.class);
            intent.putExtra("item", items.get(position));


            intent.putExtra("pictures", position);


            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        Button knopka;
        ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.item_nazvan2);
            imageView = view.findViewById(R.id.imageView22);
            knopka = itemView.findViewById(R.id.buttonRes2);
        }
    }





}