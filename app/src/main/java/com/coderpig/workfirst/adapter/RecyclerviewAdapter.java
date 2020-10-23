package com.coderpig.workfirst.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.coderpig.workfirst.R;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.BaseViewHolder> {
    static class BaseViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
             tv = itemView.findViewById(R.id.recyclerview_item_id);
        }
    }

    public ArrayList<String> data;
    public RecyclerviewAdapter(ArrayList<String> data){
        this.data = data;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        BaseViewHolder holder = new BaseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.tv.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }
}
