package com.kemp.kemplibrary.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by wangkp on 2017/5/27.
 */

public class CommonUtils {

    //进入手机相册
    public static void intentToAlbum(Activity activity, int requestCode) {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("image/*");
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, requestCode);
    }
}
