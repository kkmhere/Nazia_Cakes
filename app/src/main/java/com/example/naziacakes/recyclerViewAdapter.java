package com.example.naziacakes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.ViewHolder> {

    private List<Cakes> cake;
    private Context context;

    public recyclerViewAdapter(List<Cakes> cake, Context context) {
        this.cake = cake;
        this.context = context;
    }

    @NonNull
    @Override
    public recyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerViewAdapter.ViewHolder holder, int position) {
        Cakes cakes =  cake.get(position);
        holder.cakename.setText(cakes.getName());
        holder.caketype.setText(cakes.getType());
    }

    @Override
    public int getItemCount() {
        return cake.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {   public TextView cakename;
        public TextView caketype;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cakename=itemView.findViewById(R.id.cakname);
            caketype=itemView.findViewById(R.id.caketype);
        }
    }
}
