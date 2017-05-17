package com.toandoan.luatgiaothong.data.source.remote.timeline;

import com.toandoan.luatgiaothong.data.model.TimelineModel;
import com.toandoan.luatgiaothong.data.source.callback.DataCallback;

/**
 * Created by toand on 5/13/2017.
 */

public class TimelineRepository {
    private TimelineRemoteDataSource mDataSource;

    public TimelineRepository(TimelineRemoteDataSource dataSource) {
        mDataSource = dataSource;
    }

    public void createNewPost(TimelineModel timelineModel, DataCallback callback) {
        mDataSource.createNewPost(timelineModel, callback);
    }
}
