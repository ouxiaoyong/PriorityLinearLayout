# PriorityLinearLayout
这是一个可以自定义子View measure顺序的LinearLayout框架
# 应用场景
 ![image](https://github.com/ouxiaoyong/PriorityLinearLayout/images/device-2018-10-11-144409.png)
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
