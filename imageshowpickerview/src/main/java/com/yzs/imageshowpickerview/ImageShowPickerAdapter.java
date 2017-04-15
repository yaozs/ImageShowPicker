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
        frameLayout.addView(vh.iv_pic);
        frameLayout.addView(vh.ic_del);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (list.size() == 0 || list.size() == position) {
            Log.e("list.size() == 0 ", "========" + position);
            holder.iv_pic.setBackgroundResource(R.drawable.image_show_piceker_add);
        } else {
            Log.e("list.size() != 0 ", "========" + position);
            imageLoaderInterface.displayImage(context, list.get(position).getImageShowPickerUrl(), holder.iv_pic);
            holder.ic_del.setImageResource(R.mipmap.image_show_piceker_del);
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
            FrameLayout.LayoutParams pic_params = new FrameLayout.LayoutParams(ImageShowPickerView.dp2px(view.getContext(), 100), ImageShowPickerView.dp2px(view.getContext(), 100));
//            FrameLayout.LayoutParams pic_params = new FrameLayout.LayoutParams(100, 100);
//            ic_del.getLayoutParams();
//            pic_params.height = ImageShowPickerView.dp2px(view.getContext(), 100);
//            pic_params.width = ImageShowPickerView.dp2px(view.getContext(), 100);

            iv_pic.setPadding(10, 10, 10, 10);
            iv_pic.setLayoutParams(pic_params);
            ic_del = new ImageView(view.getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.TOP | Gravity.END;
            ic_del.setLayoutParams(layoutParams);
        }
    }
}
