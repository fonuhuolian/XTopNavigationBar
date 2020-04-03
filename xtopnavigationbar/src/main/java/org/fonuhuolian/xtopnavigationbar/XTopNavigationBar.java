package org.fonuhuolian.xtopnavigationbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.TextKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class XTopNavigationBar extends FrameLayout {

    // 是否支持清除和输入
    private int supportWriteAndClear;
    // 是否有正确的搜索框右侧按钮
    private boolean isHasSearchLayoutRightIcon;

    private TextView xRightBtn1;
    private TextView xRightBtn2;
    private TextView xRightBtn3;

    private LinearLayout xSearchLayout;
    private EditText xSearchView;
    private ImageView xSearchRightIcon;
    private ImageView xClearView;

    private ImageView xBack;
    private TextView xTitle;


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
        LayoutInflater.from(getContext()).inflate(R.layout.widget_x_top_bar, this, true);

        Drawable bg = array.getDrawable(R.styleable.XTopNavigationBar_xBar_background);

        if (bg == null)
            setBarBackground(XTopNavigationBarInit.getBackgroundDrawable());
        else
            setBarBackground(bg);

        float fraction = array.getFraction(R.styleable.XTopNavigationBar_xBar_background_alpha, 1, 1, 1);
        setBarBackgroundAlpha(fraction);

        xRightBtn1 = findViewById(R.id.x_right_btn1);
        xRightBtn2 = findViewById(R.id.x_right_btn2);
        xRightBtn3 = findViewById(R.id.x_right_btn3);

        int flagValue = array.getInt(R.styleable.XTopNavigationBar_xBar_right_btn_show, 0);
        setBarRightBtnsVisibility(flagValue);


        float rightBtnPadding = array.getDimension(R.styleable.XTopNavigationBar_xBar_right_btn_padding, XTopNavigationBarInit.getBarRightBtnsPadding());
        setBarRightBtnsPadding((int) rightBtnPadding);

        float rightBtnTextSize = array.getDimension(R.styleable.XTopNavigationBar_xBar_right_btn_text_size, XTopNavigationBarInit.getBarRightBtnsTextSize());
        setBarRightBtnsTextSize(rightBtnTextSize);

        int color = array.getColor(R.styleable.XTopNavigationBar_xBar_right_btn_text_color, XTopNavigationBarInit.getBarRightBtnsTextColor());
        setBarRightBtnsTextColor(color);

        String text1 = array.getString(R.styleable.XTopNavigationBar_xBar_right_first_btn_text);
        setBarRightBtnsText(RightBtn.RIGHT_FIRST, text1);
        String text2 = array.getString(R.styleable.XTopNavigationBar_xBar_right_second_btn_text);
        setBarRightBtnsText(RightBtn.RIGHT_SECOND, text2);
        String text3 = array.getString(R.styleable.XTopNavigationBar_xBar_right_third_btn_text);
        setBarRightBtnsText(RightBtn.RIGHT_THIRD, text3);

        int resourceId1 = array.getResourceId(R.styleable.XTopNavigationBar_xBar_right_first_btn_image_src, 0);
        setBarRightBtnsImage(RightBtn.RIGHT_FIRST, resourceId1);
        int resourceId2 = array.getResourceId(R.styleable.XTopNavigationBar_xBar_right_second_btn_image_src, 0);
        setBarRightBtnsImage(RightBtn.RIGHT_SECOND, resourceId2);
        int resourceId3 = array.getResourceId(R.styleable.XTopNavigationBar_xBar_right_third_btn_image_src, 0);
        setBarRightBtnsImage(RightBtn.RIGHT_THIRD, resourceId3);

        xSearchLayout = findViewById(R.id.xSearchLayout);

        boolean supportSearchLayout = array.getBoolean(R.styleable.XTopNavigationBar_xBar_searchLayout_support, false);
        setBarSupportSearchLayout(supportSearchLayout);

        Drawable searchBg = array.getDrawable(R.styleable.XTopNavigationBar_xBar_searchLayout_background);
        setBarSearchLayoutBackground(searchBg);

        int searchLayoutMargin = (int) array.getDimension(R.styleable.XTopNavigationBar_xBar_searchLayout_margin, 0);
        int searchLayoutMarginTop = (int) array.getDimension(R.styleable.XTopNavigationBar_xBar_searchLayout_marginTop, 0);
        int searchLayoutMarginBottom = (int) array.getDimension(R.styleable.XTopNavigationBar_xBar_searchLayout_marginBottom, 0);
        int searchLayoutMarginLeft = (int) array.getDimension(R.styleable.XTopNavigationBar_xBar_searchLayout_marginLeft, 0);
        int searchLayoutMarginRight = (int) array.getDimension(R.styleable.XTopNavigationBar_xBar_searchLayout_marginRight, 0);

        if (searchLayoutMargin == 0)
            setBarSearchLayoutMargin(searchLayoutMarginLeft, searchLayoutMarginTop, searchLayoutMarginRight, searchLayoutMarginBottom);
        else
            setBarSearchLayoutMargin(searchLayoutMargin, searchLayoutMargin, searchLayoutMargin, searchLayoutMargin);

        xSearchView = ((EditText) findViewById(R.id.xSearchView));
        xClearView = ((ImageView) findViewById(R.id.xClearView));
        xSearchRightIcon = ((ImageView) findViewById(R.id.xSearchRightIcon));

        int searchEditViewPaddingLeft = (int) array.getDimension(R.styleable.XTopNavigationBar_xBar_searchLayout_edit_paddingLeft, 0);
        int searchEditViewPaddingRight = (int) array.getDimension(R.styleable.XTopNavigationBar_xBar_searchLayout_edit_paddingRight, 0);
        setBarSearchEditViewPadding(searchEditViewPaddingLeft, searchEditViewPaddingRight);

        int drawablePadding = (int) array.getDimension(R.styleable.XTopNavigationBar_xBar_searchLayout_edit_drawablePadding, 0);
        setBarSearchEditViewDrawablePadding(drawablePadding);

        int drawableLeftResourceId = array.getResourceId(R.styleable.XTopNavigationBar_xBar_searchLayout_edit_drawableLeft_src, 0);
        setBarSearchEditViewDrawableLeft(drawableLeftResourceId);

        String hint = array.getString(R.styleable.XTopNavigationBar_xBar_searchLayout_edit_hint);
        setBarSearchEditHint(hint);

        int hintColor = array.getColor(R.styleable.XTopNavigationBar_xBar_searchLayout_edit_hintColor, getResources().getColor(android.R.color.darker_gray));
        setBarSearchEditHintColor(hintColor);

        float editSize = array.getDimension(R.styleable.XTopNavigationBar_xBar_searchLayout_edit_textSize, dip2px(13));
        setBarSearchEditTextSize(editSize);

        int editColor = array.getColor(R.styleable.XTopNavigationBar_xBar_searchLayout_edit_textColor, getResources().getColor(android.R.color.black));
        setBarSearchEditTextColor(editColor);

        // 是否支持清除和输入
        supportWriteAndClear = array.getInt(R.styleable.XTopNavigationBar_xBar_searchLayout_edit_support_write_and_clear, 1);
        setBarSearchSupportWriteAndClear(supportWriteAndClear);

        int closeResourceId = array.getResourceId(R.styleable.XTopNavigationBar_xBar_searchLayout_edit_clear_src, R.drawable.x_temp_close);
        setBarSearchEditClearView(closeResourceId);

        int serachRightIconId = array.getResourceId(R.styleable.XTopNavigationBar_xBar_searchLayout_rightIcon_src, 0);
        setBarSearchLayoutRightIcon(serachRightIconId);

        int searchIconPadding = (int) array.getDimension(R.styleable.XTopNavigationBar_xBar_searchLayout_icon_padding, 0);
        setBarSearchIconPadding(searchIconPadding);

        xBack = ((ImageView) findViewById(R.id.xBack));
        xTitle = ((TextView) findViewById(R.id.xTitle));

        int backIconPadding = (int) array.getDimension(R.styleable.XTopNavigationBar_xBar_backIcon_padding, XTopNavigationBarInit.getBackIconPadding());
        setBarBackIconPadding(backIconPadding);

        int backResourceId = array.getResourceId(R.styleable.XTopNavigationBar_xBar_backIcon_src, XTopNavigationBarInit.getBackResId());
        seBarBackView(backResourceId);

        boolean backVisible = array.getBoolean(R.styleable.XTopNavigationBar_xBar_backIcon_visibility, XTopNavigationBarInit.getBackVisible());
        setBarBackVisible(backVisible);

        int titleColor = array.getColor(R.styleable.XTopNavigationBar_xBar_title_color, XTopNavigationBarInit.getTitleColor());
        setBarTitleColor(titleColor);

        float titleSize = array.getDimension(R.styleable.XTopNavigationBar_xBar_title_textSize, XTopNavigationBarInit.getTitleSize());
        seBarTitleTextSize(titleSize);

        int titlePaddingLeft = (int) array.getDimension(R.styleable.XTopNavigationBar_xBar_title_paddingLeft, 0);
        int titlePaddingRight = (int) array.getDimension(R.styleable.XTopNavigationBar_xBar_title_paddingRight, 0);
        setBarTitlePadding(titlePaddingLeft, titlePaddingRight);

        String titleStr = array.getString(R.styleable.XTopNavigationBar_xBar_title_content);
        setBarTitleContent(titleStr);
    }

    public void setOnXTopBarListener(final OnTopBarListener onXTopBarListener, boolean translucentBars) {

        if (translucentBars)
            TopBarUtil.setPaddingSmart(getContext(), this);

        if (onXTopBarListener == null)
            return;

        xBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onXTopBarListener.onBack(xBack);
            }
        });

        xRightBtn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onXTopBarListener.onRightIconClick(RightBtn.RIGHT_FIRST, xRightBtn1);
            }
        });

        xRightBtn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onXTopBarListener.onRightIconClick(RightBtn.RIGHT_SECOND, xRightBtn2);
            }
        });

        xRightBtn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onXTopBarListener.onRightIconClick(RightBtn.RIGHT_THIRD, xRightBtn3);
            }
        });

        xSearchRightIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onXTopBarListener.onSearchRightIconClick(xSearchRightIcon);
            }
        });

        xSearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String content = charSequence.toString();

                if (supportWriteAndClear == 1) {

                    if (TextUtils.isEmpty(content)) {
                        xClearView.setVisibility(GONE);
                        xSearchRightIcon.setVisibility(isHasSearchLayoutRightIcon ? VISIBLE : GONE);
                    } else {
                        xClearView.setVisibility(VISIBLE);
                        xSearchRightIcon.setVisibility(GONE);
                    }

                } else {
                    xSearchRightIcon.setVisibility(isHasSearchLayoutRightIcon ? VISIBLE : GONE);
                }

                onXTopBarListener.onTextChanged(content);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * TODO 设置搜索布局背景
     *
     * @param drawable 对应背景
     */
    public void setBarBackground(Drawable drawable) {

        if (drawable == null)
            return;

        this.setBackground(drawable);
    }

    /**
     * TODO 设置搜索布局背景
     *
     * @param drawableRes 对应背景
     */
    public void setBarBackgroundDrawable(int drawableRes) {
        Drawable drawable = getResources().getDrawable(drawableRes);
        try {
            this.setBarBackground(drawable);
        } catch (Exception e) {
            Log.e("XTopNavigationBar", e.getMessage());
        }
    }

    /**
     * TODO 设置搜索布局背景
     *
     * @param colorRes 对应背景
     */
    public void setBarBackgroundColor(int colorRes) {
        ColorDrawable drawable = new ColorDrawable(colorRes);
        try {
            this.setBarBackground(drawable);
        } catch (Exception e) {
            Log.e("XTopNavigationBar", e.getMessage());
        }
    }

    /**
     * TODO 设置搜索布局背景
     *
     * @param alphaPercent 透明
     */
    public void setBarBackgroundAlpha(float alphaPercent) {

        Drawable background = this.getBackground();

        if (background == null)
            return;

        int alpha = 0;

        if (alphaPercent <= 0) {
            alpha = 0;
        } else if (alphaPercent >= 1) {
            alpha = 255;
        } else {
            alpha = (int) (255 * alphaPercent);
        }

        background.mutate().setAlpha(alpha);
    }

    /**
     * TODO 获取搜索内容
     */
    public String getBarSearchWord() {
        return xSearchView.getText().toString();
    }

    /**
     * TODO 设置title的padding
     *
     * @param paddingLeft  左边距
     * @param paddingRight 右边距
     */
    public void setBarTitlePadding(int paddingLeft, int paddingRight) {
        xTitle.setPadding(paddingLeft, 0, paddingRight, 0);
    }

    /**
     * TODO 设置返回键是否显示
     *
     * @param backVisible 显示？
     */
    public void setBarBackVisible(boolean backVisible) {
        xBack.setVisibility(backVisible ? VISIBLE : GONE);
    }

    /**
     * TODO 设置title内容
     *
     * @param content 提示内容
     */
    public void setBarTitleContent(String content) {
        xTitle.setText(content);
    }

    /**
     * TODO 设置title内容
     *
     * @param content 内容
     */
    public void setBarTitleContent(int content) {
        xTitle.setText(content);
    }


    /**
     * TODO 设置title文字的大小
     *
     * @param size 字体大小
     */
    public void seBarTitleTextSize(float size) {
        xTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    /**
     * TODO 设置搜title颜色
     *
     * @param color 颜色
     */
    public void setBarTitleColor(@ColorInt int color) {
        xTitle.setTextColor(color);
    }

    /**
     * TODO 设置title颜色
     *
     * @param color 颜色
     */
    public void setBarTitleColor(String color) {
        xTitle.setTextColor(Color.parseColor(color));
    }

    /**
     * TODO 设置返回按钮
     *
     * @param resId 资源id
     */
    public void seBarBackView(@DrawableRes int resId) {

        Drawable drawable_n = null;

        try {
            drawable_n = getResources().getDrawable(resId);
            xBack.setImageDrawable(drawable_n);
        } catch (Exception e) {
            Log.e("XTopNavigationBar", e.getMessage() + "");
        }
    }

    /**
     * TODO 设置返回键的padding
     *
     * @param backIconPadding 左、右边距
     */
    public void setBarBackIconPadding(int backIconPadding) {
        xBack.setPadding(backIconPadding, 0, backIconPadding, 0);
    }

    /**
     * TODO 设置搜索右侧的按钮、清除按钮padding
     *
     * @param searchIconPadding 左、右边距
     */
    public void setBarSearchIconPadding(int searchIconPadding) {
        xClearView.setPadding(searchIconPadding, 0, searchIconPadding, 0);
        xSearchRightIcon.setPadding(searchIconPadding, 0, searchIconPadding, 0);
    }

    /**
     * TODO 设置搜索右侧的按钮
     *
     * @param resId 资源id
     */
    public void setBarSearchLayoutRightIcon(@DrawableRes int resId) {

        Drawable drawable_n = null;

        try {
            drawable_n = getResources().getDrawable(resId);
            xSearchRightIcon.setImageDrawable(drawable_n);
        } catch (Exception e) {
            Log.e("XTopNavigationBar", e.getMessage() + "");
            return;
        }

        isHasSearchLayoutRightIcon = true;
    }

    /**
     * TODO 设置搜索清除的按钮
     *
     * @param resId 资源id
     */
    public void setBarSearchEditClearView(@DrawableRes int resId) {

        Drawable drawable_n = null;

        try {
            drawable_n = getResources().getDrawable(resId);
            xClearView.setImageDrawable(drawable_n);
        } catch (Exception e) {
            Log.e("XTopNavigationBar", e.getMessage() + "");
        }
    }

    private void setBarSearchSupportWriteAndClear(int supportWriteAndClear) {
        xSearchView.setKeyListener(supportWriteAndClear != 1 ? new TextKeyListener(TextKeyListener.Capitalize.NONE, false) : null);
    }


    /**
     * TODO 设置是否支持清除和输入
     *
     * @param supportWriteAndClear 是否支持
     */
    public void setBarSearchSupportWriteAndClear(boolean supportWriteAndClear) {
        xSearchView.setKeyListener(supportWriteAndClear ? new TextKeyListener(TextKeyListener.Capitalize.NONE, false) : null);
    }

    /**
     * TODO 设置搜索文字颜色
     *
     * @param color 提示文颜色
     */
    public void setBarSearchEditTextColor(@ColorInt int color) {
        xSearchView.setTextColor(color);
    }

    /**
     * TODO 设置搜索文颜色
     *
     * @param color 提示文颜色
     */
    public void setBarSearchEditTextColor(String color) {
        xSearchView.setTextColor(Color.parseColor(color));
    }

    /**
     * TODO 设置搜索输入文字的大小（hint文字大小）
     *
     * @param size 字体大小
     */
    public void setBarSearchEditTextSize(float size) {
        xSearchView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    /**
     * TODO 设置搜索提示颜色
     *
     * @param color 提示内容颜色
     */
    public void setBarSearchEditHintColor(@ColorInt int color) {
        xSearchView.setHintTextColor(color);
    }

    /**
     * TODO 设置搜索提示颜色
     *
     * @param color 提示内容颜色
     */
    public void setBarSearchEditHintColor(String color) {
        xSearchView.setHintTextColor(Color.parseColor(color));
    }

    /**
     * TODO 设置搜索提示
     *
     * @param hint 提示内容
     */
    public void setBarSearchEditHint(String hint) {

        if (hint == null)
            hint = "";

        xSearchView.setHint(hint);

    }

    /**
     * TODO 设置搜索提示
     *
     * @param hint 提示内容
     */
    public void setBarSearchEditHint(int hint) {
        xSearchView.setHint(hint);
    }


    /**
     * TODO 设置搜索输入文字距离左边的图片
     *
     * @param resId 资源id
     */
    public void setBarSearchEditViewDrawableLeft(@DrawableRes int resId) {

        Drawable drawable_n = null;

        try {
            drawable_n = getResources().getDrawable(resId);
            drawable_n.setBounds(0, 0, drawable_n.getMinimumWidth(), drawable_n.getMinimumHeight());
        } catch (Exception e) {
            xSearchView.setCompoundDrawables(null, null, null, null);
            return;
        }

        xSearchView.setCompoundDrawables(drawable_n, null, null, null);

    }


    /**
     * TODO 设置搜索输入文字距离左边图片的距离
     *
     * @param padding 边距
     */
    public void setBarSearchEditViewDrawablePadding(int padding) {
        xSearchView.setCompoundDrawablePadding(padding);
    }

    /**
     * TODO 设置搜索输入内边距
     *
     * @param paddingLeft  左边距
     * @param PaddingRight 右边距
     */
    public void setBarSearchEditViewPadding(int paddingLeft, int PaddingRight) {
        xSearchView.setPadding(paddingLeft, 0, PaddingRight, 0);
    }


    /**
     * TODO 设置是否支持搜索框
     *
     * @param isSupport true 支持
     */
    public void setBarSupportSearchLayout(boolean isSupport) {
        xSearchLayout.setVisibility(isSupport ? VISIBLE : INVISIBLE);
    }

    /**
     * TODO 设置搜索布局margin
     *
     * @param left   marginLeft
     * @param top    marginTop
     * @param right  marginRight
     * @param bottom marginBottom
     */
    public void setBarSearchLayoutMargin(int left, int top, int right, int bottom) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) xSearchLayout.getLayoutParams();
        layoutParams.setMargins(left, top, right, bottom);
        xSearchLayout.setLayoutParams(layoutParams);
    }

    /**
     * TODO 设置搜索布局背景
     *
     * @param drawable 对应背景
     */
    public void setBarSearchLayoutBackground(Drawable drawable) {
        xSearchLayout.setBackground(drawable);
    }

    /**
     * TODO 设置右侧三个按钮是否可见
     *
     * @param leftVisible   右侧三个按钮中的左侧按钮是否可见
     * @param middleVisible 右侧三个按钮中中间按钮是否可见
     * @param rightVisible  右侧三个按钮中的右侧按钮是否可见
     */
    public void setBarRightBtnsVisibility(boolean leftVisible, boolean middleVisible, boolean rightVisible) {
        int flag = 0;
        flag += (leftVisible ? 1 : 0);
        flag += (middleVisible ? 10 : 0);
        flag += (rightVisible ? 100 : 0);
        setBarRightBtnsVisibility(flag);
    }

    /**
     * TODO 设置右侧三个按钮内边距
     *
     * @param padding 内边距
     */
    public void setBarRightBtnsPadding(int padding) {
        xRightBtn1.setPadding(padding, 0, padding, 0);
        xRightBtn2.setPadding(padding, 0, padding, 0);
        xRightBtn3.setPadding(padding, 0, padding, 0);
    }

    /**
     * TODO 设置右侧三个按钮的字体大小
     *
     * @param size 字体大小
     */
    public void setBarRightBtnsTextSize(float size) {
        xRightBtn1.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        xRightBtn2.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        xRightBtn3.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    /**
     * TODO 设置右侧三个按钮的字体颜色
     *
     * @param color 字体颜色
     */
    public void setBarRightBtnsTextColor(int color) {
        xRightBtn1.setTextColor(color);
        xRightBtn2.setTextColor(color);
        xRightBtn3.setTextColor(color);
    }

    /**
     * TODO 设置右侧三个按钮的字体颜色
     *
     * @param color 字体颜色
     */
    public void setBarRightBtnsTextColor(String color) {
        xRightBtn1.setTextColor(Color.parseColor(color));
        xRightBtn2.setTextColor(Color.parseColor(color));
        xRightBtn3.setTextColor(Color.parseColor(color));
    }

    /**
     * TODO 设置右侧三个按钮的文字内容
     *
     * @param rightBtn 哪个按钮
     * @param content  对应得按钮内容
     */
    public void setBarRightBtnsText(RightBtn rightBtn, String content) {

        if (content == null)
            content = "";

        switch (rightBtn) {
            case RIGHT_FIRST:
                xRightBtn1.setText(content);
                break;
            case RIGHT_SECOND:
                xRightBtn2.setText(content);
                break;
            case RIGHT_THIRD:
                xRightBtn3.setText(content);
                break;
        }
    }

    /**
     * TODO 获取右侧三个按钮
     *
     * @param rightBtn 哪个按钮
     */
    public TextView getBarRightBtn(RightBtn rightBtn) {

        switch (rightBtn) {
            case RIGHT_FIRST:
                return xRightBtn1;
            case RIGHT_SECOND:
                return xRightBtn2;
            case RIGHT_THIRD:
                return xRightBtn3;
        }

        return xRightBtn1;
    }

    /**
     * TODO 设置右侧三个按钮的图片
     *
     * @param rightBtn 哪个按钮
     * @param resId    对应的按钮图片资源id
     */
    public void setBarRightBtnsImage(RightBtn rightBtn, @DrawableRes int resId) {

        Drawable drawable_n = null;

        try {
            drawable_n = getResources().getDrawable(resId);
            drawable_n.setBounds(0, 0, drawable_n.getMinimumWidth(), drawable_n.getMinimumHeight());
        } catch (Exception e) {
            return;
        }

        switch (rightBtn) {
            case RIGHT_FIRST:
                xRightBtn1.setCompoundDrawables(drawable_n, null, null, null);
                break;
            case RIGHT_SECOND:
                xRightBtn2.setCompoundDrawables(drawable_n, null, null, null);
                break;
            case RIGHT_THIRD:
                xRightBtn3.setCompoundDrawables(drawable_n, null, null, null);
                break;
        }
    }

    /**
     * 设置右侧按钮是否可见
     */
    private void setBarRightBtnsVisibility(int flag) {

        xRightBtn1.setVisibility(GONE);
        xRightBtn2.setVisibility(GONE);
        xRightBtn3.setVisibility(GONE);

        int $hundred = flag / 100;
        int $ten = (flag - $hundred * 100) / 10;
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

    private float dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return dpValue * scale + 0.5f;
    }

}
