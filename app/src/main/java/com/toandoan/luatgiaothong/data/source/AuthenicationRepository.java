package com.toandoan.luatgiaothong.data.source;

import com.google.firebase.auth.FirebaseUser;
import rx.Observable;

/**
 * Created by framgia on 10/05/2017.
 */

public class AuthenicationRepository {
    private AuthenicationDataSource.RemoteDataSource mRemoteDataSource;

    public AuthenicationRepository(AuthenicationDataSource.RemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public Observable<FirebaseUser> register(String email, String password) {
        return mRemoteDataSource.register(email, password);
    }

    public Observable<FirebaseUser> login(String email, String password) {
        return mRemoteDataSource.login(email, password);
    }

    public Observable<FirebaseUser> getCurrentUser() {
        return mRemoteDataSource.getCurrentUser();
    }
}
