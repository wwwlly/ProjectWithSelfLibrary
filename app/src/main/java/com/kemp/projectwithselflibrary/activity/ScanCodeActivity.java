package com.kemp.projectwithselflibrary.activity;

import android.os.Bundle;
import android.view.SurfaceView;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.ViewfinderView;
import com.kemp.projectwithselflibrary.R;
import com.kemp.projectwithselflibrary.utils.Logger;

/**
 * Created by wangkp on 2017/9/18.
 */

public class ScanCodeActivity extends CaptureActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code);

        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        ViewfinderView viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        setMainView(surfaceView, viewfinderView);

        setOrientationPortrait(true);
    }

    @Override
    public void handleDecodeText(String result) {
        Logger.d("==========" + result);
        finish();
    }
}
