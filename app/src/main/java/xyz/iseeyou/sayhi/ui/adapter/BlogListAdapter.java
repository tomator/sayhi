package xyz.iseeyou.sayhi.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;
import java.util.List;

import xyz.iseeyou.sayhi.adapter.base.ViewHolder;
import xyz.iseeyou.sayhi.bean.Blog;

/**
 * Created by bbssyyuui on 15/07/21.
 */

public class BlogListAdapter extends LoadMoreAdapter {
    private DisplayImageOptions mImageOptions;
    private List<Blog> blogList = new ArrayList<>();
    private Context context;
    private Handler handler = new Handler();

    public BlogListAdapter(Context context) {
        this.context = context;
        mImageOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .considerExifParams(true)
                .build();
    }

    public void addDatas(List<Blog> blogs){
        blogList.addAll(blogs);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        }, 50);
    }

    public void clearDatas(){
        blogList.clear();
        handler.post(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (loadMoreEnabled && !loadingMore
                && onLoadEndListener != null
                && position >= (blogList.size() - 2)) {
            loadingMore = true;
            onLoadEndListener.onLoadEnd();
        }
        ViewHolder viewHolder;
        if (convertView == null) {
//            convertView = LayoutInflater.from()
//            viewHolder = new ViewHolder(convertView);
//            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (blogList.size() <= 0) {
            return convertView;
        }

        Blog blog = (Blog) getItem(position);
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return blogList.get(position);
    }

    @Override
    public int getCount() {
        return blogList.size();
    }

}
