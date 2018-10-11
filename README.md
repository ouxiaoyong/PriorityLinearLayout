# PriorityLinearLayout
这是一个可以自定义子View measure顺序的LinearLayout框架
# 应用场景
如下图中间的视图是个listView，listView的下面跟着一个添加删除按钮，若listView铺不满屏幕，则添加删除按钮紧跟其后，若超出屏幕，则添加删除按钮在最底部。
如果用PriorityLinearLayout则可以轻松实现。只需在子视图增加一个属性measure_priority（大于0的int值）即可。
 ![image](https://github.com/ouxiaoyong/PriorityLinearLayout/blob/master/images/image1.png)
# 用法
project/build.gradle  

allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
	}
  
  app/build.gradle  
  dependencies {
	        implementation 'com.github.ouxiaoyong:PriorityLinearLayout:Tag'
	}
  
  将原有的LinearLayout标签替换成com.oxy.library.PriorityLinearLayout标签
<?xml version="1.0" encoding="utf-8"?>
<com.oxy.library.PriorityLinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:gravity="center_horizontal"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <TextView
        android:layout_marginTop="5dp"
        android:padding="5dp"
        android:text="If you look for it, I've got a sneaky feeling you'll find that love actually is all around.\n如果你仔细寻找，就会发现爱其实无处不在。"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
    <ListView
        android:id="@+id/listView"
        android:background="#7fff0000"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
    <LinearLayout
        app:measure_priority="1"
        android:gravity="center"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">
        <Button
            android:onClick="onAddChild"
            android:text="  add  "
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />
        <Button
            android:onClick="onRemoveChild"
            android:layout_marginLeft="20dp"
            android:text="  remove  "
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />
    </LinearLayout>
</com.oxy.library.PriorityLinearLayout>

这里app:measure_priority="1"，其实任何大于0的值都可以，PriorityLinearLayout会根据子视图的measure_priority属性值从大到小顺序只需measure子视图
