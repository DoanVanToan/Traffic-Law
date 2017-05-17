package com.toandoan.luatgiaothong.data.source.remote.timeline;

import com.toandoan.luatgiaothong.data.model.TimelineModel;
import com.toandoan.luatgiaothong.data.source.callback.DataCallback;

/**
 * Created by toand on 5/13/2017.
 */

public interface TimelineDataSource {
    void createNewPost(TimelineModel timelineModel, DataCallback callback);
}
