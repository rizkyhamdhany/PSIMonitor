package com.psi.monitor.views.fragments;

import android.view.View;

/**
 * Created by hamdhanywijaya@gmail.com on 8/10/17.
 */

public interface FragmentInteface {
    public void initView(View view);
    public void setUICallbacks();
    public void updateUI();
    public String getPageTitle();
    public int getFragmentLayout();
    public String getTag();
}
