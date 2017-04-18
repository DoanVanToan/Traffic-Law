package toandoan.framgia.com.rxjavaretrofit.data.source.remote;

import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service.AppApi;

/**
 * Created by framgia on 18/04/2017.
 */

public class BaseRemoteDataSource {
    protected AppApi mApi;

    public BaseRemoteDataSource(AppApi api) {
        mApi = api;
    }
}
