package net.yazeed44.imagepicker.ui;

import android.animation.LayoutTransition;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import net.yazeed44.imagepicker.library.R;
import net.yazeed44.imagepicker.model.AlbumEntry;
import net.yazeed44.imagepicker.model.ImageEntry;
import net.yazeed44.imagepicker.util.CameraSupport;
import net.yazeed44.imagepicker.util.Events;
import net.yazeed44.imagepicker.util.Picker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import de.greenrobot.event.EventBus;


public class PickerActivity extends FragmentActivity {


    public static final int NO_LIMIT = -1;

    public static final String KEY_ACTION_BAR_TITLE = "actionBarKey";
    public static final String KEY_SHOULD_SHOW_ACTIONBAR_UP = "shouldShowUpKey";
    public static final String CAPTURED_IMAGES_ALBUM_NAME = "captured_images";
    public static final String CAPTURED_IMAGES_DIR = Environment.getExternalStoragePublicDirectory(CAPTURED_IMAGES_ALBUM_NAME).getAbsolutePath();
    private static final int REQUEST_PORTRAIT_RFC = 1337;
    private static final int REQUEST_PORTRAIT_FFC = REQUEST_PORTRAIT_RFC + 1;
    public static ArrayList<ImageEntry> sCheckedImages = new ArrayList<>();

    private boolean mShouldShowUp = false;

    public TextView mDoneFab;
    public LinearLayout mEditFab;
    private Picker mPickOptions;
    //For ViewPager
    private ImageEntry mCurrentlyDisplayedImage;
    private AlbumEntry mSelectedAlbum;

    private TextView tvTitle;
    private TextView tvCount;

    public static PickerActivity instance;
    private LinearLayout rootContainer;
    public static PickerActivity getInstance() {
        return instance;
    }

