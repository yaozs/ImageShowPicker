package com.yzs.imageshowpickerview;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Author 姚智胜
 * Version V1.0版本
 * Description: 单纯的图片展示选择控件
 * Date: 2017/4/6
 */

public class ImageShowPickerView extends FrameLayout {

    private RecyclerView recyclerView;
    /**
     * 图片加载接口
     */
    private ImageLoaderInterface imageLoaderInterface;

    private Context context;


    public ImageShowPickerView(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ImageShowPickerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public ImageShowPickerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }


    private void init() {
        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));

    }


}
