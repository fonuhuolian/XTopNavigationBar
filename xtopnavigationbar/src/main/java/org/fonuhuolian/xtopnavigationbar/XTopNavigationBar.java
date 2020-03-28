package org.fonuhuolian.xtopnavigationbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class XTopNavigationBar extends FrameLayout {

    // 加载的样式
    private int style;

    public XTopNavigationBar(@NonNull Context context) {
        this(context, null);
    }

    public XTopNavigationBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XTopNavigationBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {

        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.XTopNavigationBar, defStyle, 0);
        style = array.getInt(R.styleable.XTopNavigationBar_xBar_style, 1);

        if (style == 1)
            LayoutInflater.from(getContext()).inflate(R.layout.widget_x_top_bar, this, true);
        else
            LayoutInflater.from(getContext()).inflate(R.layout.widget_x_top_bar2, this, true);
    }
}
