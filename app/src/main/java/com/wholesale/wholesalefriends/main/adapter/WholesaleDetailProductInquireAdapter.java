package com.wholesale.wholesalefriends.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.wholesale.wholesalefriends.R;
import com.wholesale.wholesalefriends.main.data.ProductQnaListData;
import com.wholesale.wholesalefriends.main.data.ProductQnaReplyData;
import com.wholesale.wholesalefriends.module.util.ImageUtil;
import com.wholesale.wholesalefriends.module.util.Util;

import java.util.ArrayList;
import java.util.List;

public class WholesaleDetailProductInquireAdapter extends RecyclerView.Adapter<WholesaleDetailProductInquireAdapter.ViewHolder> {
    private static Context ctx;
    private LayoutInflater inflater;
    private List<ProductQnaListData> arrayList = null;
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
        void onClickItem(ProductQnaListData data, int pos);
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

    public WholesaleDetailProductInquireAdapter(Context context, ArrayList<ProductQnaListData> data) {
        ctx = context;
        inflater = LayoutInflater.from(ctx);
        arrayList = data;

    }

    public WholesaleDetailProductInquireAdapter(Context context, RequestManager manager, ArrayList<ProductQnaListData> data) {
        ctx = context;
        inflater = LayoutInflater.from(ctx);
        arrayList = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product_inquire, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductQnaListData data = arrayList.get(position);
        if (data != null) {

            holder.tvTitle.setText(data.getName());
            holder.tvRegDate.setText(data.getCreated_at());

            holder.tvContent.setText(data.getContent());

            holder.btnComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(adapterListener!=null) adapterListener.onClickItem(data,position);
                }
            });
            if(data.getWith_reply()!=null && data.getWith_reply().size()>0){
                holder.containerReply.setVisibility(View.VISIBLE);
                holder.containerReply.removeAllViews();
                List<ProductQnaReplyData> with_reply = data.getWith_reply();
                LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



                for(int i=0; i<with_reply.size();i++){
                    View view = inflater.inflate(R.layout.adapter_product_qna_reply, null);
                    RelativeLayout rootReplyContainer = view.findViewById(R.id.rootReplyContainer);
                    TextView tvReplyTitle = view.findViewById(R.id.tvReplyTitle);
                    TextView tvReplyRegDate = view.findViewById(R.id.tvReplyRegDate);
                    TextView tvReplyContent = view.findViewById(R.id.tvReplyContent);
                    tvReplyContent.setText(with_reply.get(i).getIs_notice() ==1?"[공지] "+ with_reply.get(i).getContent(): with_reply.get(i).getContent());
                    tvReplyRegDate.setText(with_reply.get(i).getCreated_at());
                    tvReplyTitle.setText(with_reply.get(i).getName());
                    holder.containerReply.addView(view);
                }


            }else{
                holder.containerReply.setVisibility(View.GONE);
            }
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
        private LinearLayout rootContainer;
        private TextView tvTitle;
        private TextView tvRegDate;
        private TextView tvContent;
        private Button btnComment;
        private LinearLayout containerReply;

        public ViewHolder(View convertView) {
            super(convertView);
            rootContainer = convertView.findViewById(R.id.rootContainer);
            tvTitle = convertView.findViewById(R.id.tvTitle);
            tvRegDate = convertView.findViewById(R.id.tvRegDate);
            tvContent = convertView.findViewById(R.id.tvContent);
            btnComment = convertView.findViewById(R.id.btnComment);
            containerReply = convertView.findViewById(R.id.containerReply);

        }

    }

    public ProductQnaListData getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public List<ProductQnaListData> getItems() {
        return arrayList;
    }

    public void add(ProductQnaListData item, int position) {
        arrayList.add(position, item);
        notifyDataSetChanged();
    }

    public void addAll(List<ProductQnaListData> items) {
        this.arrayList = items;
        notifyDataSetChanged();
    }

    public void clear() {
        arrayList.clear();
        notifyDataSetChanged();
    }

    public void remove(ProductQnaListData item) {
        int position = arrayList.indexOf(item);
        arrayList.remove(item);
        notifyDataSetChanged();
    }

    public void set(int pos, ProductQnaListData item) {
        arrayList.set(pos, item);
        notifyDataSetChanged();
    }
}
