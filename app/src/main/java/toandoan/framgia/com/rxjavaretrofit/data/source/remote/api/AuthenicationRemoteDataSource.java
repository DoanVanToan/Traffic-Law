package toandoan.framgia.com.rxjavaretrofit.data.source.remote.api;

import rx.Observable;
import toandoan.framgia.com.rxjavaretrofit.data.source.AuthenicationDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.BaseRemoteDataSource;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.response.GitHub;
import toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.service.AppApi;

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
