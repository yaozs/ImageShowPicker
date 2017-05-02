package com.yzs.imageshowpicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.yzs.imageshowpickerview.ImageShowPickerBean;
import com.yzs.imageshowpickerview.ImageShowPickerListener;
import com.yzs.imageshowpickerview.ImageShowPickerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2017/4/26
 */

public class OnePickerActivity extends AppCompatActivity {

    List<ImageBean> list;
    ImageShowPickerView pickerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
        pickerView = (ImageShowPickerView) findViewById(R.id.it_picker_view);
        list = new ArrayList<>();
        for (int j = 0; j < 10; j++) {

            list.add(new ImageBean("http://img2.imgtn.bdimg.com/it/u=819201812,3553302270&fm=214&gp=0.jpg"));
            list.add(new ImageBean("http://img02.tooopen.com/images/20140504/sy_60294738471.jpg"));
            list.add(new ImageBean("http://pic.58pic.com/58pic/16/62/63/97m58PICyWM_1024.jpg"));
            list.add(new ImageBean("http://pic78.huitu.com/res/20160604/1029007_20160604114552332126_1.jpg"));
            list.add(new ImageBean("http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg"));
        }

        Log.e("list", "======" + list.size());
        pickerView.setImageLoaderInterface(new Loader());
        pickerView.setNewData(list);
        //展示有动画和无动画

        pickerView.setShowAnim(true);

        pickerView.setPickerListener(new ImageShowPickerListener() {
            @Override
            public void addOnClickListener(int remainNum) {
                Toast.makeText(OnePickerActivity.this, "remainNum" + remainNum, Toast.LENGTH_SHORT).show();

                list.add(new ImageBean("http://pic78.huitu.com/res/20160604/1029007_20160604114552332126_1.jpg"));
                pickerView.addData(new ImageBean("http://pic78.huitu.com/res/20160604/1029007_20160604114552332126_1.jpg"));
            }

            @Override
            public void picOnClickListener(List<ImageShowPickerBean> list, int position, int remainNum) {
                Toast.makeText(OnePickerActivity.this, list.size() + "========" + position + "remainNum" + remainNum, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void delOnClickListener(int position, int remainNum) {
                list.remove(position);
                Toast.makeText(OnePickerActivity.this, "delOnClickListenerremainNum" + remainNum, Toast.LENGTH_SHORT).show();
            }
        });
        pickerView.show();
    }
}
