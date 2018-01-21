package com.example.memorism;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.memorism.memory.MemoryContent;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class Triplist_Adaptater extends RecyclerView.Adapter<Triplist_Adaptater.Trip_listViewHolder> {




    @Override
    public Trip_listViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final TextView name;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.trip_list_content, parent, false);

        return new Trip_listViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Trip_listViewHolder holder, int position) {

        holder.display(MemoryContent.getAllTripName().get(position));
    }

    @Override
    public int getItemCount() {
        return MemoryContent.getAllTripName().size();
    }

    public class Trip_listViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;

        public Trip_listViewHolder(final View itemView){
            super(itemView);
            name = ((TextView) itemView.findViewById(R.id.id_text_trip_name));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    Intent m_itemListActivity = new Intent(itemView.getContext(),ItemListActivity.class);
                    m_itemListActivity.putExtra("trip_name",name.getText().toString());
                    itemView.getContext().startActivity(m_itemListActivity);
                }

            });
        }

        public void display(String name) {
            this.name.setText(name);
        }

    }

}
