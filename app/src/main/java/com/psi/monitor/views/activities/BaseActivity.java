package com.psi.monitor.views.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by hamdhanywijaya@gmail.com on 8/10/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements ActivityInterface{

    protected Context context;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        context = this;
        initView();
        setUICallbacks();
        updateUI();
    }

}
