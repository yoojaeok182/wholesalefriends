package com.wholesale.wholesalefriends.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.data.BuildingListData;
import com.wholesale.wholesalefriends.main.data.CategoryStoreListData;
import com.wholesale.wholesalefriends.main.data.StoreListData;
import com.wholesale.wholesalefriends.main.data.StoreSearchListData;
import com.wholesale.wholesalefriends.module.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class CategoryStoreListAdapter extends RecyclerView.Adapter<CategoryStoreListAdapter.ViewHolder> {
    private static Context ctx;
    private LayoutInflater inflater;
    private List<StoreListData> arrayList = null;
    private int nCurrentPage = 1;
    private int pervPosition;

    public ListSelectItemListener listSelectItemListener;


    public interface ListSelectItemListener {
        void moreLoading(int page);
        void itemClick(int pos, StoreListData data);
    }

    public void setListSelectItemListener(ListSelectItemListener listSelectItemListener) {
        this.listSelectItemListener = listSelectItemListener;

    }

    public int getnCurrentPage() {
        return nCurrentPage;
    }

    public void setnCurrentPage(int nCurrentPage) {
        this.nCurrentPage = nCurrentPage;
    }

    public CategoryStoreListAdapter(Context context, ArrayList<StoreListData> data) {
        ctx = context;
        inflater = LayoutInflater.from(ctx);
        arrayList = data;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_category_store, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        StoreListData data = arrayList.get(position);
        if (data != null) {
            Glide.with(ctx).load(data.getImage()).error(R.drawable.no_image_01).into(holder.ivPhoto);

            holder.tvName.setText(data.getStore_name());
//            holder.tvStoreNumber.setVisibility(View.GONE);

            if(data.getFavorites()!=null && data.getFavorites().equals("1")){
                holder.btnAdd.setBackgroundResource(R.drawable.btn_11);
                holder.btnAdd.setText("내 거래처");
                holder.btnAdd.setEnabled(false);
            }else{
                holder.btnAdd.setBackgroundResource(R.drawable.btn_10);
                holder.btnAdd.setText("거래처 추가");
                holder.btnAdd.setEnabled(true);
            }

            holder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(listSelectItemListener!=null) listSelectItemListener.itemClick(position,data);
                    pervPosition = position;
                }
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPhoto;
        private TextView tvName;
//        private TextView tvStoreNumber;
        private Button btnAdd;
        private LinearLayout rootContainer;

        public ViewHolder(View convertView) {
            super(convertView);

            ivPhoto = convertView.findViewById(R.id.ivPhoto);
            tvName = convertView.findViewById(R.id.tvName);
//            tvStoreNumber = convertView.findViewById(R.id.tvStoreNumber);
            btnAdd = convertView.findViewById(R.id.btnAdd);
            rootContainer = convertView.findViewById(R.id.rootContainer);

           /* */

            rootContainer.post(new Runnable() {
                @Override
                public void run() {

                    int margin1 =  ctx.getResources().getDimensionPixelSize(R.dimen.item_margin_half1);
                    int clumWidth1 = ctx.getResources().getDimensionPixelSize(R.dimen.column_width4);
                    ImageUtil.requestImageView(ctx,clumWidth1,rootContainer,margin1,margin1);

                }
            });

        }

    }

    public StoreListData getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public List<StoreListData> getItems() {
        return arrayList;
    }

    public void add(StoreListData item, int position) {
        arrayList.add(position, item);
        notifyDataSetChanged();
    }

    public void addAll(List<StoreListData> items) {
        this.arrayList = items;
        notifyDataSetChanged();
    }

    public void clear() {
        arrayList.clear();
        notifyDataSetChanged();
    }

    public void remove(StoreListData item) {
        int position = arrayList.indexOf(item);
        arrayList.remove(item);
        notifyDataSetChanged();
    }

    public void set(int pos, StoreListData item) {
        arrayList.set(pos, item);
        notifyDataSetChanged();
    }
}
