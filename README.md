xiaoma
======

 简单可用的 基于web的 android 拼音输入法  pinyin input method

![预览](https://raw.githubusercontent.com/gowithwind/xiaoma/master/preview.png)

初心
======
撸管不求人

Native部分
======
只实现了webview与inputmethodservice的结合。很简单明了。

##todo

实现web文件的自主定制更新


web部分
======
使用了angularjs作为框架，界面方面参考了ios的界面实现。这也是本输入法最好的部分，你可以进行任意的定制，只要你明白web的魔力。

##todo
需要一些工作来实现拼音的正确切分工作

词库部分python
======
词库的来源是ibus-pinyin。使用python，将其转化成js的词库，进行了单字索引和头部索引。py2.js

相关代码在 [https://github.com/gowithwind/fbtext](https://github.com/gowithwind/fbtext) to.py