    //TODO Add animation
    //TODO Fix bugs with changing orientation
    //TODO Add support for gif
    //TODO Use robust method for capturing photos
    //TODO Add support for picking videos
    //TODO When photo is captured a new album created for it

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        mPickOptions = (EventBus.getDefault().getStickyEvent(Events.OnPublishPickOptionsEvent.class)).options;
        initTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);
        this.rootContainer = (LinearLayout) findViewById(R.id.rootContainer);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvCount = (TextView) findViewById(R.id.fab_count);
        ImageView btnBack = (ImageView) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        if(Build.VERSION.SDK_INT >= 21) {
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int color = this.getResources().getColor(R.color.status_bar);
            this.getWindow().setStatusBarColor(color);
        }

        addToolbarToLayout();
        initActionbar(savedInstanceState);
        setupAlbums(savedInstanceState);
        initFab();
        updateFab();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_SHOULD_SHOW_ACTIONBAR_UP, mShouldShowUp);
    }

    private void initTheme() {
        setTheme(mPickOptions.themeResId);

    }

    private void addToolbarToLayout() {


    }


    @Override
    protected void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();

    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    private void initActionbar(final Bundle savedInstanceState) {


        if (savedInstanceState == null) {
            mShouldShowUp = mPickOptions.backBtnInMainActivity;
            tvTitle.setText("Albums");
        } else {
            mShouldShowUp = savedInstanceState.getBoolean(KEY_SHOULD_SHOW_ACTIONBAR_UP);
            tvTitle.setText(savedInstanceState.getString(KEY_ACTION_BAR_TITLE));

        }


    }

    public void initFab() {
        Drawable doneIcon = ContextCompat.getDrawable(this, R.drawable.ic_action_done_white);
        doneIcon = DrawableCompat.wrap(doneIcon);
        DrawableCompat.setTint(doneIcon, mPickOptions.doneFabIconTintColor);

        mDoneFab = (TextView) findViewById(R.id.fab_done);
        mDoneFab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onClickDone(v);
            }
        });

        mEditFab = (LinearLayout) findViewById(R.id.fab_edit);
        mEditFab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onClickEdit(v);
            }
        });

    }

    public void setupAlbums(Bundle savedInstanceState) {
        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState == null) {

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, new AlbumsFragment(), AlbumsFragment.TAG)
                        .commit();


            }
        }
    }


    public void updateFab() {


    }

    public void onClickEdit(View view) {


        if (mPickOptions.pickMode == Picker.PickMode.SINGLE_IMAGE) {
            if (mCurrentlyDisplayedImage != null) {
                sCheckedImages.add(mCurrentlyDisplayedImage);
                mCurrentlyDisplayedImage.isPicked = true;
            }
        }

        super.finish();

        //New object because sCheckedImages will get cleared
        mPickOptions.pickListener.onPickedSuccessfully(new ArrayList<>(sCheckedImages), true);
        sCheckedImages.clear();
        EventBus.getDefault().removeAllStickyEvents();

    }

    public void onClickDone(View view) {


        if (mPickOptions.pickMode == Picker.PickMode.SINGLE_IMAGE) {
            if(mCurrentlyDisplayedImage != null) {
                sCheckedImages.add(mCurrentlyDisplayedImage);
                mCurrentlyDisplayedImage.isPicked = true;
            }
        }

        super.finish();

        //New object because sCheckedImages will get cleared
        mPickOptions.pickListener.onPickedSuccessfully(new ArrayList<>(sCheckedImages), false);
        sCheckedImages.clear();
        EventBus.getDefault().removeAllStickyEvents();

    }

    public void onCancel(boolean isCamera) {
        mPickOptions.pickListener.onCancel(isCamera);
        sCheckedImages.clear();
        EventBus.getDefault().removeAllStickyEvents();


    }

    public void startCamera() {

        if (!CameraSupport.isEnabled()) {
            return;
        }

        if(!mPickOptions.videosEnabled){
            capturePhoto();
            return;
        }


        new AlertDialog.Builder(this).setTitle(R.string.dialog_choose_camera_title)
                .setItems(new String[]{getResources().getString(R.string.dialog_choose_camera_item_0), getResources().getString(R.string.dialog_choose_camera_item_1)}, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            capturePhoto();
                        } else {
                            captureVideo();
                        }
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();

    }

    public void capturePhoto() {

    }

    private File createTemporaryFileForCapturing(final String extension) {
        final File captureTempFile = new File(CAPTURED_IMAGES_DIR + "/tmp" + System.currentTimeMillis() + extension);
        try {
            captureTempFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("capture", e.getMessage());
        }

        return captureTempFile;
    }

    public void captureVideo() {
        final File captureVideoFile = createTemporaryFileForCapturing(".mp4");
        CameraSupport.startVideoCaptureActivity(this,
                captureVideoFile, mPickOptions.videoLengthLimit, REQUEST_PORTRAIT_FFC);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == REQUEST_PORTRAIT_FFC) {
            //For capturing image from camera
            refreshMediaScanner(data.getData().getPath());

        } else {
            Log.i("onActivityResult", "User canceled the camera activity");
        }
    }

    private void refreshMediaScanner(final String imagePath) {
        MediaScannerConnection.scanFile(this,
                new String[]{imagePath}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {

                        PickerActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                reloadAlbums();
                            }
                        });


                        Log.d("onActivityResult", "New image should appear in camera folder");
                    }
                });
    }

    private void reloadAlbums() {
        if (isImagesThumbnailShown()) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            getSupportFragmentManager().popBackStackImmediate(ImagesThumbnailFragment.TAG, 0);
            getSupportFragmentManager().popBackStackImmediate();
        }

        EventBus.getDefault().post(new Events.OnReloadAlbumsEvent());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    private boolean shouldShowSelectAll() {
        final Fragment imageThumbnailFragment = getSupportFragmentManager().findFragmentByTag(ImagesThumbnailFragment.TAG);
        return imageThumbnailFragment != null && imageThumbnailFragment.isVisible() && !mPickOptions.pickMode.equals(Picker.PickMode.SINGLE_IMAGE);
    }

    private void initCaptureMenuItem(final Menu menu) {
//        if (CameraSupport.isEnabled()) {
//            getMenuInflater().inflate(R.menu.menu_take_photo, menu);
//            Drawable captureIconDrawable = ContextCompat.getDrawable(this, R.drawable.ic_action_camera_white);
//            captureIconDrawable = DrawableCompat.wrap(captureIconDrawable);
//
//            DrawableCompat.setTint(captureIconDrawable, mPickOptions.captureItemIconTintColor);
//
//            menu.findItem(R.id.action_take_photo).setIcon(captureIconDrawable);
//        }
    }

    private void hideDeselectAll() {

    }

    private void showDeselectAll() {

        if (mPickOptions.pickMode == Picker.PickMode.SINGLE_IMAGE) {
            return;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int itemId = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private void deselectAllImages() {

        for (final ImageEntry imageEntry : mSelectedAlbum.imageList) {
            imageEntry.isPicked = false;
            sCheckedImages.remove(imageEntry);
        }

        EventBus.getDefault().post(new Events.OnUpdateImagesThumbnailEvent());

        hideDeselectAll();
        updateFab();

    }

    private void selectAllImages() {

        if (mSelectedAlbum == null) {
            mSelectedAlbum = EventBus.getDefault().getStickyEvent(Events.OnClickAlbumEvent.class).albumEntry;
        }

        if (sCheckedImages.size() < mPickOptions.limit || mPickOptions.limit == NO_LIMIT) {

            for (final ImageEntry imageEntry : mSelectedAlbum.imageList) {

                if (mPickOptions.limit != NO_LIMIT && sCheckedImages.size() + 1 > mPickOptions.limit) {
                    //Hit the limit
                    Toast.makeText(this, R.string.you_cant_check_more_images, Toast.LENGTH_SHORT).show();
                    break;
                }


                if (!imageEntry.isPicked) {
                    //To avoid repeated images
                    sCheckedImages.add(imageEntry);
                    imageEntry.isPicked = true;
                }
            }
        }
        EventBus.getDefault().post(new Events.OnUpdateImagesThumbnailEvent());
        updateFab();

        if (shouldShowDeselectAll()) {
            showDeselectAll();
        }


    }


    @Override
    public void onBackPressed() {

        if (isImagesThumbnailShown()) {
            //Return to albums fragment
            getSupportFragmentManager().popBackStack();
            tvTitle.setText(R.string.albums_title);
            mShouldShowUp = mPickOptions.backBtnInMainActivity;
            hideSelectAll();
            hideDeselectAll();

        } else if (isImagesPagerShown()) {
            //Returns to images thumbnail fragment

            if (mSelectedAlbum == null) {
                mSelectedAlbum = EventBus.getDefault().getStickyEvent(Events.OnClickAlbumEvent.class).albumEntry;
            }
            getSupportFragmentManager().popBackStack(ImagesThumbnailFragment.TAG, 0);
            tvTitle.setText(mSelectedAlbum.name);
            showSelectAll();

        } else {
            //User on album fragment
            super.onBackPressed();
            onCancel(false);
        }
    }

    public void onCamera() {
        finish();
        onCancel(true);
    }

    private boolean isImagesThumbnailShown() {
        return isFragmentShown(ImagesThumbnailFragment.TAG);
    }

    private boolean isImagesPagerShown() {
        return isFragmentShown(ImagesPagerFragment.TAG);
    }


    private boolean isFragmentShown(final String tag) {

        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);

        return fragment != null && fragment.isVisible();
    }

    private void hideSelectAll() {

    }

    private void showSelectAll() {
        if (mPickOptions.pickMode == Picker.PickMode.SINGLE_IMAGE) {
            //Keep it hidden
            return;
        }

    }

    private void handleMultipleModeAddition(final ImageEntry imageEntry) {

        if (mPickOptions.pickMode != Picker.PickMode.MULTIPLE_IMAGES) {
            return;
        }

        if (sCheckedImages.size() < mPickOptions.limit || mPickOptions.limit == NO_LIMIT) {
            imageEntry.isPicked = true;
            sCheckedImages.add(imageEntry);
            if(sCheckedImages.size() > 0) {
                tvCount.setVisibility(View.VISIBLE);
                tvCount.setText(String.valueOf(sCheckedImages.size()));
                mDoneFab.setVisibility(View.VISIBLE);
            } else {
                tvCount.setVisibility(View.GONE);
                mDoneFab.setVisibility(View.GONE);
            }
        } else {
            Toast.makeText(this, R.string.you_cant_check_more_images, Toast.LENGTH_SHORT).show();
            Log.i("onPickImage", "You can't check more images");
        }

    }

    private boolean shouldShowDeselectAll() {

        if (mSelectedAlbum == null) {
            return false;
        }

        boolean isAllImagesSelected = true;
        for (final ImageEntry albumChildImage : mSelectedAlbum.imageList) {

            if (!sCheckedImages.contains(albumChildImage)) {
                isAllImagesSelected = false;
                break;
            }
        }

        final Fragment imageThumbnailFragment = getSupportFragmentManager().findFragmentByTag(ImagesThumbnailFragment.TAG);

        return isAllImagesSelected && imageThumbnailFragment != null && imageThumbnailFragment.isVisible();
    }

    private void handleToolbarVisibility(final boolean show) {



    }



    public void onEvent(final Events.OnClickAlbumEvent albumEvent) {
        mSelectedAlbum = albumEvent.albumEntry;


        final ImagesThumbnailFragment imagesThumbnailFragment;


        if (getSupportFragmentManager().findFragmentByTag(ImagesThumbnailFragment.TAG) != null) {
            imagesThumbnailFragment = (ImagesThumbnailFragment) getSupportFragmentManager().findFragmentByTag(ImagesThumbnailFragment.TAG);
        } else {
            imagesThumbnailFragment = new ImagesThumbnailFragment();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, imagesThumbnailFragment, ImagesThumbnailFragment.TAG)
                .addToBackStack(ImagesThumbnailFragment.TAG)
                .commit();

        tvTitle.setText(albumEvent.albumEntry.name);
        //mShouldShowUp = true;
        showSelectAll();

        if (shouldShowDeselectAll()) {
            showDeselectAll();
        } else {
            hideDeselectAll();
        }


    }


    public void onEvent(final Events.OnPickImageEvent pickImageEvent) {
        if (mPickOptions.videosEnabled && mPickOptions.videoLengthLimit > 0 && pickImageEvent.imageEntry.isVideo) {
            // Check to see if the selected video is too long in length
            final MediaPlayer mp = MediaPlayer.create(this, Uri.parse(pickImageEvent.imageEntry.path));
            final int duration = mp.getDuration();
            mp.release();
            if (duration > (mPickOptions.videoLengthLimit)) {
                Toast.makeText(this, getResources().getString(R.string.video_too_long).replace("$", String.valueOf(mPickOptions.videoLengthLimit / 1000)), Toast.LENGTH_SHORT).show();
                return; // Don't allow selection
            }
        }

        if (mPickOptions.pickMode == Picker.PickMode.MULTIPLE_IMAGES) {
            handleMultipleModeAddition(pickImageEvent.imageEntry);


        } else if (mPickOptions.pickMode == Picker.PickMode.SINGLE_IMAGE) {
            //Single image pick mode


            //====START싱글 이미지 피커에서 뷰 페이져로 보여줄때 ===//
//            final ImagesPagerFragment pagerFragment;
//
//            if (getSupportFragmentManager().findFragmentByTag(ImagesPagerFragment.TAG) != null) {
//
//                pagerFragment = (ImagesPagerFragment) getSupportFragmentManager().findFragmentByTag(ImagesPagerFragment.TAG);
//            } else {
//                pagerFragment = new ImagesPagerFragment();
//            }
//
//
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container, pagerFragment, ImagesPagerFragment.TAG)
//                    .addToBackStack(ImagesPagerFragment.TAG)
//                    .commit();
            //====END 싱글 이미지 피커에서 뷰 페이져로 보여줄때 ===//



            //====START 싱글 이미지 피커에서 바로 선택할때
            mCurrentlyDisplayedImage = pickImageEvent.imageEntry;

            if (mPickOptions.pickMode == Picker.PickMode.SINGLE_IMAGE) {
                if(mCurrentlyDisplayedImage != null) {
                    sCheckedImages.add(mCurrentlyDisplayedImage);
                    mCurrentlyDisplayedImage.isPicked = true;
                }
            }

            super.finish();

            //New object because sCheckedImages will get cleared
            mPickOptions.pickListener.onPickedSuccessfully(new ArrayList<>(sCheckedImages), false);
            sCheckedImages.clear();
            EventBus.getDefault().removeAllStickyEvents();
            //====END 싱글 이미지 피커에서 바로 선택할때 ====//

        }


        updateFab();

    }


    public void onEvent(final Events.OnUnpickImageEvent unpickImageEvent) {
        sCheckedImages.remove(unpickImageEvent.imageEntry);
        unpickImageEvent.imageEntry.isPicked = false;
        if(sCheckedImages.size() > 0) {
            tvCount.setVisibility(View.VISIBLE);
            tvCount.setText(String.valueOf(sCheckedImages.size()));
            mDoneFab.setVisibility(View.VISIBLE);
        } else {
            tvCount.setVisibility(View.GONE);
            mDoneFab.setVisibility(View.GONE);
        }

        updateFab();
        hideDeselectAll();
    }

    public void onEvent(final Events.OnChangingDisplayedImageEvent newImageEvent) {
        mCurrentlyDisplayedImage = newImageEvent.currentImage;

    }

    public void onEvent(final Events.OnShowingToolbarEvent showingToolbarEvent) {
        handleToolbarVisibility(true);
    }


    public void onEvent(final Events.OnHidingToolbarEvent hidingToolbarEvent) {
        handleToolbarVisibility(false);
    }




}
