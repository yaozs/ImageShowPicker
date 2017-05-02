package com.yzs.imageshowpicker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.yzs.imageshowpickerview.ImageShowPickerBean;
import com.yzs.imageshowpickerview.ImageShowPickerListener;
import com.yzs.imageshowpickerview.ImageShowPickerView;

import java.util.List;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2017/4/17
 */

public class MyAdapter extends BaseAdapter {
    private List<List<ImageBean>> myLists;
    private Context context;

    public MyAdapter(List<List<ImageBean>> list, Context context) {
        this.myLists = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return myLists.size();
    }

    @Override
    public List<ImageBean> getItem(int position) {
        return myLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final MyViewHolder holder;
        if (convertView != null) {
            holder = (MyViewHolder) convertView.getTag();
        } else {
            //当convertView 为空时  声明控件
            holder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
            holder.pickerView = (ImageShowPickerView) convertView.findViewById(R.id.it_picker_view);
            convertView.setTag(holder);
        }
        final List<ImageBean> list = getItem(position);
        Log.e("list", "======" + list.size());
        holder.pickerView.setImageLoaderInterface(new Loader());
        holder.pickerView.setMaxNum(100);
        holder.pickerView.setNewData(list);
        //展示有动画和无动画
        if (position % 2 == 1) {
            holder.pickerView.setShowAnim(true);
        } else {
            holder.pickerView.setShowAnim(false);
        }
        holder.pickerView.setPickerListener(new ImageShowPickerListener() {
            @Override
            public void addOnClickListener(int remainNum) {
                Toast.makeText(context, "remainNum" + remainNum, Toast.LENGTH_SHORT).show();

                list.add(new ImageBean("http://pic78.huitu.com/res/20160604/1029007_20160604114552332126_1.jpg"));
                holder.pickerView.addData(new ImageBean("http://pic78.huitu.com/res/20160604/1029007_20160604114552332126_1.jpg"));
            }

            @Override
            public void picOnClickListener(List<ImageShowPickerBean> list, int position, int remainNum) {
                Toast.makeText(context, list.size() + "========" + position + "remainNum" + remainNum, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void delOnClickListener(int position, int remainNum) {
                list.remove(position);
                Toast.makeText(context, "delOnClickListenerremainNum" + remainNum, Toast.LENGTH_SHORT).show();
            }
        });
        holder.pickerView.show();

        return convertView;
    }

    class MyViewHolder {
        private ImageShowPickerView pickerView;
    }
}
