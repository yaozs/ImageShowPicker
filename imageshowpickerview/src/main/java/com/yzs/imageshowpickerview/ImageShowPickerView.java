package com.yzs.imageshowpickerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

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

    public void setImageLoaderInterface(ImageLoaderInterface imageLoaderInterface) {
        this.imageLoaderInterface = imageLoaderInterface;
        adapter = new ImageShowPickerAdapter(9, context, list, imageLoaderInterface);
        recyclerView.setAdapter(adapter);
    }


    private ImageShowPickerAdapter adapter;

    private List<ImageShowPickerBean> list;

    private int picSize = 80;

    private int mPicSize;

    private int mAddLabel;

    private int mDelLabel;

    private boolean isShowDel;

    public ImageShowPickerView(@NonNull Context context) {
        super(context);
    }

    public ImageShowPickerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(getContext(), attrs);
    }

    public ImageShowPickerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(getContext(), attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        list = new ArrayList<>();
        viewTypedArray(context, attrs);
        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        addView(recyclerView);
    }

    private void viewTypedArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageShowPickerView);
        mPicSize = typedArray.getDimensionPixelSize(R.styleable.ImageShowPickerView_pic_size, dp2px(getContext(), picSize));
        isShowDel = typedArray.getBoolean(R.styleable.ImageShowPickerView_is_show_del, false);
        mAddLabel = typedArray.getResourceId(R.styleable.ImageShowPickerView_add_label, R.drawable.image_show_piceker_add);
        mDelLabel = typedArray.getResourceId(R.styleable.ImageShowPickerView_del_label, R.drawable.image_show_piceker_del);
        typedArray.recycle();
    }


    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
