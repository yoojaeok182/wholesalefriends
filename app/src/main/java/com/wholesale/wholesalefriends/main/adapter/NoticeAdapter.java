package com.wholesale.wholesalefriends.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.data.NoticeListData;

import java.util.ArrayList;
import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
    private Context ctx;
    private LayoutInflater inflater;
    private List<NoticeListData> arrayList = null;
    private int nCurrentPage = 1;
    private int pervPosition;

    public ListSelectItemListener listSelectItemListener;


    public interface ListSelectItemListener {
        void moreLoading(int page);
        void itemClick(int pos, NoticeListData data);
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

    public NoticeAdapter(Context context, ArrayList<NoticeListData> data) {
        ctx = context;
        inflater = LayoutInflater.from(ctx);
        arrayList = data;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notice, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        NoticeListData data = arrayList.get(position);
        if (data != null) {
            holder.tvTitle.setText(data.getTitle());
            holder.tvContent.setText(data.getContent());
            if(data.isNew()){
                holder.tvNew.setVisibility(View.VISIBLE);
            }else{
                holder.tvNew.setVisibility(View.GONE);
            }

            holder.rootContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(holder.llayoutForContent.getVisibility()== View.VISIBLE){
                        holder.icArrow.setBackgroundResource(R.drawable.icon_droparrow);
                        holder.llayoutForContent.setVisibility(View.GONE);
                    }else{
                        holder.icArrow.setBackgroundResource(R.drawable.icon_uparrow);
                        holder.llayoutForContent.setVisibility(View.VISIBLE);
                    }
                }
            });


        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRegDate;
        private TextView tvTitle;
        private TextView tvContent;
        private ImageView icArrow;
        private LinearLayout rootContainer;
        private LinearLayout llayoutForContent;

        private TextView tvNew;
        public ViewHolder(View convertView) {
            super(convertView);

            tvNew = convertView.findViewById(R.id.tvNew);
            tvRegDate = convertView.findViewById(R.id.tvRegDate);
            tvTitle = convertView.findViewById(R.id.tvTitle);
            tvContent = convertView.findViewById(R.id.tvContent);
            icArrow = convertView.findViewById(R.id.icArrow);
            rootContainer = convertView.findViewById(R.id.rootContainer);
            llayoutForContent = convertView.findViewById(R.id.llayoutForContent);
        }

    }

    public NoticeListData getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public List<NoticeListData> getItems() {
        return arrayList;
    }

    public void add(NoticeListData item, int position) {
        arrayList.add(position, item);
        notifyDataSetChanged();
    }

    public void addAll(List<NoticeListData> items) {
        this.arrayList = items;
        notifyDataSetChanged();
    }

    public void clear() {
        arrayList.clear();
        notifyDataSetChanged();
    }

    public void remove(NoticeListData item) {
        int position = arrayList.indexOf(item);
        arrayList.remove(item);
        notifyDataSetChanged();
    }

    public void set(int pos, NoticeListData item) {
        arrayList.set(pos, item);
        notifyDataSetChanged();
    }
}
