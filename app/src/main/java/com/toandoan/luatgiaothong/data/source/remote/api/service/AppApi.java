package com.toandoan.luatgiaothong.data.source.remote.api.service;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import com.toandoan.luatgiaothong.data.source.remote.api.response.GitHub;

/**
 *
 */

public interface AppApi {
    @GET("/users/{login}")
    Observable<GitHub> getUser(@Path("login") String login);
}
