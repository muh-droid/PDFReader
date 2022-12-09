package com.muh.mypdf;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

public class PdfAdapter extends RecyclerView.Adapter<PdfViewHolder> {
    Context context;
    ArrayList<HashMap<String, Object>> data;

    public PdfAdapter(Context context, ArrayList<HashMap<String, Object>> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public PdfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PdfViewHolder(LayoutInflater.from(context).inflate(R.layout.items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PdfViewHolder holder, @SuppressLint("RecyclerView") int position) {
       holder.textView.setText(data.get(position).get("name").toString().replace(".pdf", ""));

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent();
               intent.setClass(context, PdfActivity.class);
               intent.putExtra("path", data.get(position).get("file").toString());
               context.startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class PdfViewHolder extends RecyclerView.ViewHolder{
    TextView textView;
    public PdfViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
    }
}