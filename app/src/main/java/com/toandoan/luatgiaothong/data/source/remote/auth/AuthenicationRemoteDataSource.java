package com.toandoan.luatgiaothong.data.source.remote.auth;

import android.net.Uri;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.toandoan.luatgiaothong.data.source.AuthenicationDataSource;
import com.toandoan.luatgiaothong.data.source.callback.DataCallback;

/**
 * Created by framgia on 10/05/2017.
 */

public class AuthenicationRemoteDataSource extends BaseAuthRemoteDataSource
        implements AuthenicationDataSource.RemoteDataSource {
    public AuthenicationRemoteDataSource() {
    }

    @Override
    public void register(String email, String password, final DataCallback<FirebaseUser> callback) {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        getResponse(task, callback);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onGetDataFailed(e.getMessage());
                    }
                });
    }

    @Override
    public void signIn(String email, String password, final DataCallback<FirebaseUser> callback) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        getResponse(task, callback);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onGetDataFailed(e.getMessage());
                    }
                });
    }

    @Override
    public void getCurrentUser(DataCallback<FirebaseUser> callback) {
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        if (user == null) {
            callback.onGetDataFailed(null);
        } else {
            callback.onGetDataSuccess(user);
        }
    }

    @Override
    public void signOut(DataCallback<FirebaseUser> callback) {
        mFirebaseAuth.signOut();
        callback.onGetDataSuccess(null);
    }

    @Override
    public void resetPassword(String email, final DataCallback callback) {
        mFirebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            callback.onGetDataSuccess(null);
                        } else {
                            callback.onGetDataFailed(task.getException().getMessage());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onGetDataFailed(e.getMessage());
                    }
                });
    }

    @Override
    public void updateProfile(String userName, Uri photo, final DataCallback callback) {
        UserProfileChangeRequest.Builder builder = new UserProfileChangeRequest.Builder();
        builder.setDisplayName(userName);

        if (photo != null) {
            builder.setPhotoUri(photo);
        }

        mFirebaseAuth.getCurrentUser()
                .updateProfile(builder.build())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        callback.onGetDataSuccess(null);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onGetDataFailed(e.getMessage());
                    }
                });
    }

    private void getResponse(Task<AuthResult> authResultTask, DataCallback callback) {
        if (authResultTask == null) {
            callback.onGetDataFailed(null);
            return;
        }
        if (!authResultTask.isSuccessful()) {
            String message = authResultTask.getException().getMessage();
            callback.onGetDataFailed(message);
            return;
        }
        callback.onGetDataSuccess(authResultTask.getResult().getUser());
    }
}
