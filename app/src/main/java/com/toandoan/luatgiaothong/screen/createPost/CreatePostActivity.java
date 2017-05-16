package com.toandoan.luatgiaothong.screen.createPost;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.toandoan.luatgiaothong.BaseActivity;
import com.toandoan.luatgiaothong.R;
import com.toandoan.luatgiaothong.data.source.remote.auth.AuthenicationRemoteDataSource;
import com.toandoan.luatgiaothong.data.source.remote.auth.AuthenicationRepository;
import com.toandoan.luatgiaothong.databinding.ActivityCreatePostBinding;
import com.toandoan.luatgiaothong.utils.navigator.Navigator;
import com.toandoan.luatgiaothong.widget.autofit.AutoFitEditText;
import com.toandoan.luatgiaothong.widget.autofit.AutoFitEditUtil;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.toandoan.luatgiaothong.screen.createPost.CreatePostActivity.CreateType.IMAGE;
import static com.toandoan.luatgiaothong.screen.createPost.CreatePostActivity.CreateType.LOCATION;
import static com.toandoan.luatgiaothong.screen.createPost.CreatePostActivity.CreateType
        .TEXT_CONTENT;
import static com.toandoan.luatgiaothong.screen.createPost.CreatePostActivity.CreateType.VIDEO;

/**
 * CreatePost Screen.
 */
public class CreatePostActivity extends BaseActivity {

    private static final String EXTRA_CREATE_TYPE = "EXTRA_CREATE_TYPE";

    private CreatePostContract.ViewModel mViewModel;
    @CreateType
    private int mCreateType;

    public static Intent getInstance(Context context, @CreateType int createType) {
        Intent intent = new Intent(context, CreatePostActivity.class);
        intent.putExtra(EXTRA_CREATE_TYPE, createType);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();

        mViewModel = new CreatePostViewModel(this, new Navigator(this), mCreateType);
        AuthenicationRepository repository =
                new AuthenicationRepository(new AuthenicationRemoteDataSource());
        CreatePostContract.Presenter presenter = new CreatePostPresenter(mViewModel, repository);
        mViewModel.setPresenter(presenter);

        ActivityCreatePostBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_create_post);
        binding.setViewModel((CreatePostViewModel) mViewModel);

        getSupportActionBar().setTitle(R.string.title_create_post);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    private void getData() {
        Intent intent = getIntent();
        if (intent == null) return;
        mCreateType = intent.getExtras().getInt(EXTRA_CREATE_TYPE);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_post, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewModel.onActivityResult(requestCode, resultCode, data);
    }

    @IntDef({ TEXT_CONTENT, VIDEO, IMAGE, LOCATION })
    @Retention(RetentionPolicy.SOURCE)
    public @interface CreateType {
        int TEXT_CONTENT = 0;
        int VIDEO = 1;
        int IMAGE = 2;
        int LOCATION = 3;
    }
}
