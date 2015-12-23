/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package xyz.iseeyou.sayhi.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import butterknife.ButterKnife;
import butterknife.InjectView;
import xyz.iseeyou.sayhi.R;
import xyz.iseeyou.sayhi.ui.NewMainActivity;
import xyz.iseeyou.sayhi.util.DensityUtil;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.blog_default)
            .showImageForEmptyUri(R.drawable.blog_default)
            .showImageOnFail(R.drawable.blog_default)
            .cacheInMemory(true)
            .displayer(new RoundedBitmapDisplayer(DensityUtil.dp2px(4f)))
            .build();


    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.avatarView)
        ImageView avatarView;
        @InjectView(R.id.nameView)
        TextView nameView;
        @InjectView(R.id.ageView)
        TextView ageView;
        @InjectView(R.id.addressView)
        TextView addressView;
        @InjectView(R.id.distanceView)
        TextView distanceView;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.inject(this, v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getLayoutPosition() + " clicked.");
                }
            });
        }
    }

    public ShowAdapter() {
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.show_item, viewGroup, false);
        return new ViewHolder(v);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        int width = NewMainActivity.point.x / 2 - DensityUtil.dp2px(12);
        viewHolder.avatarView.setLayoutParams(new LinearLayout.LayoutParams(width, width));
        ImageLoader.getInstance().displayImage("", viewHolder.avatarView, options);
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 100;
    }
}