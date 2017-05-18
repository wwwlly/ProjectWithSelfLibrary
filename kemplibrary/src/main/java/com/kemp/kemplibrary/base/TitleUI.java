package com.kemp.kemplibrary.base;

import android.view.View;

/**
 * Created by wangkp on 2017/1/16.
 */

public interface TitleUI {
    void setTitleName(String titleName);

    void setTitleName(int titleId);

    String getTitleName();

    void setTitleLength(int length);

    void setLeftButton(int leftButton);

    void setRightButton(int rightButton);

    void onClickLeft(View view);

    void onClickRight(View view);

    void setTitleVisible(boolean visible);

    void setLeftButtonVisible(boolean visible);

    void setRightButtonVisible(boolean visible);
}
