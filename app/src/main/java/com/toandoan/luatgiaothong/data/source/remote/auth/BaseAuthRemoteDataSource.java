package com.toandoan.luatgiaothong.data.source.remote.auth;

import com.google.firebase.auth.FirebaseAuth;

/**
 *
 */

public class BaseAuthRemoteDataSource {
    protected FirebaseAuth mFirebaseAuth;

    public BaseAuthRemoteDataSource() {
        mFirebaseAuth = FirebaseAuth.getInstance();
    }
}
