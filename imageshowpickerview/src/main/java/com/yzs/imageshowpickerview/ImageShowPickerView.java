package com.yzs.imageshowpickerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    private ImageShowPickerListener pickerListener;

    private Context context;


    private ImageShowPickerAdapter adapter;

    private List<ImageShowPickerBean> list;
    /**
     * 默认单个大小
     */
    private static final int PIC_SIZE = 80;
    /**
     * 默认单行显示数量
     */
    private static final int ONE_LINE_SHOW_NUM = 4;
    /**
     * 默认单个大小
     */
    private static final int MAX_NUM = 9;
    /**
     * 单个item大小
     */
    private int mPicSize;
    /**
     * 添加图片
     */
    private int mAddLabel;
    /**
     * 删除图片
     */
    private int mDelLabel;
    /**
     * 是否显示删除
     */
    private boolean isShowDel;
    /**
     * 单行显示数量，默认4
     */
    private int oneLineShowNum;
    /**
     * 是否展示动画，默认false
     */
    private boolean isShowAnim;

    /**
     * 最大数量
     */
    private int maxNum;

    /**
     * 设置单个item大小
     *
     * @param mPicSize
     */
    public void setmPicSize(int mPicSize) {
        this.mPicSize = mPicSize;
    }

    /**
     * 设置增加图片
     *
     * @param mAddLabel
     */
    public void setmAddLabel(int mAddLabel) {
        this.mAddLabel = mAddLabel;
    }

    /**
     * 设置删除图片
     *
     * @param mDelLabel
     */
    public void setmDelLabel(int mDelLabel) {
        this.mDelLabel = mDelLabel;
    }

    /**
     * 设置是否显示删除
     *
     * @param showDel
     */
    public void setShowDel(boolean showDel) {
        isShowDel = showDel;
    }

    /**
     * 设置单行显示数量
     *
     * @param oneLineShowNum
     */
    public void setOneLineShowNum(int oneLineShowNum) {
        this.oneLineShowNum = oneLineShowNum;
    }

    /**
     * 设置是否显示动画
     *
     * @param showAnim
     */
    public void setShowAnim(boolean showAnim) {
        isShowAnim = showAnim;
    }

    /**
     * 设置最大允许图片数量
     *
     * @param maxNum
     */
    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public ImageShowPickerView(@NonNull Context context) {
        this(context, null);
    }

    public ImageShowPickerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
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
        addView(recyclerView);
    }

    private void viewTypedArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageShowPickerView);
        mPicSize = typedArray.getDimensionPixelSize(R.styleable.ImageShowPickerView_pic_size, SizeUtils.getSizeUtils().dp2px(getContext(), PIC_SIZE));
        isShowDel = typedArray.getBoolean(R.styleable.ImageShowPickerView_is_show_del, true);
        isShowAnim = typedArray.getBoolean(R.styleable.ImageShowPickerView_is_show_anim, false);
        mAddLabel = typedArray.getResourceId(R.styleable.ImageShowPickerView_add_label, R.mipmap.image_show_piceker_add);
        mDelLabel = typedArray.getResourceId(R.styleable.ImageShowPickerView_del_label, R.mipmap.image_show_piceker_del);
        oneLineShowNum = typedArray.getInt(R.styleable.ImageShowPickerView_one_line_show_num, ONE_LINE_SHOW_NUM);
        maxNum = typedArray.getInt(R.styleable.ImageShowPickerView_max_num, MAX_NUM);
        typedArray.recycle();
    }

    /**
     * 最后调用方法显示，必须最后调用
     */
    public void show() {
        MyGridLayoutManager layoutManager = new MyGridLayoutManager(context, oneLineShowNum);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ImageShowPickerAdapter(maxNum, context, list, imageLoaderInterface, pickerListener);
        adapter.setAddPicRes(mAddLabel);
        adapter.setDelPicRes(mDelLabel);
        adapter.setIconHeight(mPicSize);
        adapter.setShowDel(isShowDel);
        adapter.setShowAnim(isShowAnim);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 设置选择器监听
     *
     * @param pickerListener 选择器监听事件
     */
    public void setPickerListener(ImageShowPickerListener pickerListener) {
        this.pickerListener = pickerListener;
    }

    /**
     * 图片加载器
     *
     * @param imageLoaderInterface
     */
    public void setImageLoaderInterface(ImageLoaderInterface imageLoaderInterface) {
        this.imageLoaderInterface = imageLoaderInterface;

    }

    /**
     * 添加新数据
     *
     * @param bean
     * @param <T>
     */
    public <T extends ImageShowPickerBean> void addData(T bean) {
        if (bean == null) {
            return;
        }
        this.list.add(bean);
        if (isShowAnim) {
            if (adapter != null) {
                adapter.notifyItemChanged(list.size() - 1);
                adapter.notifyItemChanged(list.size());
            }
        } else {
            adapter.notifyDataSetChanged();
        }

    }

    /**
     * 添加新数据
     *
     * @param list
     * @param <T>
     */
    public <T extends ImageShowPickerBean> void addData(List<T> list) {
        if (list == null) {
            return;
        }
        this.list.addAll(list);

        if (isShowAnim) {
            if (adapter != null)
                adapter.notifyItemRangeChanged(this.list.size() - list.size(), list.size());

        } else {
            if (adapter != null)
                adapter.notifyDataSetChanged();
        }

    }

    /**
     * 首次添加数据
     *
     * @param list
     * @param <T>
     */
    public <T extends ImageShowPickerBean> void setNewData(List<T> list) {
        this.list = new ArrayList<>();
        this.list.addAll(list);

        if (isShowAnim) {
            if (adapter != null)
                adapter.notifyItemRangeChanged(this.list.size() - list.size(), list.size());

        } else {
            if (adapter != null)
                adapter.notifyDataSetChanged();
        }

    }

    /**
     * 获取picker的list数据集合
     *
     * @param <T>
     * @return
     */
    public <T extends ImageShowPickerBean> List<T> getDataList() {
        return (List<T>) list;
    }

}
