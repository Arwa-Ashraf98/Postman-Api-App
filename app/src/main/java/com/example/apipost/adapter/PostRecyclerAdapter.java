package com.example.apipost.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apipost.R;
import com.example.apipost.models.ModelPost;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.Holder> {

    private ArrayList<ModelPost> list;
    private onItemClickListener onItemClickListener;

    public void setList(ArrayList<ModelPost> list) {
        this.list = list;
    }

    public void setOnItemClickListener(PostRecyclerAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post_recycler, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ModelPost modelPost = list.get(position);
        holder.title.setText(modelPost.getTitle());
        holder.body.setText(modelPost.getBody());

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView title, body;
        MaterialButton comments;

        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title);
            body = itemView.findViewById(R.id.text_body);
            comments = itemView.findViewById(R.id.btnComments);

            comments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null)
                        onItemClickListener.onCommentClick(list.get(getLayoutPosition()).getId());
                }
            });

        }

    }

    public interface onItemClickListener {
        void onCommentClick(int postId);

    }
}
