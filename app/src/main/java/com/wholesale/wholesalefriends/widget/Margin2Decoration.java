package com.wholesale.wholesalefriends.widget;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.wholesale.wholesalefriends.R;

/**
 * Created by Handasoft_dev01 on 2016-10-26.
 */

public class Margin2Decoration extends RecyclerView.ItemDecoration {
    private int margin;
    private boolean isMargin;

    public Margin2Decoration(Context context, int item_margin) {
        margin = item_margin;
    }

    public Margin2Decoration(Context context) {
        margin = context.getResources().getDimensionPixelSize(R.dimen.item_margin_half2);
    }
    @Override
    public void getItemOffsets(
            Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(margin/2, 0, margin/2, 0);
    }
}