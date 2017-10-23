package com.kemp.projectwithselflibrary.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kemp.kemplibrary.adapter.PlusListAdapter;
import com.kemp.kemplibrary.utils.ViewHolder;
import com.kemp.projectwithselflibrary.R;

/**
 * Created by wangkp on 2017/10/21.
 */

public class GridPlusAdapter extends PlusListAdapter<String> {
    public GridPlusAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public View getNormalView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_img, parent, false);
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_img, null); 使用它gridview的item的高度不是预设高度
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String bitmapPath = getItem(position);
        holder.setContent(bitmapPath);

        return convertView;
//        String bitmapPath = getItem(position);
//        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, R.layout.item_img, position);
//
//        ImageView imageView = holder.getView(R.id.iv);
//
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setImageBitmap(creatBitmap(bitmapPath));
//
//        return holder.getConvertView();
    }

    private class ViewHolder {
        private ImageView imageView;

        ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.iv);
        }

        private void setContent(String path) {
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageBitmap(creatBitmap(path));
        }
    }

    private Bitmap creatBitmap(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        return BitmapFactory.decodeFile(path, options);
    }

    @Override
    public View getPlusView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_img, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageResource(R.drawable.item_plus);
        return view;
    }
}
