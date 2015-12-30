package xyz.iseeyou.sayhi.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.umeng.analytics.MobclickAgent;

import xyz.iseeyou.sayhi.util.Log;
import xyz.iseeyou.sayhi.view.LoadingDialog;

/**
 * Created by storm on 14-3-25.
 */
public abstract class BaseFragment extends Fragment {
    public static final String TAG = "fragmentLife";
    protected LoadingDialog progressDialog;
    private Handler handler = new Handler();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG,"onAttach "+getClass().getSimpleName()+" tag = "+getTag());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate "+getClass().getSimpleName()+" tag = "+getTag());

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated "+getClass().getSimpleName()+" tag = "+getTag());
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getName());
        Log.d(TAG,"onResume "+getClass().getSimpleName()+" tag = "+getTag());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getName());
        Log.d(TAG,"onPause "+getClass().getSimpleName()+" tag = "+getTag());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"onStop "+getClass().getSimpleName()+" tag = "+getTag());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy " + getClass().getSimpleName());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint ====== " + getClass().getSimpleName() + " ========== " + isVisibleToUser);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState " + getClass().getSimpleName());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(TAG, "onViewStateRestored " + getClass().getSimpleName());
    }

    public void showMyProgressDialog(){
        if(progressDialog == null){
            progressDialog = new LoadingDialog(getActivity());
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    progressDialog.show();
                } catch (Exception e) {
                }
            }
        });

    }

    public void closeMyProgressDialog(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (progressDialog != null) {
                    try {
                        progressDialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
