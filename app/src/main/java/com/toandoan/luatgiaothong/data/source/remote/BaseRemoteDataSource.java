package com.toandoan.luatgiaothong.data.source.remote;

import com.toandoan.luatgiaothong.data.source.remote.api.service.AppApi;

/**
 *
 */

public class BaseRemoteDataSource {
    protected AppApi mApi;

    public BaseRemoteDataSource(AppApi api) {
        mApi = api;
    }
}
