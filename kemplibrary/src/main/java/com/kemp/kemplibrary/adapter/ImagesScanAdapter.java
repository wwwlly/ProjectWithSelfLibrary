package com.kemp.kemplibrary.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangkp on 2017/5/31.
 */

public class ImagesScanAdapter extends PagerAdapter {

    private List<View> views;

    private Context mContext;
    private List<String> mData;

    public ImagesScanAdapter(Context context, List<String> data){
        views = new ArrayList<>();
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.isEmpty() ? 0 : mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = views.get(position);
        if(view == null){
            view = getItem(position);
            container.addView(view);
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = views.get(position);
        container.removeView(view);
        views.remove(position);
    }

    private View getItem(int position){
        ImageView imageView = new ImageView(mContext);

        imageView.setImageBitmap(creatBitmap(mData.get(position)));
        return imageView;
    }

    private Bitmap creatBitmap(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        return BitmapFactory.decodeFile(path, options);
    }
}
