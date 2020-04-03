# XTopNavigationBar
顶部导航栏

> 添加依赖

`root build.gradle `
```
allprojects {
    repositories {
        ...
        maven {
            url 'https://jitpack.io'
        }
    }
}
```
`module build.gradle `
```
implementation 'com.github.fonuhuolian:XTopNavigationBar:1.0.5'
```

> 混淆
```
-dontwarn org.fonuhuolian.xtopnavigationbar.**
-keep class org.fonuhuolian.xtopnavigationbar.**{*;}
```

> Notice
```
基本都是以xBar_为自定义属性和方法
```
> 全局初始化(默认的样式)
```
XTopNavigationBarInit.initialize(this)
        .addBarBackgroundDrawable(R.drawable.base_bg)
        .addBarBackView(R.mipmap.back)
        .addBarTitleColor("#ffffff")
        .addBarTitleTextSize(45);
```

> xml

```
<org.fonuhuolian.xtopnavigationbar.XTopNavigationBar
    android:id="@+id/top_bar"
    android:layout_width="match_parent"
    android:layout_height="46dp"
    app:xBar_background_alpha="0%"
    app:xBar_right_second_btn_image_src="@mipmap/guanli"
    app:xBar_right_third_btn_image_src="@mipmap/fx"
    app:xBar_title_content="详情" />
```

> 代码

```
topBar = findViewById(R.id.topBar);

// 状态栏透明（用来使用沉浸式）
TopBarUtil.darkMode(this);

// 设置右侧三个按钮是否显示
topBar.setBarRightBtnsVisibility(false, true, true);
...


// 监听事件
// boolean translucentBars ture 会拉伸一个状态栏的高度
topBar.setOnXTopBarListener(new OnTopBarListener() {
        @Override
        public void onBack(ImageView back) {
            // 返回键监听
            onBackPressed();
        }

        @Override
        public void onRightIconClick(RightBtn rightBtn, TextView right) {

            // 右侧三个按钮监听
            switch (rightBtn) {
                case RIGHT_THIRD:
                            break;
                case RIGHT_SECOND:
                            break;
            }
        }

        @Override
        public void onSearchRightIconClick(ImageView right) {
            // 搜索右侧按钮监听
        }

        @Override
        public void onTextChanged(String content) {
            // 搜索内容变化监听
        }
    }, true);
});
```

> 效果

![效果](https://github.com/fonuhuolian/XTopNavigationBar/blob/master/app/doc/info.png?raw=true)
