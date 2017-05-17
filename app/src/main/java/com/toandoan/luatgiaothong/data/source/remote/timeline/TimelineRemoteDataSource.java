package com.toandoan.luatgiaothong.data.source.remote.timeline;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
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

    @Override
    public void getTimeline(final TimelineRemoteDataSource.TimelineCallback callback) {
        mReference.orderByValue()
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        TimelineModel timeline = dataSnapshot.getValue(TimelineModel.class);
                        callback.onChildAdded(timeline);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        TimelineModel timeline = dataSnapshot.getValue(TimelineModel.class);
                        String commentKey = dataSnapshot.getKey();
                        callback.onChildChanged(timeline, commentKey);
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        TimelineModel timeline = dataSnapshot.getValue(TimelineModel.class);
                        String commentKey = dataSnapshot.getKey();
                        callback.onChildRemoved(timeline, commentKey);
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                        TimelineModel timeline = dataSnapshot.getValue(TimelineModel.class);
                        String commentKey = dataSnapshot.getKey();
                        callback.onChildMoved(timeline, commentKey);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        callback.onCancelled(databaseError.getMessage());
                    }
                });
    }
}
