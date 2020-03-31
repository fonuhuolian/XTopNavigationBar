package org.fonuhuolian.xtopnavigationbar;

import android.widget.ImageView;
import android.widget.TextView;

public interface OnTopBarListener {

    void onBack(ImageView back);

    void onRightIconClick(RightBtn rightBtn, TextView right);

    void onSearchRightIconClick(ImageView right);

    void onTextChanged(String content);
}
