package com.example.apipost.network;

import com.example.apipost.helper.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnection {
    // singleton pattern to initialize / create Retrofit

    // 1- take object from RetrofitClass

    private static Retrofit retrofit;

    private static Retrofit getRetrofit(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static RetrofitServices getServices(){
        return getRetrofit().create(RetrofitServices.class);
    }

}
