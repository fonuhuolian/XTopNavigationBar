package org.fonuhuolian.topnavigationbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import org.fonuhuolian.xtopnavigationbar.OnTopBarListener;
import org.fonuhuolian.xtopnavigationbar.RightBtn;
import org.fonuhuolian.xtopnavigationbar.XTopNavigationBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XTopNavigationBar navigationBar = (XTopNavigationBar) findViewById(R.id.top);
        navigationBar.setOnXTopBarListener(new OnTopBarListener() {
            @Override
            public void onBack(ImageView back) {

            }

            @Override
            public void onRightIconClick(RightBtn rightBtn, TextView right) {

            }

            @Override
            public void onSearchRightIconClick(ImageView right) {

            }

            @Override
            public void onTextChanged(String content) {

            }
        }, false);
    }
}
