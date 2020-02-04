package com.wholesale.wholesalefriends.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.data.CartListProductData;
import com.wholesale.wholesalefriends.module.util.Util;

import java.util.ArrayList;
import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private Context ctx;
    private LayoutInflater inflater;
    private List<CartListProductData> arrayList = null;
    private int nCurrentPage = 1;
    private int pervPosition;

    public ListSelectItemListener listSelectItemListener;



    public interface ListSelectItemListener {
        void itemClick(int pos, boolean isCheck, CartListProductData data);
        void modAmount(int pos, int count,CartListProductData data);
        void itemDelete(int pos, CartListProductData data);
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

    public CartListAdapter(Context context, ArrayList<CartListProductData> data) {
        ctx = context;
        inflater = LayoutInflater.from(ctx);
        arrayList = data;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_shopping_basket, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CartListProductData data = arrayList.get(position);
        if (data != null) {
           holder.tvStoreName.setText(data.getStore_name());
           holder.tvItemName.setText(data.getName());
           holder.tvItemInfo.setText(data.getOption_1() +" / " +data.getOption_2());
           holder.tvItemPrice.setText(Util.getFormattedPrice(data.getPrice()));

            Glide.with(ctx).load(data.getImage()).into(holder.ivPhoto);
            holder.ivCheck.setBackgroundResource(R.drawable.check_default);
            if(data.isCheck()){
                holder.ivCheck.setBackgroundResource(R.drawable.check_on);
            }
            holder.tvAmount.setText(data.getAmount()+"");

            holder.ivCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(data.isCheck()){
                        if(listSelectItemListener!=null)listSelectItemListener.itemClick(position,false,data);
                    }else{
                        if(listSelectItemListener!=null)listSelectItemListener.itemClick(position,true,data);
                    }
                }
            });

            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(data.isCheck()){
                        if(listSelectItemListener!=null) listSelectItemListener.itemDelete(position,data);
                    }
                }
            });

            holder.btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(listSelectItemListener!=null) listSelectItemListener.modAmount(position,data.getAmount()+1,data);
                }
            });

            holder.btnSubtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(data.getAmount()<=0){
                        return;
                    }
                    if(listSelectItemListener!=null) listSelectItemListener.modAmount(position,data.getAmount()-1,data);
                }
            });

        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCheck;
        private TextView tvItemName;
        private LinearLayout btnItemCheck;
        private Button btnDelete;
        private ImageView ivPhoto;
        private TextView tvItemInfo;
        private TextView tvItemPrice;
        private ImageButton btnSubtract;
        private ImageButton btnPlus;
        private LinearLayout rootContainer;

        private TextView tvAmount;
        private TextView tvStoreName;
        public ViewHolder(View convertView) {
            super(convertView);
            tvAmount= convertView.findViewById(R.id.tvAmount);
            tvStoreName= convertView.findViewById(R.id.tvStoreName);
            ivCheck = convertView.findViewById(R.id.ivCheck);
            tvItemName = convertView.findViewById(R.id.tvItemName);
            btnItemCheck = convertView.findViewById(R.id.btnItemCheck);
            btnDelete = convertView.findViewById(R.id.btnDelete);
            ivPhoto = convertView.findViewById(R.id.ivPhoto);
            tvItemInfo = convertView.findViewById(R.id.tvItemInfo);
            tvItemPrice = convertView.findViewById(R.id.tvItemPrice);
            btnSubtract = convertView.findViewById(R.id.btnSubtract);
            btnPlus = convertView.findViewById(R.id.btnPlus);
            rootContainer = convertView.findViewById(R.id.rootContainer);
        }

    }

    public CartListProductData getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public List<CartListProductData> getItems() {
        return arrayList;
    }

    public void add(CartListProductData item, int position) {
        arrayList.add(position, item);
        notifyDataSetChanged();
    }

    public void addAll(List<CartListProductData> items) {
        this.arrayList = items;
        notifyDataSetChanged();
    }

    public void clear() {
        arrayList.clear();
        notifyDataSetChanged();
    }

    public void remove(CartListProductData item) {
        int position = arrayList.indexOf(item);
        arrayList.remove(item);
        notifyDataSetChanged();
    }

    public void set(int pos, CartListProductData item) {
        arrayList.set(pos, item);
        notifyDataSetChanged();
    }
}
