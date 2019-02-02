package com.kemp.kemplibrary.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.kemp.kemplibrary.utils.Logger;

/**
 * Created by wangkp on 2017/7/21.
 */

public class ResizeImageView extends AppCompatImageView {

    private int width, height;

    public ResizeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Logger.d("====" + widthSize + "," + heightSize + "," + getWidth());
        width = widthSize;
        height = heightSize;
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        Bitmap temp = ThumbnailUtils.extractThumbnail(bm, width, height);
        super.setImageBitmap(temp);
    }
}
