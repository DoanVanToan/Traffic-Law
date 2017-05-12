package com.toandoan.luatgiaothong.utils.binding;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.toandoan.luatgiaothong.R;

/**
 *
 */

public final class BindingUtils {

    private BindingUtils() {
        // No-op
    }

    /**
     * setAdapter For RecyclerView
     */
    @BindingAdapter({ "recyclerAdapter" })
    public static void setAdapterForRecyclerView(RecyclerView recyclerView,
            RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({ "bind:adapter" })
    public static void setViewPagerAdapter(final ViewPager viewPager,
            final FragmentPagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter({ "bind:pager" })
    public static void setViewPagerTabs(final TabLayout tabLayout, final ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @BindingAdapter({ "bind:currentFragment" })
    public static void setCurrentViewPager(final ViewPager viewPager, final int currentPage) {
        viewPager.setCurrentItem(currentPage);
        viewPager.beginFakeDrag();
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }

    @BindingAdapter("bind:imageUri")
    public static void loadImage(ImageView imageView, Uri uri) {
        if (uri == null) return;
        imageView.setImageURI(uri);
    }

    @BindingAdapter({ "spinnerAdapter" })
    public static void setAdapterForSpinner(AppCompatSpinner spinner,
            ArrayAdapter<String> adapter) {
        spinner.setAdapter(adapter);
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView,
            LayoutManagers.LayoutManagerFactory layoutManagerFactory) {
        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }

    @BindingAdapter("errorText")
    public static void setErrorText(EditText editText, String text) {
        editText.setError(text);
    }
}
