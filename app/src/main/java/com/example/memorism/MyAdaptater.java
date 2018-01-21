package com.example.memorism;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdaptater extends RecyclerView.Adapter<MyAdaptater.MyViewHolder> {

    public static final List<String> list_trip_name = new ArrayList<String>();

    public static void addItem(String name)
    {
        list_trip_name.add(name);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final TextView name;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.trip_list_content, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.display(list_trip_name.get(position));
    }

    @Override
    public int getItemCount() {
        return list_trip_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;

        public MyViewHolder(final View itemView){
            super(itemView);
            name = ((TextView) itemView.findViewById(R.id.id_text_trip_name));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                }

            });
        }

        public void display(String name) {
            this.name.setText(name);
        }

    }

}
