package com.webdemo.recycler;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.webdemo.R;
import com.webdemo.listeners.CustomItemClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * induiduel webview projesidir.
 */

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {

    List<Person> list_person;
    CustomItemClickListener listener;
    public SimpleRecyclerAdapter(List<Person> list_person, CustomItemClickListener listener) {

        this.list_person = list_person;
        this.listener = listener;
    }

    @NotNull
    @Override
    public SimpleRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

        v.setOnClickListener(v1 -> {
            //noinspection deprecation
            listener.onItemClick(v1, view_holder.getPosition());
        });

        return view_holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.person_name.setText(list_person.get(position).getName());

        holder.person_img.setImageResource(list_person.get(position).getPhoto_id());

    }

    @Override
    public int getItemCount() {
        return list_person.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView person_name;
        public TextView person_age;
        public ImageView person_img;
        public CardView card_view;


        public ViewHolder(View view) {
            super(view);

            card_view = view.findViewById(R.id.card_view);
            person_name = view.findViewById(R.id.person_name);
            person_img = view.findViewById(R.id.person_photo);

        }
    }


}