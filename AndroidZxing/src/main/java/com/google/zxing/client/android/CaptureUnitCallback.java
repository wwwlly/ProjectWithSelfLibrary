package com.google.zxing.client.android;

/**
 * Created by wangkp on 2017/9/18.
 */

public interface CaptureUnitCallback {

    void handleDecode(String result);

    void resetStatusView();
}
