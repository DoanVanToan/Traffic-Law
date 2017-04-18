package toandoan.framgia.com.rxjavaretrofit.data.source.local;

import toandoan.framgia.com.rxjavaretrofit.data.source.local.realm.RealmApi;

/**
 * Created by framgia on 18/04/2017.
 */

public class BaseLocalDataSource {
    private RealmApi mApi;

    public BaseLocalDataSource(RealmApi api) {
        mApi = api;
    }
}
