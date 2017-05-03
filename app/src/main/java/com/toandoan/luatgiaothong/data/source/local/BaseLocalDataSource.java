package com.toandoan.luatgiaothong.data.source.local;

import com.toandoan.luatgiaothong.data.source.local.realm.RealmApi;

/**
 *
 */

public class BaseLocalDataSource {
    private RealmApi mApi;

    public BaseLocalDataSource(RealmApi api) {
        mApi = api;
    }
}
