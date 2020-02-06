package com.wholesale.wholesalefriends.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.data.ProductListData;
import com.wholesale.wholesalefriends.module.util.ImageUtil;
import com.wholesale.wholesalefriends.module.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ProductList2Adapter extends RecyclerView.Adapter<ProductList2Adapter.ViewHolder> {
    private static Context ctx;
    private LayoutInflater inflater;
    private List<ProductListData> arrayList = null;
    private int nCurrentPage = 1;

    private boolean isProductManager;


    public boolean isProductManager() {
        return isProductManager;
    }

    public void setProductManager(boolean productManager) {
        isProductManager = productManager;
    }

    public AdapterListener adapterListener;



    public interface AdapterListener {
        void moreLoading(int page);
        void onCheckItem(ProductListData data, int pos,boolean isCheck);
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

    public ProductList2Adapter(Context context, ArrayList<ProductListData> data) {
        ctx = context;
        inflater = LayoutInflater.from(ctx);
        arrayList = data;

    }

    public ProductList2Adapter(Context context, RequestManager manager, ArrayList<ProductListData> data) {
        ctx = context;
        inflater = LayoutInflater.from(ctx);
        arrayList = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product_item2, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductListData data = arrayList.get(position);
        if (data != null) {
            Glide.with(ctx).load(data.getImage()).into(holder.ivPhoto);
            holder.tvInfo.setText(data.getStore_name());
            holder.tvLike.setText(data.getLike() + "");
            holder.tvName.setText(data.getName());
            holder.tvprice.setText(Util.getFormattedPrice(Integer.valueOf(data.getPrice())));
            holder.tvRegDate.setText(Util.getSplitRegDate(data.getCreated_at()));


            holder.tvTopStore.setVisibility(View.GONE);
            holder.layoutForSoldOut.setVisibility(View.GONE);
            if(data.getIs_soldout()!=null&& data.getIs_soldout().equals("1")){
                holder.layoutForSoldOut.setVisibility(View.VISIBLE);
            }

            if(data.getIs_top()!=null&& data.getIs_top().equals("1")){
                holder.tvTopStore.setVisibility(View.VISIBLE);
            }
            holder.ivCheck.setVisibility(View.GONE);
            holder.ivCheck.setBackgroundResource(R.drawable.check_default);

            if(isProductManager()){
                holder.ivCheck.setVisibility(View.VISIBLE);
            }
            if(data.isCheck()){
                holder.ivCheck.setBackgroundResource(R.drawable.check_on);
            }
            holder.rootContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isProductManager()){
                        if(data.isCheck()){
                            if (adapterListener != null) adapterListener.onCheckItem(data, position,false);
                        }else{
                            if (adapterListener != null) adapterListener.onCheckItem(data, position,true);
                        }

                        return;
                    }
                    if (adapterListener != null) adapterListener.onClickItem(data, position);
                }
            });


            if (position == arrayList.size() - 1) {
                if (adapterListener != null) adapterListener.moreLoading(getnCurrentPage() + 1);
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RequestManager requestManager;
        private ImageView ivPhoto;
        private RelativeLayout layoutForSoldOut;
        private ImageView ivCheck;
        private TextView tvTopStore;
        private TextView tvName;
        private TextView tvprice;
        private TextView tvInfo;
        private TextView tvLike;
        private TextView tvRegDate;
        private LinearLayout rootContainer;

        public ViewHolder(View convertView) {
            super(convertView);
            ivPhoto = convertView.findViewById(R.id.ivPhoto);
            layoutForSoldOut = convertView.findViewById(R.id.layoutForSoldOut);
            ivCheck = convertView.findViewById(R.id.ivCheck);
            tvTopStore = convertView.findViewById(R.id.tvTopStore);
            tvName = convertView.findViewById(R.id.tvName);
            tvprice = convertView.findViewById(R.id.tvprice);
            tvInfo = convertView.findViewById(R.id.tvInfo);
            tvLike = convertView.findViewById(R.id.tvLike);
            tvRegDate = convertView.findViewById(R.id.tvRegDate);
            rootContainer = convertView.findViewById(R.id.rootContainer);

            ivPhoto.post(new Runnable() {
                @Override
                public void run() {
                    int clumWidth = ctx.getResources().getDimensionPixelSize(R.dimen.column_width4);
                    int margin = ctx.getResources().getDimensionPixelSize(R.dimen.item_margin_half2);
                    ImageUtil.requestImageView(ctx, clumWidth, ivPhoto, margin, margin);
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
