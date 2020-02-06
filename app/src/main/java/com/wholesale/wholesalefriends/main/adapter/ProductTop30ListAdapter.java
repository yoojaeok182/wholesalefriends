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
import com.bumptech.glide.RequestManager;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.data.TopStoreListData;
import com.wholesale.wholesalefriends.module.util.ImageUtil;
import com.wholesale.wholesalefriends.module.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ProductTop30ListAdapter extends RecyclerView.Adapter<ProductTop30ListAdapter.ViewHolder> {
    private static Context ctx;
    private LayoutInflater inflater;
    private List<TopStoreListData> arrayList = null;
    private int nCurrentPage = 1;

    public AdapterListener adapterListener;



    public interface AdapterListener {
        void moreLoading(int page);

        void onClickItem(TopStoreListData data, int pos);
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

    public ProductTop30ListAdapter(Context context, ArrayList<TopStoreListData> data) {
        ctx = context;
        inflater = LayoutInflater.from(ctx);
        arrayList = data;

    }

    public ProductTop30ListAdapter(Context context, RequestManager manager, ArrayList<TopStoreListData> data) {
        ctx = context;
        inflater = LayoutInflater.from(ctx);
        arrayList = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product_top30_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TopStoreListData data = arrayList.get(position);
        if (data != null) {
            Glide.with(ctx).load(data.getImage()).into(holder.ivPhoto);
            holder.tvNum.setText((position+1)+"");
            holder.rootContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (adapterListener != null) adapterListener.onClickItem(data, position);
                }
            });
            holder.ivLeftDummy.setVisibility(View.GONE);
            if(position ==0){
                holder.ivLeftDummy.setVisibility(View.GONE);
            }
            holder.ivRightDummy.setVisibility(View.VISIBLE);
            if (position == arrayList.size() - 1) {
                holder.ivRightDummy.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivLeftDummy;
        private ImageView ivPhoto;
        private TextView tvNum;
        private RelativeLayout rootContainer;
        private ImageView ivRightDummy;

        public ViewHolder(View convertView) {
            super(convertView);
            ivLeftDummy = convertView.findViewById(R.id.ivLeftDummy);
            ivPhoto = convertView.findViewById(R.id.ivPhoto);
            tvNum = convertView.findViewById(R.id.tvNum);
            rootContainer = convertView.findViewById(R.id.rootContainer);
            ivRightDummy = convertView.findViewById(R.id.ivRightDummy);
        }

    }

    public TopStoreListData getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public List<TopStoreListData> getItems() {
        return arrayList;
    }

    public void add(TopStoreListData item, int position) {
        arrayList.add(position, item);
        notifyDataSetChanged();
    }

    public void addAll(List<TopStoreListData> items) {
        this.arrayList = items;
        notifyDataSetChanged();
    }

    public void clear() {
        arrayList.clear();
        notifyDataSetChanged();
    }

    public void remove(TopStoreListData item) {
        int position = arrayList.indexOf(item);
        arrayList.remove(item);
        notifyDataSetChanged();
    }

    public void set(int pos, TopStoreListData item) {
        arrayList.set(pos, item);
        notifyDataSetChanged();
    }
}
