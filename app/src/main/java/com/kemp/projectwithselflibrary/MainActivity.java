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

import com.kemp.kemplibrary.utils.ToolUtils;
import com.kemp.projectwithselflibrary.activity.AddImgActivity;
import com.kemp.projectwithselflibrary.activity.IamgeActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ItemBean> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String deviceCode = ToolUtils.getUniqueCode(this);
        Log.d("www", deviceCode);

        initData();
        ListView listView = (ListView) findViewById(R.id.list_view);
        ArrayAdapter<ItemBean> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemBean itemBean = (ItemBean) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, itemBean.aClass);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        mData = initItemList();
    }

    private List<ItemBean> initItemList() {
        List<ItemBean> list = new ArrayList<>();
        list.add(new ItemBean("图片", IamgeActivity.class));
        list.add(new ItemBean("添加图片", AddImgActivity.class));
        return list;
    }

    private class ItemBean {
        private String title;
        private Class<? extends Activity> aClass;

        public ItemBean(String title, Class<? extends Activity> activity) {
            this.title = title;
            this.aClass = activity;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
