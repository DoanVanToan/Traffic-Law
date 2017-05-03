package toandoan.framgia.com.rxjavaretrofit.data.source.local;

import toandoan.framgia.com.rxjavaretrofit.data.source.local.realm.RealmApi;

/**
 *
 */

public class BaseLocalDataSource {
    private RealmApi mApi;

    public BaseLocalDataSource(RealmApi api) {
        mApi = api;
    }
}
