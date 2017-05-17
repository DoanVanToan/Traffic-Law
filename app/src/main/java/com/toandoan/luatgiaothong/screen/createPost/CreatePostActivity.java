package com.toandoan.luatgiaothong.screen.createPost;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import com.toandoan.luatgiaothong.BaseActivity;
import com.toandoan.luatgiaothong.R;
import com.toandoan.luatgiaothong.data.model.MediaModel;
import com.toandoan.luatgiaothong.data.source.remote.auth.AuthenicationRemoteDataSource;
import com.toandoan.luatgiaothong.data.source.remote.auth.AuthenicationRepository;
import com.toandoan.luatgiaothong.databinding.ActivityCreatePostBinding;
import com.toandoan.luatgiaothong.databinding.ItemPostFirstTypeBinding;
import com.toandoan.luatgiaothong.databinding.ItemPostFourTypeBinding;
import com.toandoan.luatgiaothong.databinding.ItemPostSecondTypeBinding;
import com.toandoan.luatgiaothong.databinding.ItemPostThridTypeBinding;
import com.toandoan.luatgiaothong.databinding.ItemUploadProgressBinding;
import com.toandoan.luatgiaothong.utils.Constant;
import com.toandoan.luatgiaothong.utils.navigator.Navigator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

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
    private LinearLayout mLinearContent;
    private LinearLayout mLinearProgress;
    private View mBottomSheetView;
    private BottomSheetBehavior mBottomSheetBehavior;

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

        mLinearContent = binding.linearContent;
        mLinearProgress = binding.linearProgress;
        mBottomSheetView = binding.bottomSheet;
        mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheetView);
        mBottomSheetBehavior.setPeekHeight(0);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent == null) return;
        mCreateType = intent.getExtras().getInt(EXTRA_CREATE_TYPE);
    }

    private void removeOldPostView() {
        View v = mLinearContent.getChildAt(mLinearContent.getChildCount() - 1);
        if (v instanceof CardView) {
            mLinearContent.removeView(v);
        }
    }

    public void addPostView(List<MediaModel> models) {
        removeOldPostView();
        switch (models.size()) {
            case Constant.Timeline.ONE_IMAGE:
                ItemPostFirstTypeBinding firstTypeBinding =
                        ItemPostFirstTypeBinding.inflate(getLayoutInflater());
                firstTypeBinding.setListData(models);
                mLinearContent.addView(firstTypeBinding.getRoot());
                break;
            case Constant.Timeline.TWO_IMAGE:
                ItemPostSecondTypeBinding secondTypeBinding =
                        ItemPostSecondTypeBinding.inflate(getLayoutInflater());
                secondTypeBinding.setListData(models);
                mLinearContent.addView(secondTypeBinding.getRoot());
                break;
            case Constant.Timeline.THREE_IMAGE:
                ItemPostThridTypeBinding thirdTypeBinding =
                        ItemPostThridTypeBinding.inflate(getLayoutInflater());
                thirdTypeBinding.setListData(models);
                mLinearContent.addView(thirdTypeBinding.getRoot());
                break;
            case Constant.Timeline.FOUR_IMAGE:
            default:
                ItemPostFourTypeBinding fourTypeBinding =
                        ItemPostFourTypeBinding.inflate(getLayoutInflater());
                fourTypeBinding.setListData(models);
                mLinearContent.addView(fourTypeBinding.getRoot());
                break;
        }
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
            case R.id.menu_save:
                mViewModel.onCreatePost();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewModel.onActivityResult(requestCode, resultCode, data);
    }

    public void showUploadProgressView(List<MediaModel> mediaModels) {
        if (mediaModels == null || mediaModels.size() == 0) return;
        mLinearProgress.removeAllViews();
        for (MediaModel mediaModel : mediaModels) {
            ItemUploadProgressBinding itemUpload =
                    ItemUploadProgressBinding.inflate(getLayoutInflater());
            itemUpload.setMediaModel(mediaModel);
            mLinearProgress.addView(itemUpload.getRoot());
        }
        mBottomSheetBehavior.setPeekHeight(300);
    }

    public void hideBottomSheet() {
        mBottomSheetBehavior.setPeekHeight(0);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
