package com.example.apipost.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apipost.adapter.CommentAdapter;
import com.example.apipost.adapter.PostRecyclerAdapter;
import com.example.apipost.databinding.ActivityMainBinding;
import com.example.apipost.helper.Constant;
import com.example.apipost.models.ModelPost;
import com.example.apipost.network.RetrofitConnection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final PostRecyclerAdapter adapter = new PostRecyclerAdapter();
    private final CommentAdapter adapter2 = new CommentAdapter();
    private ActivityMainBinding binding;
    private ArrayList<ModelPost> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getData();
        onClicks();

    }

    private void onClicks() {
        adapter.setOnItemClickListener(new PostRecyclerAdapter.onItemClickListener() {
            @Override
            public void onCommentClick(int postId) {
                Intent intent = new Intent(MainActivity.this, CommentsActivity.class);
                intent.putExtra(Constant.POST_ID, postId);
                startActivity(intent);

            }
        });
        binding.addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreatePostActivity.class);
                startActivity(intent);

            }
        });
    }

//    private void getComments() {
//        binding.progressbar.setVisibility(View.VISIBLE);
//        RetrofitConnection.getServices()
//                .getComments(3)
//                .enqueue(new Callback<ArrayList<ModelComments>>() {
//                    @Override
//                    public void onResponse(Call<ArrayList<ModelComments>> call, Response<ArrayList<ModelComments>> response) {
//                        binding.progressbar.setVisibility(View.GONE);
//                        adapter2.setList(response.body());
//                        binding.recyclerPosts.setAdapter(adapter);
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ArrayList<ModelComments>> call, Throwable t) {
//                        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                        binding.progressbar.setVisibility(View.GONE);
//
//
//                    }
//                });
//
//
//    }

    private void getData() {
        binding.progressbar.setVisibility(View.VISIBLE);
        RetrofitConnection.getServices().
                getAllPosts().enqueue(new Callback<ArrayList<ModelPost>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<ModelPost>> call, @NonNull Response<ArrayList<ModelPost>> response) {
                binding.progressbar.setVisibility(View.GONE);
                list = response.body();
                adapter.setList(list);
                binding.recyclerPosts.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<ModelPost>> call, @NonNull Throwable t) {
                binding.progressbar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}