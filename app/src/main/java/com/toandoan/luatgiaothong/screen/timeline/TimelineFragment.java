package com.toandoan.luatgiaothong.screen.timeline;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.toandoan.luatgiaothong.BaseFragment;
import com.toandoan.luatgiaothong.R;
import com.toandoan.luatgiaothong.databinding.FragmentTimelineBinding;

/**
 * Timeline Screen.
 */
public class TimelineFragment extends BaseFragment {

    private TimelineContract.ViewModel mViewModel;

    public static TimelineFragment newInstance() {
        return new TimelineFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new TimelineViewModel();

        TimelineContract.Presenter presenter = new TimelinePresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentTimelineBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_timeline, container, false);
        binding.setViewModel((TimelineViewModel) mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
