package com.kemp.kemplibrary.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import com.kemp.kemplibrary.utils.ToolUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangkp on 2017/2/27.
 */

public abstract class BaseListAdapter<E> extends BaseAdapter {

    protected Context mContext;
    private List<E> items = new ArrayList<>();

    public BaseListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return null == items ? 0 : items.size();
    }

    @Override
    public E getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setData(List<E> data) {
        this.items = data;
        notifyDataSetChanged();
    }

    public void addAll(List<E> data) {
        if (null == data)
            return;
        this.items.addAll(data);
        notifyDataSetChanged();
    }

    public void add(E data) {
        if (null == data)
            return;
        items.add(data);
        notifyDataSetChanged();
    }

    public void add(int index, E data) {
        if (null == data)
            return;
        items.add(index, data);
        notifyDataSetChanged();
    }

    public void clear() {
        if (!ToolUtils.isEmpty(items))
            items.clear();
        notifyDataSetChanged();
    }

    public void remove(E data) {
        if (null == data || ToolUtils.isEmpty(items))
            return;
        items.remove(data);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (ToolUtils.isEmpty(items) || position < 0 || position >= items.size())
            return;
        items.remove(position);
        notifyDataSetChanged();
    }

    public List<E> getItems() {
        return items;
    }


}
