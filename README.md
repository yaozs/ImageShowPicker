# ImageShowPicker
单纯的上传图片展示控件

![image](https://github.com/yaozs/YzsLib/blob/master/app/src/main/res/mipmap-xxxhdpi/icon.png)

## 欢迎大家Star，使用

 YzsLib开源交流群：331973212

        朋友是否一次又一次的写着放朋友圈的选择发布框架就是这个
![image](https://github.com/yaozs/ImageShowPicker/blob/master/pic/weixin_pic.jpg)

## 现在支持的框架来了

![image](https://github.com/yaozs/ImageShowPicker/blob/master/pic/imageshowpickerview.gif)


## Attributes属性（ImageShowPicker布局文件中调用）
|Attributes|forma|describe
|---|---|---|
|pic_size| dimension|单张图片大小（宽高一样）
|max_num| integer|最大数量
|add_label| reference|添加图片
|del_label| reference|删除图片
|is_show_del| boolean|是否显示删除按钮
|is_show_anim| boolean|是否展示动画
|one_line_show_num| integer|单行显示数量

## 使用步骤

#### Step 1.依赖
Gradle

先在 build.gradle(Project:XXXX) 的 repositories 添加:
```groovy
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```
然后在 build.gradle(Module:app) 的 dependencies 添加:

```groovy
dependencies{
   compile 'com.github.yaozs:ImageShowPicker:1.0.0' //最新版本
}
```
```java
        ImageShowPickerView  pickerView = (ImageShowPickerView)findViewById(R.id.it_picker_view);
        final List<ImageBean> list = getItem(position);
        pickerView.setImageLoaderInterface(new Loader());
        pickerView.setNewData(list);
        //展示有动画和无动画
        if (position % 2 == 1) {
           pickerView.setShowAnim(true);
        } else {
           pickerView.setShowAnim(false);
        }
        pickerView.setPickerListener(new ImageShowPickerListener() {
            @Override
            public void addOnClickListener(int remainNum) {
                Toast.makeText(context, "remainNum" + remainNum, Toast.LENGTH_SHORT).show();
                //在listview或recyclerview才会使用这个list.add(),其他情况都不用
                list.add(new ImageBean("http://pic78.huitu.com/res/20160604/1029007_20160604114552332126_1.jpg"));
                pickerView.addData(new ImageBean("http://pic78.huitu.com/res/20160604/1029007_20160604114552332126_1.jpg"));
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
        pickerView.show();


        //获取所有数据
        pickerView.getDataList();
```
        

## 本人的其他项目
[YzsLib——超好用的开发框架](https://github.com/yaozs/YzsLib)


[YzsBaseActivity——简化到一定境界的BaseActivity](https://github.com/yaozs/YzsBaseActivity)



        
