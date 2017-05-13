package com.toandoan.luatgiaothong.data.source;

import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.data.source.callback.DataCallback;

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

    public void login(String email, String password, DataCallback<FirebaseUser> callback) {
        mRemoteDataSource.signIn(email, password, callback);
    }

    public void getCurrentUser(DataCallback<FirebaseUser> callback) {
        mRemoteDataSource.getCurrentUser(callback);
    }

    public void signOut(DataCallback callback) {
        mRemoteDataSource.signOut(callback);
    }

    public void resetPassword(String email, DataCallback callback) {
        mRemoteDataSource.resetPassword(email, callback);
    }

    public void updateProfile(String userName, Uri photo, DataCallback callback) {
        mRemoteDataSource.updateProfile(userName, photo, callback);
    }
}
