package xyz.iseeyou.sayhi.ui.adapter;

import android.widget.BaseAdapter;

/**
 * Created by bici on 15/7/13.
 */
public abstract class LoadMoreAdapter extends BaseAdapter {
    protected boolean loadingMore = false;
    protected boolean loadMoreEnabled = false;
    protected OnLoadEndListener onLoadEndListener;

    public interface OnLoadEndListener{
        public void onLoadEnd();
    }

    public void setOnLoadEndListener(OnLoadEndListener onLoadEndListener){
        this.onLoadEndListener = onLoadEndListener;
    }

    public void setLoadingMoreFinished(){
        loadingMore = false;
    }

    public void setLoadMoreEnabled(boolean loadMoreEnabled) {
        this.loadMoreEnabled = loadMoreEnabled;
    }
}
