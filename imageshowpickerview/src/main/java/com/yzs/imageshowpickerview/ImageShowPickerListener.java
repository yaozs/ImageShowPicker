package com.yzs.imageshowpickerview;

import java.util.List;

/**
 * Author 姚智胜
 * Version V1.0版本
 * Description: picker点击事件监听
 * Date: 2017/4/15
 */

public interface ImageShowPickerListener {

    void addOnClickListener(int remainNum);

    void picOnClickListener(List<ImageShowPickerBean> list, int position, int remainNum);

    void delOnClickListener(int position, int remainNum);
}
