package com.wholesale.wholesalefriends.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.base.MyApplication;
import com.wholesale.wholesalefriends.main.data.BannerLIstData;

import java.util.ArrayList;

public class BannerPageAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater mInflater;
    private ArrayList<BannerLIstData> listBannerList;
    private ImageView ivBanner;

    public BannerPageAdapter(Context ctx, ArrayList<BannerLIstData> list) {
        context = ctx;
        mInflater = LayoutInflater.from(MyApplication.get_instance().getApplicationContext());
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
        ivBanner = v.findViewById(R.id.ivBanner);

        BannerLIstData item = listBannerList.get(position);
        if(item!=null){
            Glide.with(context).load(item.getImage()).into(ivBanner);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(adapterListener!=null){
                        adapterListener.onClick(item);
                    }
                }
            });
        }
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
