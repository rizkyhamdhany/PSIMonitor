package com.psi.monitor.views.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.psi.monitor.R;

/**
 * Created by hamdhanywijaya@gmail.com on 8/10/17.
 */

public abstract class EndlessBaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements EndlessAdapterInteface {
    protected Context context;
    protected int lastVisibleItem, totalItemCount;
    protected boolean loading = false;
    protected final int VIEW_HEADER = 2;
    protected final int VIEW_ITEM = 1;
    protected final int VIEW_PROG = 0;
    protected OnLoadMoreListener onLoadMoreListener;
    protected LinearLayoutManager linearLayoutManager;

    public EndlessBaseAdapter(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager){
        this.linearLayoutManager = linearLayoutManager;
        recyclerView.addOnScrollListener(parentOnScrollListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        if (viewType == VIEW_ITEM) {
            return onCreateView(parent, viewType);
        } else if(viewType == VIEW_HEADER){
            return onCreateView(parent, viewType);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.progressbar_item, parent, false);

            ProgressViewHolder vh = new ProgressViewHolder(v);
            return vh;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProgressViewHolder) {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        } else {
            onBindView(holder, position);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        }
    }

    private RecyclerView.OnScrollListener parentOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            totalItemCount = linearLayoutManager.getItemCount();
            lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            if (!loading && totalItemCount <= (lastVisibleItem + itemVisibleThreshold())) {
                // End has been reached
                // Do something
                if (onLoadMoreListener != null) {
                    onLoadMoreListener.onLoadMore();
                }
            }
        }
    };

    @Override
    public int getItemViewType(int position) {
        return isNotNullDataSet(position) ? VIEW_ITEM : VIEW_PROG;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setLoaded(boolean loading) {
        this.loading = loading;
    }

    void loadImage(String url, int placeHolder, ImageView target) {
        Glide.with(context).load(url).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(placeHolder)
                .error(placeHolder)
                .into(target);

    }
}
