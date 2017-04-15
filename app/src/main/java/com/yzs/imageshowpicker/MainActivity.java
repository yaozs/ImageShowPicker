package com.yzs.imageshowpicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.yzs.imageshowpickerview.ImageShowPickerBean;
import com.yzs.imageshowpickerview.ImageShowPickerListener;
import com.yzs.imageshowpickerview.ImageShowPickerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageShowPickerView view;
    List<ImageBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (ImageShowPickerView) findViewById(R.id.imageshowpicker);
        view.setImageLoaderInterface(new Loader());
        list.add(new ImageBean("http://img2.imgtn.bdimg.com/it/u=819201812,3553302270&fm=214&gp=0.jpg"));
        list.add(new ImageBean("http://img02.tooopen.com/images/20140504/sy_60294738471.jpg"));
        list.add(new ImageBean("http://pic.58pic.com/58pic/16/62/63/97m58PICyWM_1024.jpg"));
        list.add(new ImageBean("http://pic78.huitu.com/res/20160604/1029007_20160604114552332126_1.jpg"));
        list.add(new ImageBean("http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg"));
        view.addData(list);
        view.show();
        view.setPickerListener(new ImageShowPickerListener() {
            @Override
            public void addOnClickListener() {
                Log.e("MainActivity", "addOnClickListener");
                view.addData(new ImageBean("http://pic78.huitu.com/res/20160604/1029007_20160604114552332126_1.jpg"));
            }

            @Override
            public void picOnClickListener(List<ImageShowPickerBean> list, int position) {
                Log.e("MainActivity", "picOnClickListener");
                Toast.makeText(MainActivity.this, list.size() + "========" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
