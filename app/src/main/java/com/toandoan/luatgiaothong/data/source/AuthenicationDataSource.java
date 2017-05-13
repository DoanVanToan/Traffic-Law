package com.toandoan.luatgiaothong.data.source;

import android.net.Uri;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseUser;
import com.toandoan.luatgiaothong.data.source.callback.DataCallback;

/**
 *
 */

public interface AuthenicationDataSource {
    interface RemoteDataSource {
        void register(String email, String password, DataCallback<FirebaseUser> callBack);

        void signIn(String email, String password, DataCallback<FirebaseUser> callBack);

        void signIn(GoogleSignInAccount account, DataCallback<FirebaseUser> callback);

        void getCurrentUser(DataCallback<FirebaseUser> callBack);

        void signOut(GoogleApiClient googleApiClient, DataCallback<FirebaseUser> callback);

        void signOut(DataCallback<FirebaseUser> callback);

        void resetPassword(String email, DataCallback callback);

        void updateProfile(String userName, Uri photo, DataCallback callback);

    }
}
