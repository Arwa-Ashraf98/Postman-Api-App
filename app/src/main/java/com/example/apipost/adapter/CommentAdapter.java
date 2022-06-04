package com.example.apipost.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apipost.R;
import com.example.apipost.models.ModelComments;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Holder> {
    private ArrayList<ModelComments> list;

    public void setList(ArrayList<ModelComments> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment_recycler, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.commentBody.setText(list.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView commentBody;

        public Holder(@NonNull View itemView) {
            super(itemView);
            commentBody = itemView.findViewById(R.id.text_comment);
        }
    }
}
