package net.yazeed44.imagepicker.ui;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import net.yazeed44.imagepicker.library.R;
import net.yazeed44.imagepicker.model.AlbumEntry;
import net.yazeed44.imagepicker.model.ImageEntry;
import net.yazeed44.imagepicker.util.Events;
import net.yazeed44.imagepicker.util.Picker;
import net.yazeed44.imagepicker.util.Util;

import de.greenrobot.event.EventBus;

/**
 * Created by yazeed44 on 11/23/14.
 */
public class ImagesThumbnailAdapter extends RecyclerView.Adapter<ImagesThumbnailAdapter.ImageViewHolder> implements Util.OnClickImage {


    protected final AlbumEntry mAlbum;
    protected final RecyclerView mRecyclerView;
    protected final Picker mPickOptions;

    protected final Drawable mVideoIcon;
    protected final Fragment mFragment;


    public ImagesThumbnailAdapter(final Fragment fragment, final AlbumEntry album, final RecyclerView recyclerView, Picker pickOptions) {
        mFragment = fragment;
        this.mAlbum = album;
        this.mRecyclerView = recyclerView;
        mPickOptions = pickOptions;


        mVideoIcon = createVideoIcon();
    }


    private Drawable createVideoIcon() {
        if (!mPickOptions.videosEnabled) {
            return null;
        }
        Drawable videoIcon = ContextCompat.getDrawable(mRecyclerView.getContext(), R.drawable.ic_play_arrow);
        videoIcon = DrawableCompat.wrap(videoIcon);
        DrawableCompat.setTint(videoIcon, mPickOptions.videoIconTintColor);
        return videoIcon;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View imageLayout = LayoutInflater.from(mRecyclerView.getContext()).inflate(R.layout.element_image, viewGroup, false);


        return new ImageViewHolder(imageLayout, this);
    }

    @Override
    public int getItemCount() {
        return mAlbum.imageList.size();
    }

    @Override
    public void onBindViewHolder(ImageViewHolder imageViewHolder, int position) {
        final ImageEntry imageEntry = mAlbum.imageList.get(position);
        setHeight(imageViewHolder.itemView);

        if (position % 3 == 1) {
            imageViewHolder.lineLeft.setVisibility(View.VISIBLE);
            imageViewHolder.lineRight.setVisibility(View.VISIBLE);
        } else {
            imageViewHolder.lineLeft.setVisibility(View.GONE);
            imageViewHolder.lineRight.setVisibility(View.GONE);
        }

        displayThumbnail(imageViewHolder, imageEntry);
        drawGrid(imageViewHolder, imageEntry);

    }

    @Override
    public void onClickImage(View layout, ImageView thumbnail, ImageView check) {

        final int position = Util.getPositionOfChild(layout, R.id.image_layout, mRecyclerView);
        final ImageViewHolder holder = (ImageViewHolder) mRecyclerView.getChildViewHolder(layout);
        if(mPickOptions.mCamera && position == 0) {
            PickerActivity.getInstance().onCamera();
        } else {
            pickImage(holder, mAlbum.imageList.get(position));
        }
    }


    public void setHeight(final View convertView) {
        final int height = mRecyclerView.getMeasuredWidth() / mRecyclerView.getResources().getInteger(R.integer.num_columns_images);
        convertView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
    }

    public void displayThumbnail(final ImageViewHolder holder, final ImageEntry photo) {


        Glide.with(mFragment)   .asBitmap()
                .load(photo.path)
                .centerCrop()
                .into(holder.thumbnail)
        ;


    }

    public void drawGrid(final ImageViewHolder holder, final ImageEntry imageEntry) {

        holder.pictureContainer.setVisibility(View.GONE);
        holder.check.setImageResource(R.drawable.select_pic_btn);
        holder.videoIcon.setVisibility(View.GONE);

        if(imageEntry.isCamera) {
            holder.pictureContainer.setVisibility(View.VISIBLE);
        } else {
            holder.pictureContainer.setVisibility(View.GONE);
            if (imageEntry.isPicked) {
                holder.check.setImageResource(R.drawable.select_pic_btn_on);
            } else {
                holder.check.setImageResource(R.drawable.select_pic_btn);
            }

            if (mPickOptions.pickMode == Picker.PickMode.SINGLE_IMAGE) {
                holder.check.setVisibility(View.GONE);
            }

            if (imageEntry.isVideo) {
                holder.thumbnail.setColorFilter(mPickOptions.videoThumbnailOverlayColor, PorterDuff.Mode.MULTIPLY);
                holder.videoIcon.setImageDrawable(mVideoIcon);
                holder.videoIcon.setVisibility(View.VISIBLE);
            }
        }

    }


    public void pickImage(final ImageViewHolder holder, final ImageEntry imageEntry) {

        if (imageEntry.isPicked) {
            //Unpick
            EventBus.getDefault().post(new Events.OnUnpickImageEvent(imageEntry));
        } else {
            //pick
            EventBus.getDefault().postSticky(new Events.OnPickImageEvent(imageEntry));
        }

        drawGrid(holder, imageEntry);
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        private final ImageView thumbnail;
        private final ImageView check;
        private final ImageView videoIcon;
        private final ImageView lineLeft;
        private final ImageView lineRight;
        private final LinearLayout pictureContainer;
        public ImageViewHolder(final View itemView, final Util.OnClickImage listener) {
            super(itemView);

            thumbnail = (ImageView) itemView.findViewById(R.id.image_thumbnail);
            check = (ImageView) itemView.findViewById(R.id.image_check);
            videoIcon = (ImageView) itemView.findViewById(R.id.image_video_icon);
            pictureContainer = (LinearLayout) itemView.findViewById(R.id.pictureContainer);
            lineLeft = (ImageView) itemView.findViewById(R.id.line_left);
            lineRight = (ImageView) itemView.findViewById(R.id.line_right);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickImage(itemView, thumbnail, check);
                }
            });


        }
    }


}
