package com.toandoan.luatgiaothong.service;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.toandoan.luatgiaothong.screen.main.MainActivity;

/**
 * Created by framgia on 11/05/2017.
 */

public class FirebaseUploadService extends BaseStorageService {
    /**
     * Action
     **/
    public final static String ACTION_UPLOAD = "com.toandoan.action.ACTION_UPLOAD";
    public final static String UPLOAD_COMPLETE = "upload_complete";
    public final static String UPLOAD_ERROR = "upload_error";
    /**
     * Intent Extra
     **/
    public final static String EXTRA_URI = "EXTRA_URI";
    public final static String EXTRA_DOWNLOAD_URL = "EXTRA_DOWNLOAD_URL";
    public final static String EXTRA_FOLDER = "EXTRA_FOLDER";
    public final static String PROGRESS_UPLOAD = "Uploading...";
    public final static String UPLOAD_SUCCESS = "Upload successful";
    public final static String UPLOAD_FAILED = "Upload failed";
    private static final String TAG = "FirebaseUploadService";
    private StorageReference mStorageRef;

    public static IntentFilter getIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UPLOAD_COMPLETE);
        filter.addAction(UPLOAD_ERROR);

        return filter;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(ACTION_UPLOAD)) {
            Uri uri = intent.getParcelableExtra(EXTRA_URI);
            String folder = intent.getStringExtra(EXTRA_FOLDER);
            uploadFromUri(uri, folder);
        }
        return START_REDELIVER_INTENT;
    }
    // [END upload_from_uri]

    // [START upload_from_uri]
    private void uploadFromUri(final Uri fileUri, String folder) {
        // [START_EXCLUDE]
        taskStarted();
        showProgressNotification(PROGRESS_UPLOAD, 0, 0);
        // [END_EXCLUDE]

        // [START get_child_ref]
        // Get a reference to store file at photos/<FILENAME>.jpg
        final StorageReference photoRef =
                mStorageRef.child(folder).child(fileUri.getLastPathSegment());
        // [END get_child_ref]

        // Upload file to Firebase Storage
        Log.d(TAG, "uploadFromUri:dst:" + photoRef.getPath());
        photoRef.putFile(fileUri).
                addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        showProgressNotification(PROGRESS_UPLOAD,
                                taskSnapshot.getBytesTransferred(),
                                taskSnapshot.getTotalByteCount());
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Upload succeeded
                Log.d(TAG, "uploadFromUri:onSuccess");

                // Get the public download URL
                Uri downloadUri = taskSnapshot.getMetadata().getDownloadUrl();

                // [START_EXCLUDE]
                broadcastUploadFinished(downloadUri, fileUri);
                showUploadFinishedNotification(downloadUri, fileUri);
                taskCompleted();
                // [END_EXCLUDE]
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Upload failed
                Log.w(TAG, "uploadFromUri:onFailure", exception);

                // [START_EXCLUDE]
                broadcastUploadFinished(null, fileUri);
                showUploadFinishedNotification(null, fileUri);
                taskCompleted();
                // [END_EXCLUDE]
            }
        });
    }

    /**
     * Broadcast finished upload (success or failure).
     *
     * @return true if a running receiver received the broadcast.
     */
    private boolean broadcastUploadFinished(@Nullable Uri downloadUrl, @Nullable Uri fileUri) {
        boolean success = downloadUrl != null;

        String action = success ? UPLOAD_COMPLETE : UPLOAD_ERROR;

        Intent broadcast = new Intent(action).putExtra(EXTRA_DOWNLOAD_URL, downloadUrl)
                .putExtra(EXTRA_URI, fileUri);
        return LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(broadcast);
    }

    /**
     * Show a notification for a finished upload.
     */
    private void showUploadFinishedNotification(@Nullable Uri downloadUrl, @Nullable Uri fileUri) {
        // Hide the progress notification
        dismissProgressNotification();

        // Make Intent to MainActivity
        Intent intent =
                new Intent(this, MainActivity.class).putExtra(EXTRA_DOWNLOAD_URL, downloadUrl)
                        .putExtra(EXTRA_URI, fileUri)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        boolean success = downloadUrl != null;
        String caption = success ? UPLOAD_SUCCESS : UPLOAD_FAILED;
        showFinishedNotification(caption, intent, success);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
