package com.toandoan.luatgiaothong.data.source.remote;

import com.google.firebase.auth.FirebaseAuth;
import com.toandoan.luatgiaothong.data.source.remote.api.service.AppApi;

/**
 *
 */

public class BaseRemoteDataSource {
    protected FirebaseAuth mFirebaseAuth;

    public BaseRemoteDataSource(FirebaseAuth firebaseAuth) {
        mFirebaseAuth = firebaseAuth;
    }
}
