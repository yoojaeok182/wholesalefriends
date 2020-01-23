package net.yazeed44.imagepicker.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.yazeed44.imagepicker.library.R;
import net.yazeed44.imagepicker.model.AlbumEntry;
import net.yazeed44.imagepicker.model.ImageEntry;
import net.yazeed44.imagepicker.util.Events;
import net.yazeed44.imagepicker.util.Picker;

import de.greenrobot.event.EventBus;


/**
 * Created by yazeed44 on 11/23/14.
 */
public class ImagesThumbnailFragment extends Fragment {

    public static final String TAG = ImagesThumbnailFragment.class.getSimpleName();
    protected RecyclerView mImagesRecycler;
    protected Picker mPickOptions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        mImagesRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_image_browse, container, false);

        setupRecycler();

        mPickOptions = EventBus.getDefault().getStickyEvent(Events.OnPublishPickOptionsEvent.class).options;


        return mImagesRecycler;
    }

    @Override
    public void onStart() {
        EventBus.getDefault().registerSticky(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    protected void setupRecycler() {

        mImagesRecycler.setHasFixedSize(true);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.num_columns_images));

        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mImagesRecycler.setLayoutManager(gridLayoutManager);


    }


    public void onEvent(final Events.OnClickAlbumEvent event) {
        AlbumEntry albumEntry = event.albumEntry;
        if(mPickOptions.mCamera && albumEntry.imageList.get(0).isCamera == false) {
            ImageEntry imageEntry = new ImageEntry(new ImageEntry.Builder(""));
            imageEntry.isCamera = true;
            albumEntry.imageList.add(0, imageEntry);
        }
        mImagesRecycler.setAdapter(new ImagesThumbnailAdapter(this, albumEntry, mImagesRecycler, mPickOptions));
    }


    public void onEvent(final Events.OnAttachFabEvent fabEvent) {

    }

    public void onEvent(final Events.OnUpdateImagesThumbnailEvent redrawImage) {
        mImagesRecycler.getAdapter().notifyDataSetChanged();

    }


}
