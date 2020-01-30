package com.wholesale.wholesalefriends.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.data.BestProductListData;
import com.wholesale.wholesalefriends.main.data.ProductListData;
import com.wholesale.wholesalefriends.module.util.ImageUtil;
import com.wholesale.wholesalefriends.module.util.Util;

import java.util.ArrayList;
import java.util.List;

public class HomeMain03ListAdapter extends RecyclerView.Adapter<HomeMain03ListAdapter.ViewHolder> {
    private static Context ctx;
    private LayoutInflater inflater;
    private List<ProductListData> arrayList = null;
    private int nCurrentPage = 1;

    public AdapterListener adapterListener;

    public interface AdapterListener {
        void moreLoading(int page);
        void onClickItem(ProductListData data, int pos);
    }

    public void setAdapterListener(AdapterListener listMeetingPlaceListener) {
        this.adapterListener = listMeetingPlaceListener;

    }

    public int getnCurrentPage() {
        return nCurrentPage;
    }

    public void setnCurrentPage(int nCurrentPage) {
        this.nCurrentPage = nCurrentPage;
    }

    public HomeMain03ListAdapter(Context context, ArrayList<ProductListData> data) {
        ctx = context;
        inflater = LayoutInflater.from(ctx);
        arrayList = data;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductListData data = arrayList.get(position);
        if (data!=null){
            Glide.with(ctx).load(data.getImage()).into(holder.ivPhoto);
            holder.tvInfo.setText(data.getStore_name());
            holder.tvLike.setText(data.getLike()+"");
            holder.tvName.setText(data.getName());
            holder.tvprice.setText(Util.getFormattedPrice(Integer.valueOf(data.getPrice())));
            holder.tvRegDate.setText(Util.getSplitRegDate(data.getCreated_at()));
            holder.tvRanking.setText("SALE");
            holder.tvImageCount.setText("+"+data.getImage_count());
            holder.rootContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(adapterListener!=null) adapterListener.onClickItem(data,position);
                }
            });


            if(position == arrayList.size() - 1) {
                if(adapterListener!=null) adapterListener.moreLoading(getnCurrentPage()+1);
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvName;
        TextView tvprice;
        TextView tvInfo;
        TextView tvLike;
        TextView tvRegDate;
        RelativeLayout rootContainer;
        TextView tvRanking;
        TextView tvImageCount;

        public ViewHolder(View convertView) {
            super(convertView);
            tvImageCount = convertView.findViewById(R.id.tvImageCount);
            tvRanking = convertView.findViewById(R.id.tvTopRanking);
            ivPhoto = convertView.findViewById(R.id.ivPhoto);
            tvName=convertView. findViewById(R.id.tvName);
            tvprice = convertView.findViewById(R.id.tvprice);
            tvInfo = convertView.findViewById(R.id.tvInfo);
            tvLike = convertView.findViewById(R.id.tvLike);
            tvRegDate =convertView. findViewById(R.id.tvRegDate);
            rootContainer=convertView. findViewById(R.id.rootContainer);

            ivPhoto.post(new Runnable() {
                @Override
                public void run() {
                    int clumWidth = ctx.getResources().getDimensionPixelSize(R.dimen.column_width4);
                    int margin =  ctx.getResources().getDimensionPixelSize(R.dimen.item_margin_half2);
                    ImageUtil.requestImageView(ctx,clumWidth,ivPhoto,margin,margin);
                }
            });
        }

    }

    public ProductListData getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public List<ProductListData> getItems() {
        return arrayList;
    }

    public void add(ProductListData item, int position) {
        arrayList.add(position, item);
        notifyDataSetChanged();
    }

    public void addAll(List<ProductListData> items) {
        this.arrayList = items;
        notifyDataSetChanged();
    }

    public void clear() {
        arrayList.clear();
        notifyDataSetChanged();
    }

    public void remove(ProductListData item) {
        int position = arrayList.indexOf(item);
        arrayList.remove(item);
        notifyDataSetChanged();
    }

    public void set(int pos, ProductListData item) {
        arrayList.set(pos, item);
        notifyDataSetChanged();
    }
}
