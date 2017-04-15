package com.yzs.imageshowpicker;

import com.yzs.imageshowpickerview.ImageShowPickerBean;

/**
 * Author 姚智胜
 * Version V1.0版本
 * Description:
 * Date: 2017/4/10
 */

public class ImageBean extends ImageShowPickerBean {
    public ImageBean(String url) {
        this.url = url;
    }

    public ImageBean(int resId) {
        this.resId = resId;
    }

    private String url;

    private int resId;


    @Override
    public String setImageShowPickerUrl() {
        return url;
    }

    @Override
    public int setImageShowPickerDelRes() {
        return resId;
    }
}
