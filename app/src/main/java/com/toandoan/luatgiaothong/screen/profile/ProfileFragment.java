package com.toandoan.luatgiaothong.screen.profile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.toandoan.luatgiaothong.BaseFragment;
import com.toandoan.luatgiaothong.R;
import com.toandoan.luatgiaothong.data.source.remote.auth.AuthenicationRemoteDataSource;
import com.toandoan.luatgiaothong.data.source.remote.auth.AuthenicationRepository;
import com.toandoan.luatgiaothong.databinding.FragmentProfileBinding;

;

/**
 * Profile Screen.
 */
public class ProfileFragment extends BaseFragment {

    private ProfileContract.ViewModel mViewModel;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ProfileViewModel();

        AuthenicationRepository repository =
                new AuthenicationRepository(new AuthenicationRemoteDataSource());
        ProfileContract.Presenter presenter = new ProfilePresenter(mViewModel, repository);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentProfileBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        binding.setViewModel((ProfileViewModel) mViewModel);
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
