package com.toandoan.luatgiaothong.screen.register;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.toandoan.luatgiaothong.BaseActivity;
import com.toandoan.luatgiaothong.R;
import com.toandoan.luatgiaothong.data.source.remote.auth.AuthenicationRepository;
import com.toandoan.luatgiaothong.data.source.remote.auth.AuthenicationRemoteDataSource;
import com.toandoan.luatgiaothong.databinding.ActivityRegisterBinding;
import com.toandoan.luatgiaothong.utils.navigator.Navigator;

/**
 * Register Screen.
 */
public class RegisterActivity extends BaseActivity {

    private RegisterContract.ViewModel mViewModel;

    public static Intent getInstance(Context context) {
        return new Intent(context, RegisterActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new RegisterViewModel(this, new Navigator(this));

        AuthenicationRepository repository = new AuthenicationRepository(
                new AuthenicationRemoteDataSource());

        RegisterContract.Presenter presenter = new RegisterPresenter(mViewModel, repository);
        mViewModel.setPresenter(presenter);

        ActivityRegisterBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setViewModel((RegisterViewModel) mViewModel);
        getSupportActionBar().hide();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
