package com.kemp.kemplibrary.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangkp on 2017/10/21.
 */

public abstract class PlusListAdapter<E> extends BaseListAdapter<E> {

    private int maxLength = 0;

    public PlusListAdapter(Context mContext) {
        super(mContext);
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    private boolean isMaxLength() {
        return getItems().size() >= maxLength;
    }

    @Override
    public int getCount() {
        if (!isMaxLength()) {
            return getItems().size() + 1;
        } else {
            return maxLength;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return isPlusPos(position) ? 0 : 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        return type == 0 ? getPlusView() : getNormalView(position, convertView, parent);
    }

    public abstract View getNormalView(int position, View convertView, ViewGroup parent);

    public abstract View getPlusView();

    public boolean isPlusPos(int position) {
        return getItems().size() < maxLength && position >= getCount() - 1;
    }
}
