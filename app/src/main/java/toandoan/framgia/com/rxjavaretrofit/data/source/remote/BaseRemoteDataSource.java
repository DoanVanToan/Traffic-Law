package toandoan.framgia.com.rxjavaretrofit.data.source.remote;

import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service.AppApi;

/**
 *
 */

public class BaseRemoteDataSource {
    protected AppApi mApi;

    public BaseRemoteDataSource(AppApi api) {
        mApi = api;
    }
}
