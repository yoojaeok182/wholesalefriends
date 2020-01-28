package com.wholesale.wholesalefriends.main.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.data.BannerLIstData;
import com.wholesale.wholesalefriends.module.util.LogUtil;

import java.util.ArrayList;

public class BannerPageAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater mInflater;
    private ArrayList<BannerLIstData> listBannerList;

    public BannerPageAdapter(Context ctx ,ArrayList<BannerLIstData> list) {
        context = ctx;
        mInflater = LayoutInflater.from(ctx);
        listBannerList = list;
    }

    public interface AdapterListener {
        void onClick(BannerLIstData item);
    }

    private AdapterListener adapterListener;

    public void setAdapterListener(AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    @Override
    public int getCount() {
        return listBannerList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = mInflater.inflate(R.layout.adapter_banner, null);
        ImageView ivBanner = v.findViewById(R.id.ivBanner);

        BannerLIstData item = listBannerList.get(position);
        if(item!=null){

            Glide.with(context).asBitmap().load(listBannerList.get(position).getImage()).listener(new RequestListener<Bitmap>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                    LogUtil.e(e.getMessage());
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                    ivBanner.setImageBitmap(resource);
                    return false;
                }
            }).into(ivBanner);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(adapterListener!=null){
                        adapterListener.onClick(item);
                    }
                }
            });
        }
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
