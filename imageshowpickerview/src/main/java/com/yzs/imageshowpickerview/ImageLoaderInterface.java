package com.yzs.imageshowpickerview;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.View;

import java.io.Serializable;

/**
 * Author 姚智胜
 * Version V1.0版本
 * Description: 加载图片接口
 * Date: 2017/4/6
 */
public interface ImageLoaderInterface<T extends View> extends Serializable {

    void displayImage(Context context, String path, T imageView);

    void displayImage(Context context, @DrawableRes Integer resId, T imageView);

    T createImageView(Context context);
}
