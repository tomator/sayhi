package xyz.iseeyou.sayhi.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.srain.cube.views.ptr.PtrFrameLayout;
import xyz.iseeyou.sayhi.R;
import xyz.iseeyou.sayhi.ui.FragmentBase;

public class BlogFragment extends FragmentBase {


    @InjectView(R.id.listView)
    ListView listView;
    @InjectView(R.id.refreshView)
    PtrFrameLayout refreshView;

    public BlogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
