package xyz.iseeyou.sayhi.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import xyz.iseeyou.sayhi.config.Constants;
import xyz.iseeyou.sayhi.R;
import xyz.iseeyou.sayhi.bean.User;
import xyz.iseeyou.sayhi.ui.FragmentBase;
import xyz.iseeyou.sayhi.ui.adapter.ShowAdapter;
import xyz.iseeyou.sayhi.util.Log;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowFragment extends FragmentBase {

    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;
    @InjectView(R.id.refreshView)
    PtrFrameLayout refreshView;

    private ShowAdapter showAdapter;
    private int page = 0;
    private static final int COUNT = 20;

    public ShowFragment() {
        // Required empty public constructor
    }

    private int i = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show, container, false);
        ButterKnife.inject(this, view);
        showAdapter = new ShowAdapter(getContext());
        final StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.initWithString("hello");
        header.setLineWidth(5);
        header.setTextColor(Color.RED);
        refreshView.setHeaderView(header);
        refreshView.addPtrUIHandler(header);
        refreshView.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                i++;
                if (i % 3 == 0) {
                    header.initWithString("hello");
                }
                if (i % 3 == 1) {
                    header.initWithString("like");
                }
                if (i % 3 == 2) {
                    header.initWithString("love");
                }
                loadDatas(true);
            }
        });
        refreshView.autoRefresh();
        recyclerView.setAdapter(showAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private void loadDatas(boolean refresh){
        if(refresh){
            page = 0;
        }
        String sql = "select * from _User order by onlineTime desc limit ?,?";
        BmobQuery<User> query = new BmobQuery<>();
        query.doSQLQuery(getContext(), sql, new SQLQueryListener<User>() {
            @Override
            public void done(BmobQueryResult<User> bmobQueryResult, BmobException e) {
                if(e == null){
                    List<User> list = bmobQueryResult.getResults();
                    Collections.sort(list);
                }else {
                    Log.i(Constants.TAG_SERVER, "错误码：" + e.getErrorCode() + "，错误描述：" + e.getMessage());
                }
                refreshView.refreshComplete();
            }
        }, page * COUNT, COUNT);
    }
}
