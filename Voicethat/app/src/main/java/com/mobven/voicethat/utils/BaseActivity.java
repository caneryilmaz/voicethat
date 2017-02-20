package com.mobven.voicethat.utils;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mobvenmobven on 20/02/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(setLayout());
    }



    @LayoutRes
    public int setLayout() {
        return 0;
    }

}
