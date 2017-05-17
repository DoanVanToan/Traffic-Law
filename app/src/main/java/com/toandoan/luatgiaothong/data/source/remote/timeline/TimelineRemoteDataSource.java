package com.toandoan.luatgiaothong.data.source.remote.timeline;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.toandoan.luatgiaothong.data.model.TimelineModel;
import com.toandoan.luatgiaothong.data.source.callback.DataCallback;
import com.toandoan.luatgiaothong.utils.Constant;

/**
 * Created by toand on 5/13/2017.
 */

public class TimelineRemoteDataSource extends BaseFirebaseDataBase implements TimelineDataSource {
    public TimelineRemoteDataSource() {
        super(Constant.Timeline.POST);
    }

    @Override
    public void createNewPost(TimelineModel timelineModel, final DataCallback callback) {
        mReference.push()
                .setValue(timelineModel)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            callback.onGetDataSuccess(null);
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
}
