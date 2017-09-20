package com.kemp.projectwithselflibrary;

import android.app.Activity;
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
import com.kemp.projectwithselflibrary.activity.ScanCodeActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> mData = new ArrayList<>();
    private List<ItemBean> listData;

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
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, ScanCodeActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, IamgeActivity.class));
                        break;
                }
            }
        });
    }

    private void initData() {
        listData = initItemList();
        for (ItemBean itemBean : listData) {
            mData.add(itemBean.title);
        }
    }

    private List<ItemBean> initItemList() {
        List<ItemBean> list = new ArrayList<>();
        list.add(new ItemBean("扫描二维码（横屏）", CaptureActivity.class));
        list.add(new ItemBean("图片", IamgeActivity.class));
        return list;
    }

    private class ItemBean {
        private String title;
        private Class<? extends Activity> activity;

        public ItemBean(String title, Class<? extends Activity> activity) {
            this.title = title;
            this.activity = activity;
        }
    }
}
