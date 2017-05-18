package com.kemp.kemplibrary.base;

/**
 * 网络请求回调
 */

public interface NetCallBack {
    void onSuccess(String success);

    void onFailure(String error);
}
