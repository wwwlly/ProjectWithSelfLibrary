package com.kemp.projectwithselflibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.zxing.client.android.CaptureActivity;
import com.kemp.kemplibrary.utils.ToolUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String deviceCode = ToolUtils.getUniqueCode(this);
        Log.d("www", deviceCode);

        Button btnQcode = (Button) findViewById(R.id.btn_qcode);
        btnQcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CaptureActivity.class));
            }
        });
    }
}
