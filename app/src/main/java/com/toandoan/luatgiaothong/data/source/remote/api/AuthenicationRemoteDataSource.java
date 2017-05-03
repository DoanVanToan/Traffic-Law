package com.toandoan.luatgiaothong.data.source.remote.api;

import rx.Observable;
import com.toandoan.luatgiaothong.data.source.AuthenicationDataSource;
import com.toandoan.luatgiaothong.data.source.remote.BaseRemoteDataSource;
import com.toandoan.luatgiaothong.data.source.remote.api.response.GitHub;
import com.toandoan.luatgiaothong.data.source.remote.api.service.AppApi;

/**
 *
 */

public class AuthenicationRemoteDataSource extends BaseRemoteDataSource
        implements AuthenicationDataSource {
    public AuthenicationRemoteDataSource(AppApi api) {
        super(api);
    }

    @Override
    public Observable<GitHub> login(String login) {
        return mApi.getUser(login);
    }
}
