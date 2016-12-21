package com.kemp.projectwithselflibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.kemp.kemplibrary.utils.ToolUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String deviceCode = ToolUtils.getUniqueCode(this);
        Log.d("www", deviceCode);
    }
}
