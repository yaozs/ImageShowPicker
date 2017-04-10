package com.yzs.imageshowpicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yzs.imageshowpickerview.ImageShowPickerView;

public class MainActivity extends AppCompatActivity {

    private ImageShowPickerView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (ImageShowPickerView) findViewById(R.id.image);
        view.setImageLoaderInterface(new Loader());

    }
}
