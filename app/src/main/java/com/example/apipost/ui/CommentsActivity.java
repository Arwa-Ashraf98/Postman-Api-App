package com.example.apipost.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apipost.adapter.CommentAdapter;
import com.example.apipost.databinding.ActivityCommentsBinding;
import com.example.apipost.helper.Constant;
import com.example.apipost.models.ModelComments;
import com.example.apipost.network.RetrofitConnection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsActivity extends AppCompatActivity {
    private ActivityCommentsBinding binding;
    private ArrayList<ModelComments> commentsList = new ArrayList<ModelComments>();
    private final CommentAdapter adapter = new CommentAdapter();
//    private int postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int postId = getIntent().getIntExtra(Constant.POST_ID, 0);
//        this.postId=postId;
        getComment(postId);

    }

    private void getComment(int postId) {
        binding.progressbarComments.setVisibility(View.VISIBLE);
        RetrofitConnection.getServices()
                .getComments(postId)
                .enqueue(new Callback<ArrayList<ModelComments>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ModelComments>> call, Response<ArrayList<ModelComments>> response) {
                        commentsList = (ArrayList<ModelComments>) response.body();
//                        int num = commentsList.size();
                        adapter.setList(commentsList);
                        binding.recyclerComments.setAdapter(adapter);
                        binding.progressbarComments.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ModelComments>> call, Throwable t) {
                        binding.progressbarComments.setVisibility(View.GONE);
                        Toast.makeText(CommentsActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (binding != null)
            binding = null;
    }
}