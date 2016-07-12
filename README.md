# Version2Template
**UI 2.0 模板**
##说明
**请面试官在看了代码后，无论是好还是不好，无论如何给我一个答复，因为我之后将在github中删除这个项目**
###模板的相关说明
- 模板的主要内容在library/src/com.hitv.android.uiversion2.builder文件中

- 构造者有ScrollBuilder和TitleBuilder两个抽象类，他们分别构造模板的内容和头部，并且由ScrollCustomBuilder和TitleCustomBuilder继承，实现了抽象方法。

- 两个构造者主要由主导者IDirector进行调用，他是接口由AbstractDirector抽象类实现了公有的方法，AbstracDirector由Director实现了所有抽象方法。

###模板的使用
- 调用IDirector.setScrollBuilder和IDirector.setTitleBuilder可以设置自己实现的两个构造者。

- 调用IDirector.construct进行构造。

- 这里使用模板方法模式把setIContent留给使用者自己实现，让构造完全自定义。setIContent主要由constrct调用。

###自定义控件的说明
- 自定义控件的内容都在library/src/com.hitv.android.uiversion2.custom中

- HorizontalList就是一个横向添加子控件的ViewGroup，他支持横向的弹性滑动（主要让焦点滑到中间），**他支持子元素的复用**。

- FlowLayout是网上的一个向右添加子控件，满了后在下一行继续添加的控件，**主要对他修改了焦点的控制**

###项目中对模板的使用
- 项目主要用了MVP模式。

- 项目中使用模板的类主要是demo/src/com.hitv.android.hotel.ui.activity.TemplateActivity和demo/src/com.hitv.android.hotel.ui.fragment.DiningFoodFragment这两个。

- 在demo/src/com.hitv.android.hotel.ui.activity 中的HotelAboutActivity、HotelMemberActivity、hotelSeverDetailActivity都继承了TemplateActivity来使用模板。

##效果图
![Mou icon](https://github.com/yuyinghao/Version2Template/blob/master/show1.jpg) ![Mou icon](https://github.com/yuyinghao/Version2Template/blob/master/show2.jpg)
