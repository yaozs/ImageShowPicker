package com.yzs.imageshowpicker;

import android.content.Context;
import android.widget.ImageView;

import com.yzs.imageshowpickerview.ImageLoader;

/**
 * Author 姚智胜
 * Version V1.0版本
 * Description:
 * Date: 2017/4/10
 */

public class Loader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        imageView.setImageResource((Integer) path);
    }


}
