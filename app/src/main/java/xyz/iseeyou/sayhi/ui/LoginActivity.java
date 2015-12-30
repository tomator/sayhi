package xyz.iseeyou.sayhi.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.im.util.BmobLog;
import cn.bmob.v3.listener.SaveListener;
import xyz.iseeyou.sayhi.R;
import xyz.iseeyou.sayhi.bean.User;
import xyz.iseeyou.sayhi.config.BmobConstants;
import xyz.iseeyou.sayhi.util.CommonUtils;
import xyz.iseeyou.sayhi.view.AccountInputView;

/**
 * @author smile
 * @ClassName: LoginActivity
 * @Description: TODO
 * @date 2014-6-3 下午4:41:42
 */
public class LoginActivity extends BaseActivity{

    @InjectView(R.id.phoneView)
    AccountInputView phoneView;
    @InjectView(R.id.passwordView)
    AccountInputView passwordView;
    @InjectView(R.id.loginBtn)
    TextView loginBtn;
    @InjectView(R.id.registerBtn)
    TextView registerBtn;

    private MyBroadcastReceiver receiver = new MyBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        //注册退出广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(BmobConstants.ACTION_REGISTER_SUCCESS_FINISH);
        registerReceiver(receiver, filter);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && BmobConstants.ACTION_REGISTER_SUCCESS_FINISH.equals(intent.getAction())) {
                finish();
            }
        }
    }

    @OnClick(R.id.loginBtn) void loginBtn(){
        if (!CommonUtils.isNetworkAvailable(this)) {
            ShowToast(R.string.network_tips);
            return;
        }
        login();
    }

    @OnClick(R.id.registerBtn) void registerBtn(){
        Intent intent = new Intent(LoginActivity.this,
                RegisterActivity.class);
        startActivity(intent);
    }

    private void login() {
        String name = phoneView.getText().toString();
        String password = passwordView.getText().toString();

        if (TextUtils.isEmpty(name)) {
            ShowToast(R.string.phone_not_valid);
            return;
        }

        if (TextUtils.isEmpty(password)) {
            ShowToast(R.string.toast_error_password_null);
            return;
        }

        showLoadingDialog();
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        userManager.login(user, new SaveListener() {
            @Override
            public void onSuccess() {
                //更新用户的地理位置以及好友的资料
                updateUserInfos();
                closeLoadingDialog();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(int errorcode, String arg0) {
                closeLoadingDialog();
                BmobLog.i(arg0);
                ShowToast(arg0);
            }
        });
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver(receiver);
    }

}
