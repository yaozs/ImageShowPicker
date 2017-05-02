package com.yzs.imageshowpicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<List<ImageBean>> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listview);
        MyAdapter adapter = new MyAdapter(mList, this);
        listView.setAdapter(adapter);
        for (int i = 0; i < 10; i++) {
            List<ImageBean> list = new ArrayList<>();
            for (int j = 0; j < 10; j++) {

                list.add(new ImageBean("http://img02.tooopen.com/images/20140504/sy_60294738471.jpg"));
                list.add(new ImageBean("http://pic.58pic.com/58pic/16/62/63/97m58PICyWM_1024.jpg"));
                list.add(new ImageBean("http://pic78.huitu.com/res/20160604/1029007_20160604114552332126_1.jpg"));
                list.add(new ImageBean("http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg"));
            }
            mList.add(list);
        }
        adapter.notifyDataSetChanged();

    }
}
