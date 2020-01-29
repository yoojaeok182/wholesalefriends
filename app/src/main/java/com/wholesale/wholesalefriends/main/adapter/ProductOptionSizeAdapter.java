package com.wholesale.wholesalefriends.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.data.ProductViewOptionColorData;
import com.wholesale.wholesalefriends.main.data.ProductViewOptionSizeData;

import java.util.ArrayList;
import java.util.List;

public class ProductOptionSizeAdapter extends RecyclerView.Adapter<ProductOptionSizeAdapter.ViewHolder> {
    private Context ctx;
    private LayoutInflater inflater;
    private List<ProductViewOptionSizeData> arrayList = null;
    private int nCurrentPage = 1;
    private int pervPosition;

    public ListSelectItemListener listSelectItemListener;


    public interface ListSelectItemListener {
        void itemClick(int pos,int prevPos, ProductViewOptionSizeData data);
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

    public ProductOptionSizeAdapter(Context context, ArrayList<ProductViewOptionSizeData> data) {
        ctx = context;
        inflater = LayoutInflater.from(ctx);
        arrayList = data;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product_option_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductViewOptionSizeData data = arrayList.get(position);
        if (data != null) {
            holder.tvItem.setText(data.getCode_name());
            holder.btnSelectItem.setBackgroundResource(R.drawable.btn_select_01_select);
            if(data.isSelect()){
                holder.btnSelectItem.setBackgroundResource(R.drawable.roundbox_02);
            }
            holder.btnSelectItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listSelectItemListener!=null)listSelectItemListener.itemClick(position,pervPosition,data);
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
        private TextView tvItem;
        private LinearLayout btnSelectItem;

        public ViewHolder(View convertView) {
            super(convertView);

            tvItem = convertView.findViewById(R.id.tvItem);
            btnSelectItem = convertView.findViewById(R.id.btnSelectItem);
        }

    }

    public ProductViewOptionSizeData getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public List<ProductViewOptionSizeData> getItems() {
        return arrayList;
    }

    public void add(ProductViewOptionSizeData item, int position) {
        arrayList.add(position, item);
        notifyDataSetChanged();
    }

    public void addAll(List<ProductViewOptionSizeData> items) {
        this.arrayList = items;
        notifyDataSetChanged();
    }

    public void clear() {
        arrayList.clear();
        notifyDataSetChanged();
    }

    public void remove(ProductViewOptionSizeData item) {
        int position = arrayList.indexOf(item);
        arrayList.remove(item);
        notifyDataSetChanged();
    }

    public void set(int pos, ProductViewOptionSizeData item) {
        arrayList.set(pos, item);
        notifyDataSetChanged();
    }
}
