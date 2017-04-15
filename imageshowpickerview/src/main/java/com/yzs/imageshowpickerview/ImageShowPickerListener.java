package com.yzs.imageshowpickerview;

import java.util.List;

/**
 * Author 姚智胜
 * Version V1.0版本
 * Description:
 * Date: 2017/4/15
 */

public interface ImageShowPickerListener {

    void addOnClickListener();

    void picOnClickListener(List<ImageShowPickerBean> list, int position);
}
