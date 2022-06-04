package com.example.apipost.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apipost.R;
import com.example.apipost.helper.Constant;
import com.example.apipost.models.ModelComments;
import com.example.apipost.models.ModelPost;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class AdapterRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<ModelPost> list;
    ArrayList<ModelComments> commentList;

    public void setList(ArrayList<ModelPost> list) {
        this.list = list;
    }

    public void setCommentList(ArrayList<ModelComments> commentList) {
        this.commentList = commentList;
    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.isEmpty(list.get(position).getTitle())) {
            return Constant.POST_HOLDER;
        } else {
            return Constant.COMMENT_HOLDER;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == Constant.POST_HOLDER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_post_recycler, parent, false);
            return new PostHolder(view);
        } else {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.item_comment_recycler, parent, false);
            CommentHolder commentHolder = new CommentHolder(view);
            return commentHolder;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == Constant.POST_HOLDER) {
            ModelPost modelPost = list.get(position);
            ((PostHolder) holder).callPostHolder(modelPost);
        } else {
            ModelComments modelComments = commentList.get(position);
            ((CommentHolder) holder).callCommentHolder(modelComments);
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder {
        TextView title, body;
        MaterialButton comment;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title);
            body = itemView.findViewById(R.id.text_body);
            comment = itemView.findViewById(R.id.btnComments);

        }

        private void callPostHolder(ModelPost modelPost) {
            title.setText(modelPost.getTitle());
            body.setText(modelPost.getBody());
        }
    }

    public class CommentHolder extends RecyclerView.ViewHolder {
        TextView comment;

        public CommentHolder(@NonNull View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.text_comment);
        }

        private void callCommentHolder(ModelComments modelComments) {
            comment.setText(modelComments.getBody());
        }
    }

}
