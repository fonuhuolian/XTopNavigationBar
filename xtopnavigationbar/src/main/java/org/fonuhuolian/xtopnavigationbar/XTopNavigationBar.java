package org.fonuhuolian.xtopnavigationbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class XTopNavigationBar extends FrameLayout {

    // 加载的样式
    private int style;

    private TextView xRightBtn1;
    private TextView xRightBtn2;
    private TextView xRightBtn3;

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

        xRightBtn1 = findViewById(R.id.x_right_btn1);
        xRightBtn2 = findViewById(R.id.x_right_btn2);
        xRightBtn3 = findViewById(R.id.x_right_btn3);

        int flagValue = array.getInt(R.styleable.XTopNavigationBar_xBar_right_btn_show, 0);


        setRightBtnsVisibility(flagValue);
    }

    /**
     * 
     */
    private void setRightBtnsVisibility(int flag) {

        xRightBtn1.setVisibility(GONE);
        xRightBtn2.setVisibility(GONE);
        xRightBtn3.setVisibility(GONE);

        int $hundred = flag / 100;
        int $ten = (flag - $hundred * 100)/10;
        int $one = flag - $hundred * 100 - $ten * 10;

        if ($hundred == 0)
            xRightBtn3.setVisibility(GONE);
        else
            xRightBtn3.setVisibility(VISIBLE);

        if ($ten == 0)
            xRightBtn2.setVisibility(GONE);
        else
            xRightBtn2.setVisibility(VISIBLE);

        if ($one == 0)
            xRightBtn1.setVisibility(GONE);
        else
            xRightBtn1.setVisibility(VISIBLE);
    }
}
