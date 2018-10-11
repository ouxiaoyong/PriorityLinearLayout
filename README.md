# PriorityLinearLayout
这是一个可以自定义子View measure顺序的LinearLayout框架
# 应用场景

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
