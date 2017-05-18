package com.kemp.kemplibrary.base;

/**
 *
 */

interface BaseUI {
    void showLoadingUI(boolean show);

    void showEmptyUI(boolean show);

    void showNetFailureUI(boolean show);

    /**
     * NetFailureUI可能有类似“重新加载”的按钮，
     * 此方法是响应“重新加载”按钮的点击事件
     */
    void onNetFailureReload();
}
