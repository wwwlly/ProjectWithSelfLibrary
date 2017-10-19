package com.kemp.kemplibrary.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kemp.kemplibrary.R;

import java.util.List;

/**
 * 仿微信发布的动态图片展示
 * 1-4张图片在一个正方形区域显示，1张占满、2张左右平分、3张右边上下平分、4张以上前4张平分
 * Created by wangkp on 2017/10/18.
 */

public class CompoundImagesView extends ViewGroup {

    private Context mContext;
    private int width, height;
    private int space = 10;//imageview之间的间隙

    private int count = 0;

    public CompoundImagesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        space = getResources().getDimensionPixelOffset(R.dimen.size_compound_view_space);
        width = getResources().getDimensionPixelOffset(R.dimen.size_compound_view);
        height = width;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < count; i++) {
            int cl, ct, cr, cb;
            View child = getChildAt(i);
            int w = child.getLayoutParams().width;
            int h = child.getLayoutParams().height;
            cl = computeL(child, i, count);
            ct = getPaddingTop() + i / 2 * (h + space);
            cr = cl + w;
            cb = ct + h;
            child.layout(cl, ct, cr, cb);
        }
    }

    private int computeL(View child, int i, int count) {
        int w = child.getLayoutParams().width;
        int h = child.getLayoutParams().height;
        if (i == 0 || i == 2 && i != (count - 1)) {
            return getPaddingLeft();
        } else {
            return getPaddingLeft() + w + space;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        }
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    public void setImages(List<Integer> images) {
        if (images == null || images.size() == 0) {
            setDefaultStatus();
        } else if (images.size() != count) {
            count = images.size();
            if (getChildCount() != 0) {
                removeAllViews();
            }
            addChildViews(count);
            setImageRes(images);
        } else {
            setImageRes(images);
        }
    }

    private void setImageRes(List<Integer> images) {
        for (int i = 0; i < getChildCount(); i++) {
            ImageView imageView = (ImageView) getChildAt(i);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(images.get(i));
        }
    }

    private void setImageBitmaps(List<Bitmap> images) {

    }

    private void setImageUrls(List<String> images) {

    }

    private void setDefaultStatus() {

    }

    private void addChildViews(int count) {
        switch (count) {
            case 1:
                addView(createImageView1());
                break;
            case 2:
                addView(createImageView2());
                addView(createImageView2());
                break;
            case 3:
                addView(createImageView2());
                addView(createImageView4());
                addView(createImageView4());
                break;
            case 4:
                addView(createImageView4());
                addView(createImageView4());
                addView(createImageView4());
                addView(createImageView4());
                break;
        }
    }

    private ImageView createImageView() {
        return new ImageView(mContext);
    }

    private ImageView createImageView1() {
        ImageView imageView = createImageView();
        int w = width - getPaddingLeft() - getPaddingRight();
        int h = height - getPaddingTop() - getPaddingBottom();
        LayoutParams lp = new LayoutParams(w, h);
        imageView.setLayoutParams(lp);
        return imageView;
    }

    private ImageView createImageView2() {
        ImageView imageView = createImageView();
        int w = width - getPaddingLeft() - getPaddingRight() - space;
        int h = height - getPaddingTop() - getPaddingBottom();
        LayoutParams lp = new LayoutParams(w / 2, h);
        imageView.setLayoutParams(lp);
        return imageView;
    }

    private ImageView createImageView4() {
        ImageView imageView = createImageView();
        int w = width - getPaddingLeft() - getPaddingRight() - space;
        int h = height - getPaddingTop() - getPaddingBottom() - space;
        LayoutParams lp = new LayoutParams(w / 2, h / 2);
        imageView.setLayoutParams(lp);
        return imageView;
    }


}
