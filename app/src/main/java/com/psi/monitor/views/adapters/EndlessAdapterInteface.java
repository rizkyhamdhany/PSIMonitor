package com.psi.monitor.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by hamdhanywijaya@gmail.com on 8/10/17.
 */

public interface EndlessAdapterInteface {
    public RecyclerView.ViewHolder onCreateView(ViewGroup parent, int viewType);
    public void onBindView(RecyclerView.ViewHolder holder, int position);
    public boolean isNotNullDataSet(int position);
    public int itemVisibleThreshold();
}
