package com.yzs.imageshowpickerview;

/**
 * Author 姚智胜
 * Version V1.0版本
 * Description: 显示数据类的父类，必须继承于该类
 * Date: 2017/4/10
 */

public abstract class ImageShowPickerBean {

    public String getImageShowPickerUrl() {
        return setImageShowPickerUrl();
    }

    public int getImageShowPickerDelRes() {
        return setImageShowPickerDelRes();
    }

    /**
     * 为URL赋值，必须重写方法
     *
     * @return
     */
    public abstract String setImageShowPickerUrl();

    /**
     * 为删除label赋值，必须重写方法
     *
     * @return
     */
    public abstract int setImageShowPickerDelRes();


}
