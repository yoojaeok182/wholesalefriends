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
        void itemClick(int pos, PaymentListData data);

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
            holder.tvStoreName.setText(data.getStore_name() != null ? data.getStore_name() : "");
            Glide.with(ctx).load(data.getImage()).into(holder.ivPhoto);
            holder.tvItemName.setText(data.getName());

            holder.llayoutForOption01.setVisibility(View.GONE);
            holder.llayoutForOption02.setVisibility(View.GONE);
            holder.llayoutForOption03.setVisibility(View.GONE);

            holder.tvPaymentType.setText(data.getPayment());

            if(data.getPayment_id().equals("1")){
                //매장수령

            }else if(data.getPayment_id().equals("2")){
                //사입삼촌
                holder.llayoutForOption01.setVisibility(View.VISIBLE);
                holder.llayoutForOption02.setVisibility(View.VISIBLE);
                holder.tvPaymentTel.setText(data.getTel());
            }else if(data.getPayment_id().equals("3")){
                //계좌이체
                holder.llayoutForOption03.setVisibility(View.VISIBLE);
                holder.tvDepositorName.setText(data.getPayment_name());
                holder.tvBankInfo.setText(data.getAccount_info());
            }else if(data.getPayment_id().equals("4")){
                //건사입요청
            }



            holder.tvItemInfo.setText(data.getOption_1() + " / " + data.getOption_2());
            holder.tvItemCount.setText("수량 " + data.getAmount() + "개");



            holder.tvPaymentPrice.setText(Util.getFormattedPrice(Integer.valueOf(data.getPrice())));


        }


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvStoreName;
        private ImageView ivPhoto;
        private TextView tvItemName;
        private TextView tvItemInfo;
        private TextView tvItemCount;
        private TextView tvPaymentType;
        private TextView tvBankInfo;
        private LinearLayout llayoutForOption01;
        private TextView tvDepositorName;
        private LinearLayout llayoutForOption02;
        private TextView tvPaymentTel;
        private LinearLayout llayoutForOption03;
        private TextView tvPaymentPrice;
//        private EditText tvPaymentComment;
        private LinearLayout rootContainer;

        public ViewHolder(View convertView) {
            super(convertView);
            tvStoreName = convertView.findViewById(R.id.tvStoreName);
            ivPhoto = convertView.findViewById(R.id.ivPhoto);
            tvItemName = convertView.findViewById(R.id.tvItemName);
            tvItemInfo = convertView.findViewById(R.id.tvItemInfo);
            tvItemCount = convertView.findViewById(R.id.tvItemCount);
            tvPaymentType = convertView.findViewById(R.id.tvPaymentType);
            tvBankInfo = convertView.findViewById(R.id.tvBankInfo);
            llayoutForOption01 = convertView.findViewById(R.id.llayoutForOption01);
            tvDepositorName = convertView.findViewById(R.id.tvDepositorName);
            llayoutForOption02 = convertView.findViewById(R.id.llayoutForOption02);
            tvPaymentTel = convertView.findViewById(R.id.tvPaymentTel);
            llayoutForOption03 = convertView.findViewById(R.id.llayoutForOption03);
            tvPaymentPrice = convertView.findViewById(R.id.tvPaymentPrice);
//            tvPaymentComment = convertView.findViewById(R.id.tvPaymentComment);
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
