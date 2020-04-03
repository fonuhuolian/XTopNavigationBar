package org.fonuhuolian.xtopnavigationbar;

import android.app.Application;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

public class XTopNavigationBarInit {

    private static XTopNavigationBarInit instance;

    private static Application app;

    private static Drawable backgroundDrawable;

    private static Boolean backVisible;

    private static Float titleSize;

    private static Integer titleColor;

    private static Integer backResId;

    private static Integer backIconPadding;

    private static Float barRightBtnsTextSize;

    private static Integer barRightBtnsTextColor;

    private static Integer barRightBtnsPadding;

    private XTopNavigationBarInit() {

    }

    public static XTopNavigationBarInit initialize(Application context) {

        if (instance == null)
            instance = new XTopNavigationBarInit();

        app = context;

        return instance;
    }


    public static XTopNavigationBarInit addBarBackgroundColor(int colorRes) {

        if (instance == null)
            throw new RuntimeException("请在Application先调用 initialize() 方法 ！！！");

        if (backgroundDrawable == null)
            backgroundDrawable = new ColorDrawable(colorRes);

        return instance;
    }

    public static XTopNavigationBarInit addBarBackgroundDrawable(int drawableRes) {

        if (instance == null)
            throw new RuntimeException("请在Application先调用 initialize() 方法 ！！！");

        if (backgroundDrawable == null)
            backgroundDrawable = app.getResources().getDrawable(drawableRes);

        return instance;
    }

    public static XTopNavigationBarInit addBarTitleTextSize(float size) {

        if (instance == null)
            throw new RuntimeException("请在Application先调用 initialize() 方法 ！！！");

        if (titleSize == null)
            titleSize = size;

        return instance;
    }


    public static XTopNavigationBarInit addBarTitleColor(@ColorInt int color) {

        if (instance == null)
            throw new RuntimeException("请在Application先调用 initialize() 方法 ！！！");

        if (titleColor == null)
            titleColor = color;

        return instance;
    }


    public static XTopNavigationBarInit addBarTitleColor(String color) {

        if (instance == null)
            throw new RuntimeException("请在Application先调用 initialize() 方法 ！！！");

        if (titleColor == null)
            titleColor = Color.parseColor(color);

        return instance;
    }


    public static XTopNavigationBarInit addBarVisible(boolean visible) {

        if (instance == null)
            throw new RuntimeException("请在Application先调用 initialize() 方法 ！！！");

        if (backVisible == null)
            backVisible = visible;

        return instance;
    }


    public static XTopNavigationBarInit addBarBackView(@DrawableRes int resId) {

        if (instance == null)
            throw new RuntimeException("请在Application先调用 initialize() 方法 ！！！");

        if (backResId == null)
            backResId = resId;

        return instance;
    }

    public static XTopNavigationBarInit addBarBackIconPadding(int padding) {

        if (instance == null)
            throw new RuntimeException("请在Application先调用 initialize() 方法 ！！！");

        if (backIconPadding == null)
            backIconPadding = padding;

        return instance;
    }


    public static XTopNavigationBarInit addBarRightBtnsTextSize(float size) {

        if (instance == null)
            throw new RuntimeException("请在Application先调用 initialize() 方法 ！！！");

        if (barRightBtnsTextSize == null)
            barRightBtnsTextSize = size;

        return instance;
    }

    public static XTopNavigationBarInit addBarRightBtnsTextColor(int color) {

        if (instance == null)
            throw new RuntimeException("请在Application先调用 initialize() 方法 ！！！");

        if (barRightBtnsTextColor == null)
            barRightBtnsTextColor = color;

        return instance;
    }

    public static XTopNavigationBarInit addBarRightBtnsTextColor(String color) {

        if (instance == null)
            throw new RuntimeException("请在Application先调用 initialize() 方法 ！！！");

        if (barRightBtnsTextColor == null)
            barRightBtnsTextColor = Color.parseColor(color);

        return instance;
    }

    public static XTopNavigationBarInit addBarRightBtnsPadding(int padding) {

        if (instance == null)
            throw new RuntimeException("请在Application先调用 initialize() 方法 ！！！");

        if (barRightBtnsPadding == null)
            barRightBtnsPadding = padding;

        return instance;
    }


    /*****************TODO 不对外提供***********************/

    protected static Drawable getBackgroundDrawable() {
        return backgroundDrawable;
    }

    protected static Boolean getBackVisible() {
        return backVisible == null ? true : backVisible;
    }

    protected static Float getTitleSize() {
        return titleSize == null ? 45 : titleSize;
    }

    protected static Integer getTitleColor() {
        return titleColor == null ? Color.parseColor("#ffffff") : titleColor;
    }

    protected static Integer getBackResId() {
        return backResId == null ? 0 : backResId;
    }

    protected static Integer getBackIconPadding() {
        return backIconPadding == null ? 0 : backIconPadding;
    }

    protected static Float getBarRightBtnsTextSize() {
        return barRightBtnsTextSize == null ? 45 : barRightBtnsTextSize;
    }

    protected static Integer getBarRightBtnsTextColor() {
        return barRightBtnsTextColor == null ? Color.parseColor("#ffffff") : barRightBtnsTextColor;
    }

    protected static Integer getBarRightBtnsPadding() {
        return barRightBtnsPadding == null ? 0 : barRightBtnsPadding;
    }

}