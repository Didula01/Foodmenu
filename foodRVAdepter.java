package com.example.foodin;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class foodRVAdepter extends RecyclerView.Adapter<foodRVAdepter.ViewHolder> {
    @NonNull
    @Override
    public foodRVAdepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull foodRVAdepter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public enum ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull|View itemView){
            super (itemView);
        }
    }
}
