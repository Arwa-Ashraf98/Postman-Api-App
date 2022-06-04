package com.example.apipost.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apipost.R;
import com.example.apipost.databinding.ActivityCreatePostBinding;
import com.example.apipost.models.ModelPost;
import com.example.apipost.network.RetrofitConnection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePostActivity extends AppCompatActivity {
    ActivityCreatePostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreatePostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onClicks();
    }

    private void onClicks() {
        binding.btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();

            }
        });
    }

    private void getData() {
        String title = binding.editTitle.getEditableText().toString().trim();
        String body = binding.editBody.getEditableText().toString().trim();
        Integer userId = Integer.valueOf(binding.editUserId.getEditableText().toString().trim());
        validate(title, body, userId);
    }

    private void validate(String title, String body, Integer userId) {
        if (body.isEmpty()) {
            binding.editBody.setError(getString(R.string.required));
        } else if (title.isEmpty()) {
            binding.editTitle.setError(getString(R.string.required));

        } else if (userId == null) {
            binding.editUserId.setError(getString(R.string.required));
        } else {
            createPost(title, body, userId);
        }
    }

    private void createPost(String title, String body, int userId) {
        ModelPost modelPost = new ModelPost();
        modelPost.setTitle(title);
        modelPost.setBody(body);
        modelPost.setUserId(userId);
        RetrofitConnection.getServices()
                .sendPost(modelPost)
                .enqueue(new Callback<ModelPost>() {
                    @Override
                    public void onResponse(Call<ModelPost> call, Response<ModelPost> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(CreatePostActivity.this, "Code :" + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        ModelPost post = response.body();
                        post.getBody();
                        post.getTitle();
                        post.getUserId();
                        post.getId();
                        ArrayList<ModelPost> list = new ArrayList<>();
                        list.add(post);
                        Toast.makeText(CreatePostActivity.this, "posted successfully " + response.code(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<ModelPost> call, Throwable t) {
                        Toast.makeText(CreatePostActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}