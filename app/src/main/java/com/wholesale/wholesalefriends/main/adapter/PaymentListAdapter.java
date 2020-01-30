package com.wholesale.wholesalefriends.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.data.PaymentListData;
import com.wholesale.wholesalefriends.module.util.Util;

import java.util.ArrayList;
import java.util.List;

public class PaymentListAdapter extends RecyclerView.Adapter<PaymentListAdapter.ViewHolder> {
    private Context ctx;
    private LayoutInflater inflater;
    private List<PaymentListData> arrayList = null;
    private int nCurrentPage = 1;
    private int pervPosition;

    public ListSelectItemListener listSelectItemListener;



    public interface ListSelectItemListener {
        void itemClick(int pos,PaymentListData data);

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

    public PaymentListAdapter(Context context, ArrayList<PaymentListData> data) {
        ctx = context;
        inflater = LayoutInflater.from(ctx);
        arrayList = data;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_shopping_payment, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PaymentListData data = arrayList.get(position);
        if (data != null) {
            holder.tvStoreName.setText(data.getStore_name()!=null ? data.getStore_name():"");
            Glide.with(ctx).load(data.getImage()).into(holder.ivPhoto);


            holder.tvItemName.setText(data.getName());

            holder.tvItemInfo.setText(data.getOption_1() + " / " + data.getOption_2());
            holder.tvItemCount.setText("수량 "+data.getAmount()+"개");


            holder.tvPaymentType.setText(data.getPayment());
            holder.tvPaymentTel.setText(data.getTel());
            holder.tvPaymentPrice.setText(Util.getFormattedPrice(data.getPrice()) + "원");


        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPhoto;
        private TextView tvItemInfo;
        private TextView tvItemCount;
        private TextView tvPaymentType;
        private ImageButton btnTypeChange;
        private TextView tvPaymentTel;
        private ImageButton btnNumberChange;
        private TextView tvPaymentPrice;
        private EditText tvPaymentComment;
        private LinearLayout rootContainer;
        private TextView tvStoreName;
        private TextView tvItemName;

        public ViewHolder(View convertView) {
            super(convertView);
            tvItemName = convertView.findViewById(R.id.tvItemName);

            tvStoreName= convertView.findViewById(R.id.tvStoreName);
            ivPhoto = convertView.findViewById(R.id.ivPhoto);
            tvItemInfo = convertView.findViewById(R.id.tvItemInfo);
            tvItemCount = convertView.findViewById(R.id.tvItemCount);
            tvPaymentType = convertView.findViewById(R.id.tvPaymentType);
            btnTypeChange =convertView. findViewById(R.id.btnTypeChange);
            tvPaymentTel = convertView.findViewById(R.id.tvPaymentTel);
            btnNumberChange = convertView.findViewById(R.id.btnNumberChange);
            tvPaymentPrice = convertView.findViewById(R.id.tvPaymentPrice);
            tvPaymentComment = convertView.findViewById(R.id.tvPaymentComment);
            rootContainer = convertView.findViewById(R.id.rootContainer);

        }

    }

    public PaymentListData getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public List<PaymentListData> getItems() {
        return arrayList;
    }

    public void add(PaymentListData item, int position) {
        arrayList.add(position, item);
        notifyDataSetChanged();
    }

    public void addAll(List<PaymentListData> items) {
        this.arrayList = items;
        notifyDataSetChanged();
    }

    public void clear() {
        arrayList.clear();
        notifyDataSetChanged();
    }

    public void remove(PaymentListData item) {
        int position = arrayList.indexOf(item);
        arrayList.remove(item);
        notifyDataSetChanged();
    }

    public void set(int pos, PaymentListData item) {
        arrayList.set(pos, item);
        notifyDataSetChanged();
    }
}
