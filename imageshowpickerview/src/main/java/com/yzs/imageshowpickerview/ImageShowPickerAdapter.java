package com.yzs.imageshowpickerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.List;

/**
 * Author 姚智胜
 * Version V1.0版本
 * Description:
 * Date: 2017/4/10
 */

public class ImageShowPickerAdapter extends RecyclerView.Adapter<ImageShowPickerAdapter.ViewHolder> implements PicOnClickListener {

    private int mMaxNum;
    private Context context;
    private List<ImageShowPickerBean> list;
    public ImageLoaderInterface imageLoaderInterface;
    private ImageShowPickerListener pickerListener;

    public void setPickerListener(ImageShowPickerListener pickerListener) {
        this.pickerListener = pickerListener;
    }

    private String str_url;
    private int delId;

    public String getUrl() {
        return str_url;
    }

    public void setUrl(String url) {
        this.str_url = url;
    }

    public int getDelId() {
        return delId;
    }

    public void setDelId(int delId) {
        this.delId = delId;
    }

    public ImageShowPickerAdapter(int mMaxNum, Context context, List<ImageShowPickerBean> list, ImageLoaderInterface imageLoaderInterface) {
        this.mMaxNum = mMaxNum;
        this.context = context;
        this.list = list;
        this.imageLoaderInterface = imageLoaderInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FrameLayout frameLayout = new FrameLayout(context);
        parent.addView(frameLayout);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        ViewHolder vh = new ViewHolder(frameLayout, imageLoaderInterface, this);

        frameLayout.addView(vh.iv_pic);
        frameLayout.addView(vh.iv_del);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (list.size() == 0 || list.size() == position) {
            Log.e("list.size() == 0 ", "========" + position);
            holder.iv_pic.setBackgroundResource(R.mipmap.image_show_piceker_add);
        } else {
            Log.e("list.size() != 0 ", "========" + position);
            if (null == list.get(position).getImageShowPickerUrl() || "".equals(list.get(position).getImageShowPickerUrl())) {
                imageLoaderInterface.displayImage(context, list.get(position).getImageShowPickerDelRes(), holder.iv_pic);
            } else {
                imageLoaderInterface.displayImage(context, list.get(position).getImageShowPickerUrl(), holder.iv_pic);
            }
            holder.iv_del.setImageResource(R.mipmap.image_show_piceker_del);
        }


    }

    @Override
    public int getItemCount() {

        return list.size() < mMaxNum ? list.size() + 1 : list.size();

    }

    @Override
    public void onDelClickListener(int position) {

    }

    @Override
    public void onPicClickListener(int position) {
        if (position == list.size()) {
            Log.e("onPicClickListener","aaaaaaaaa");
            if (pickerListener != null) {
                pickerListener.addOnClickListener();
                Log.e("onPicClickListener","bbbbbbbbb");
            }
        } else {
            Log.e("onPicClickListener","cccccccccccc");
            if (pickerListener != null) {
                Log.e("onPicClickListener","ddddddddddddddd");
                pickerListener.picOnClickListener(list, position);
            }
        }
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public View iv_pic;
        public ImageView iv_del;
        private PicOnClickListener picOnClickListener;


        public ViewHolder(View view, ImageLoaderInterface imageLoaderInterface, PicOnClickListener picOnClickListener) {
            super(view);
            this.picOnClickListener = picOnClickListener;
            iv_pic = imageLoaderInterface.createImageView(view.getContext());
            FrameLayout.LayoutParams pic_params = new FrameLayout.LayoutParams(ImageShowPickerView.dp2px(view.getContext(), 80), ImageShowPickerView.dp2px(view.getContext(), 80));
            pic_params.setMargins(10, 10, 10, 10);
            iv_pic.setLayoutParams(pic_params);
            iv_del = new ImageView(view.getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.TOP | Gravity.END;
            iv_del.setPadding(5, 5, 5, 5);
            iv_del.setLayoutParams(layoutParams);
            iv_pic.setId(R.id.iv_image_show_picker_pic);
            iv_del.setId(R.id.iv_image_show_picker_del);
            iv_pic.setOnClickListener(this);
            iv_del.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.e("Adapter","1111111111");
            int i = v.getId();
            if (i == R.id.iv_image_show_picker_pic) {
                Log.e("Adapter","iv_image_show_picker_pic"+getLayoutPosition());
                picOnClickListener.onPicClickListener(getLayoutPosition());
            } else if (i == R.id.iv_image_show_picker_del) {
                picOnClickListener.onDelClickListener(getLayoutPosition());
                Log.e("Adapter","iv_image_show_picker_del"+getLayoutPosition());
            }
        }
    }
}
