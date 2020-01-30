package com.wholesale.wholesalefriends.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Handasoft_dev01 on 2016-10-26.
 */

public class AutofitRecyclerView extends RecyclerView {
    private GridLayoutManager manager;
    private int columnWidth = -1;
    private int viewWidth;
    private int nSpanCnt = 1;

    public int getnSpanCnt() {
        if (nSpanCnt == 1&&columnWidth > 0) {
            int spanCount = Math.max(1, getMeasuredWidth() / columnWidth);
            manager.setSpanCount(spanCount);
            nSpanCnt = spanCount;
        }
        Log.i("TAG","span_cnt: "+ nSpanCnt);

        return nSpanCnt;
    }
    public void setnSpanCnt(int nSpanCnt) {
        this.nSpanCnt = nSpanCnt;
    }

    public AutofitRecyclerView(Context context) {
        super(context);
        init(context, null);
    }

    public AutofitRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AutofitRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            int[] attrsArray = {
                    android.R.attr.columnWidth
            };
            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
            columnWidth = array.getDimensionPixelSize(0, -1);
            array.recycle();
        }

//        manager = new GridLayoutManager(getContext(), 1);
        manager = new WrapContentGridLayoutManager(getContext(), 1);
        setLayoutManager(manager);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if (columnWidth > 0) {
            int spanCount = Math.max(1, getMeasuredWidth() / columnWidth);
            manager.setSpanCount(spanCount);
            setnSpanCnt(spanCount);
        }
    }

    public int getSpanCount(){
        Log.i("TAG","cnt: "+ manager.getSpanCount());
        return manager.getSpanCount();
    }

    public int getComputeHorizontalScrollRange(){
        return computeHorizontalScrollRange();
    }

    public int getHorizontalScrollExtent(){
        return computeHorizontalScrollExtent();
    }

    public int getComputeHorizontalScrollOffset(){
        return computeHorizontalScrollOffset();
    }

    public void setScrollX(int x){
        super.smoothScrollBy(x,0);
    }
    public int getHorizontalOffset(){
        return super.computeHorizontalScrollOffset();
    }

}
