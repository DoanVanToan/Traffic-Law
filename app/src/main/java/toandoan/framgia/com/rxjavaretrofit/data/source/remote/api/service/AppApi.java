package toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.response.GitHub;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public interface AppApi {
    @GET("/users/{login}")
    Observable<GitHub> getUser(@Path("login") String login);
}
