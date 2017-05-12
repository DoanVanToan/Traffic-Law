package com.toandoan.luatgiaothong.data.source.remote.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.toandoan.luatgiaothong.data.source.remote.api.service.AppApi;

/**
 *
 */

public class BaseAuthRemoteDataSource {
    protected FirebaseAuth mFirebaseAuth;

    public BaseAuthRemoteDataSource() {
        mFirebaseAuth = FirebaseAuth.getInstance();
    }
}
