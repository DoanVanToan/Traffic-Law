package com.toandoan.luatgiaothong.data.source.remote.api.service;

import com.toandoan.luatgiaothong.data.source.remote.api.response.GitHub;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 *
 */

public interface AppApi {
    @GET("/users/{signIn}")
    Observable<GitHub> getUser(@Path("signIn") String login);
}
