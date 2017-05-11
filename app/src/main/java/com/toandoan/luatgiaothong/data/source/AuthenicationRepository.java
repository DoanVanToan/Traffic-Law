package com.toandoan.luatgiaothong.data.source;

import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.data.source.callback.DataCallback;
import rx.Observable;

/**
 * Created by framgia on 10/05/2017.
 */

public class AuthenicationRepository {
    private AuthenicationDataSource.RemoteDataSource mRemoteDataSource;

    public AuthenicationRepository(AuthenicationDataSource.RemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public void register(String email, String password, DataCallback<FirebaseUser> callback) {
        mRemoteDataSource.register(email, password, callback);
    }

    public void login(String email, String password,DataCallback<FirebaseUser> callback) {
        mRemoteDataSource.login(email, password, callback);
    }

    public void getCurrentUser(DataCallback<FirebaseUser> callback) {
        mRemoteDataSource.getCurrentUser(callback);
    }
}
