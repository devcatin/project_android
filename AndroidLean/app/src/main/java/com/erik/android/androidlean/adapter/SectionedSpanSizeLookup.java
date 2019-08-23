package com.erik.android.androidlean.adapter;

import android.support.v7.widget.GridLayoutManager;

public class SectionedSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    protected SectionedRecyclerViewAdapter<?, ?, ?> adapter = null;
    protected GridLayoutManager layoutManager = null;

    public SectionedSpanSizeLookup(SectionedRecyclerViewAdapter<?, ?, ?> adapter, GridLayoutManager layoutManager) {
        this.adapter = adapter;
        this.layoutManager = layoutManager;
    }

    @Override
    public int getSpanSize(int position) {
        if(adapter.isSectionHeaderPosition(position) ||
                adapter.isSectionFooterPosition(position)) {
            return layoutManager.getSpanCount();
        }
        return 1;
    }

}
