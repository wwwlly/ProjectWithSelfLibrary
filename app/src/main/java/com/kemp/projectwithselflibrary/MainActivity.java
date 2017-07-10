package com.kemp.projectwithselflibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.zxing.client.android.CaptureActivity;
import com.kemp.kemplibrary.utils.ToolUtils;
import com.kemp.projectwithselflibrary.activity.IamgeActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String deviceCode = ToolUtils.getUniqueCode(this);
        Log.d("www", deviceCode);

        initData();
        ListView listView = (ListView) findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(MainActivity.this, CaptureActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, IamgeActivity.class));
                        break;
                }
            }
        });
    }

    private void initData() {
        mData.add("扫描二维码");
        mData.add("图片");
    }
}
