package com.toandoan.luatgiaothong.screen.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import com.toandoan.luatgiaothong.BaseActivity;
import com.toandoan.luatgiaothong.R;
import com.toandoan.luatgiaothong.data.source.AuthenicationRepository;
import com.toandoan.luatgiaothong.data.source.remote.AuthenicationRemoteDataSource;
import com.toandoan.luatgiaothong.databinding.ActivityLoginBinding;
import com.toandoan.luatgiaothong.utils.navigator.Navigator;

/**
 * Login Screen.
 */
public class LoginActivity extends BaseActivity {

    private LoginContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AuthenicationRepository repository = new AuthenicationRepository(
                new AuthenicationRemoteDataSource(FirebaseAuth.getInstance()));

        mViewModel = new LoginViewModel(this, new Navigator(this));
        LoginContract.Presenter presenter = new LoginPresenter(mViewModel, repository);
        mViewModel.setPresenter(presenter);

        ActivityLoginBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewModel((LoginViewModel) mViewModel);
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