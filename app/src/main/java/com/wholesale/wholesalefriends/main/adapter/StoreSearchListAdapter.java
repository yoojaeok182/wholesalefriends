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
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.data.ProductListData;
import com.wholesale.wholesalefriends.main.data.StoreSearchListData;
import com.wholesale.wholesalefriends.module.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class StoreSearchListAdapter extends RecyclerView.Adapter<StoreSearchListAdapter.ViewHolder> {
    private static Context ctx;
    private LayoutInflater inflater;
    private List<StoreSearchListData> arrayList = null;
    private int nCurrentPage = 1;
    private int pervPosition;

    public ListMeetingPlaceListener listMeetingPlaceListener;


    public interface ListMeetingPlaceListener {
        void moreLoading(int page);
        void itemClick(int pos, StoreSearchListData data);
    }

    public void setMeetingPlaceListener(ListMeetingPlaceListener listMeetingPlaceListener) {
        this.listMeetingPlaceListener = listMeetingPlaceListener;

    }

    public int getnCurrentPage() {
        return nCurrentPage;
    }

    public void setnCurrentPage(int nCurrentPage) {
        this.nCurrentPage = nCurrentPage;
    }

    public StoreSearchListAdapter(Context context, ArrayList<StoreSearchListData> data) {
        ctx = context;
        inflater = LayoutInflater.from(ctx);
        arrayList = data;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_store, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        StoreSearchListData data = arrayList.get(position);
        if (data != null) {
            String photo ="";
            if(data.getStore_photo().indexOf("http")>-1){
                photo = data.getStore_photo();
            }else{
                photo = "http://app.asadalin.com/"+data.getStore_photo();
            }
            Glide.with(ctx).load(photo).into(holder.ivPhoto);

            holder.tvName.setText(data.getStore_name());
            holder.ivCheck.setBackgroundResource(R.drawable.check_default);
            holder.rootContainer.setBackgroundResource(R.drawable.box_off_01);
            if(data.isCheck()){
                holder.ivCheck.setBackgroundResource(R.drawable.check_on);
                holder.rootContainer.setBackgroundResource(R.drawable.box_on_01);
            }

            holder.rootContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(pervPosition !=position){

                        arrayList.get(pervPosition).setCheck(false);
                    }
                    data.setCheck(true);

                    if(listMeetingPlaceListener!=null) listMeetingPlaceListener.itemClick(position,data);
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
        private ImageView ivCheck;
        private LinearLayout rootContainer;

        public ViewHolder(View convertView) {
            super(convertView);

            ivPhoto = convertView.findViewById(R.id.ivPhoto);
            tvName = convertView.findViewById(R.id.tvName);
            ivCheck = convertView.findViewById(R.id.ivCheck);
            rootContainer = convertView.findViewById(R.id.rootContainer);

          /*  rootContainer.post(new Runnable() {
                @Override
                public void run() {

                    int margin1 =  ctx.getResources().getDimensionPixelSize(R.dimen.item_margin_half1);
                    int clumWidth1 = ctx.getResources().getDimensionPixelSize(R.dimen.column_width4);
                    ImageUtil.requestImageView(ctx,clumWidth1,rootContainer,margin1,margin1);

                }
            });*/
        }

    }

    public StoreSearchListData getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public List<StoreSearchListData> getItems() {
        return arrayList;
    }

    public void add(StoreSearchListData item, int position) {
        arrayList.add(position, item);
        notifyDataSetChanged();
    }

    public void addAll(List<StoreSearchListData> items) {
        this.arrayList = items;
        notifyDataSetChanged();
    }

    public void clear() {
        arrayList.clear();
        notifyDataSetChanged();
    }

    public void remove(StoreSearchListData item) {
        int position = arrayList.indexOf(item);
        arrayList.remove(item);
        notifyDataSetChanged();
    }

    public void set(int pos, StoreSearchListData item) {
        arrayList.set(pos, item);
        notifyDataSetChanged();
    }
}
