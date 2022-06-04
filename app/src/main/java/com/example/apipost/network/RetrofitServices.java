package com.example.apipost.network;

import com.example.apipost.models.ModelComments;
import com.example.apipost.models.ModelPost;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitServices {
    // create function to call api

    @GET("posts")
    Call<ArrayList<ModelPost>> getAllPosts();
    // params = query params data loaded as key and value

    @GET("posts/{postId}/comments")
    Call<ArrayList<ModelComments>> getComments(@Path("postId") int postId);

    // create Post
    @FormUrlEncoded
    @POST("posts")
    Call<ModelPost> sendPost(@Field("title") String title,
                             @Field("body") String body,
                             @Field("userId") int userId
    );

    @POST("posts")
    Call<ModelPost> sendPost(@Body ModelPost modelPost);


}
