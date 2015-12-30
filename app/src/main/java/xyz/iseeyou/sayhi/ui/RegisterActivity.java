package xyz.iseeyou.sayhi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.im.util.BmobLog;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.listener.SaveListener;
import xyz.iseeyou.sayhi.R;
import xyz.iseeyou.sayhi.bean.User;
import xyz.iseeyou.sayhi.config.BmobConstants;
import xyz.iseeyou.sayhi.util.Validator;
import xyz.iseeyou.sayhi.view.AccountInputView;

public class RegisterActivity extends BaseActivity {


    @InjectView(R.id.phoneView)
    AccountInputView phoneView;
    @InjectView(R.id.getAuthcodeBtn)
    Button getAuthcodeBtn;
    @InjectView(R.id.authcodeView)
    AccountInputView authcodeView;
    @InjectView(R.id.passwordView)
    AccountInputView passwordView;
    @InjectView(R.id.registerBtn)
    TextView registerBtn;

    private String inputPhoneNumber;
    private CountDownTimer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
        initTopBarForLeft("注册");
    }

    @OnClick(R.id.registerBtn) void registerBtn(){
        register();
    }


    private void register() {
        final String phone = phoneView.getText().toString();
        final String password = passwordView.getText().toString();
        final String authcode = authcodeView.getText().toString();
        inputPhoneNumber = phone;
        if (!Validator.isPhoneNumber(phone) || !phone.equals(inputPhoneNumber)) {
            ShowToast(R.string.phone_not_valid);
            phoneView.requestFocus();
            return;
        }
        if (password.length() < 6) {
            ShowToast(R.string.password_len_error);
            passwordView.requestFocus();
            return;
        }

        if (password.length() > 30) {
            ShowToast(R.string.password_len_error);
            passwordView.requestFocus();
            return;
        }
        int auth;
        try {
            auth = Integer.parseInt(authcode);
        } catch (NumberFormatException e) {
            ShowToast(R.string.authcode_not_valid);
            authcodeView.requestFocus();
            return;
        }
        showLoadingDialog();

        final User user = new User();
        user.setUsername(UUID.randomUUID().toString());
        user.setMobilePhoneNumber(phone);
        user.setMobilePhoneNumberVerified(true);
        user.setPassword(password);
        user.setDeviceType("android");
        user.setInstallId(BmobInstallation.getInstallationId(this));
        user.signUp(RegisterActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {
                closeLoadingDialog();
                ShowToast("注册成功");
                userManager.bindInstallationForRegister(user.getMobilePhoneNumber());
                updateUserLocation();
                //发广播通知登陆页面退出
                sendBroadcast(new Intent(BmobConstants.ACTION_REGISTER_SUCCESS_FINISH));
                // 启动主页
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(int arg0, String arg1) {
                BmobLog.i(arg1);
                ShowToast("注册失败:" + arg1);
                closeLoadingDialog();
            }
        });
    }

    @OnClick(R.id.getAuthcodeBtn)
    void getAuthcodeBtnClick() {
        final String phone = phoneView.getText().toString();
        if (Validator.isPhoneNumber(phone)) {
            getAuthcodeBtn.setEnabled(false);
        } else {
            ShowToast("请输入正确的手机号码。");
        }
    }

    private void startCountDown() {
        mTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secend = millisUntilFinished / 1000;
                getAuthcodeBtn.setText(secend + "秒");
            }

            @Override
            public void onFinish() {
                getAuthcodeBtn.setText(R.string.get_authcode);
                getAuthcodeBtn.setEnabled(true);
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

}
