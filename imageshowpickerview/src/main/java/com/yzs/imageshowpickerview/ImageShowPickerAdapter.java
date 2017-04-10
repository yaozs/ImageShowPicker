package com.yzs.imageshowpickerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
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

public class ImageShowPickerAdapter<T extends ImageShowPickerBean> extends RecyclerView.Adapter<ImageShowPickerAdapter.ViewHolder> {

    private int mMaxNum;
    private Context context;
    private List<T> list;
    public ImageLoaderInterface imageLoaderInterface;


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

    public ImageShowPickerAdapter(int mMaxNum, Context context, List<T> list, ImageLoaderInterface imageLoaderInterface) {
        this.mMaxNum = mMaxNum;
        this.context = context;
        this.list = list;
        this.imageLoaderInterface = imageLoaderInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FrameLayout frameLayout = new FrameLayout(context);
        parent.addView(frameLayout);
//        View view =
//                LayoutInflater.from(context);//当然还有另外两种方法。
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
//                LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        ViewHolder vh = new ViewHolder(frameLayout, imageLoaderInterface);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (null != list.get(position)) {
            imageLoaderInterface.displayImage(context, list.get(position).getImageShowPickerUrl(), holder.iv_pic);
        }


    }

    @Override
    public int getItemCount() {

        return list.size() < mMaxNum ? list.size() + 1 : list.size();

    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View iv_pic;
        public ImageView ic_del;


        public ViewHolder(View view, ImageLoaderInterface imageLoaderInterface) {
            super(view);
            iv_pic = imageLoaderInterface.createImageView(view.getContext());
            FrameLayout.LayoutParams pic_params = (FrameLayout.LayoutParams) ic_del.getLayoutParams();
            pic_params.height = ImageShowPickerView.dp2px(view.getContext(), 100);
            pic_params.width = ImageShowPickerView.dp2px(view.getContext(), 100);
            iv_pic.setPadding(10, 10, 10, 10);
            iv_pic.setLayoutParams(pic_params);
            iv_pic.setBackgroundResource(R.drawable.image_show_piceker_add);
            ic_del = new ImageView(view.getContext());
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) ic_del.getLayoutParams();
            layoutParams.gravity = Gravity.TOP | Gravity.END;
            ic_del.setLayoutParams(layoutParams);
        }
    }
}
