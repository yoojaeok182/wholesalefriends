package net.yazeed44.imagepicker.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import net.yazeed44.imagepicker.library.R;
import net.yazeed44.imagepicker.model.AlbumEntry;
import net.yazeed44.imagepicker.util.Events;
import net.yazeed44.imagepicker.util.Picker;

import de.greenrobot.event.EventBus;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by yazeed44 on 6/20/15.
 */
public class ImagesPagerFragment extends Fragment implements PhotoViewAttacher.OnViewTapListener, ViewPager.OnPageChangeListener {

    public static final String TAG = ImagesPagerFragment.class.getSimpleName();
    protected ViewPager mImagePager;
    protected AlbumEntry mSelectedAlbum;
    protected Picker mPickOptions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mPickOptions = EventBus.getDefault().getStickyEvent(Events.OnPublishPickOptionsEvent.class).options;


        EventBus.getDefault().post(new Events.OnShowingToolbarEvent());
        removeBehaviorAttr(container);
        mImagePager = (ViewPager) inflater.inflate(R.layout.fragment_image_pager, container, false);


        mImagePager.addOnPageChangeListener(this);

        return mImagePager;
    }

    private void removeBehaviorAttr(final ViewGroup container) {
        //If the behavior hasn't been removed then when collapsing the toolbar the layout will resize which is annoying

//        final CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) container.getLayoutParams();
//        layoutParams.setBehavior(null);
//        container.setLayoutParams(layoutParams);
    }

    private void addBehaviorAttr(final ViewGroup container) {
//        final CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) container.getLayoutParams();
//        layoutParams.setBehavior(new AppBarLayout.ScrollingViewBehavior());
//        container.setLayoutParams(layoutParams);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().registerSticky(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void onViewTap(View view, float x, float y) {


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        updateDisplayedImage(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void updateDisplayedImage(final int index) {
        EventBus.getDefault().post(new Events.OnChangingDisplayedImageEvent(mSelectedAlbum.imageList.get(index)));
        //Because index starts from 0
        final int realPosition = index + 1;
        final String actionbarTitle = getResources().getString(R.string.image_position_in_view_pager).replace("%", realPosition + "").replace("$", mSelectedAlbum.imageList.size() + "");
    }


    public void onEvent(final Events.OnPickImageEvent pickImageEvent) {

        if (mImagePager.getAdapter() != null) {
            return;
        }
        mImagePager.setAdapter(new ImagePagerAdapter(this, mSelectedAlbum, this));
        final int imagePosition = mSelectedAlbum.imageList.indexOf(pickImageEvent.imageEntry);

        mImagePager.setCurrentItem(imagePosition);

        updateDisplayedImage(imagePosition);


    }

    public void onEvent(final Events.OnClickAlbumEvent albumEvent) {
        AlbumEntry temp = albumEvent.albumEntry;

        //뷰페이저에서는 0번쨰 (카메라) 삭제
        if(temp.imageList.get(0).isCamera) {
            temp.imageList.remove(0);
        }
        mSelectedAlbum = temp;
    }

    public void onEvent(final Events.OnAttachFabEvent fabEvent) {
    }


}
