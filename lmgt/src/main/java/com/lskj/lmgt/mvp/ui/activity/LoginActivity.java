package com.lskj.lmgt.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.lskj.lmgt.R;
import com.lskj.lmgt.di.component.DaggerLoginComponent;
import com.lskj.lmgt.di.module.LoginModule;
import com.lskj.lmgt.mvp.contract.LoginContract;
import com.lskj.lmgt.mvp.presenter.LoginPresenter;
import com.lskj.lmgt.mvp.ui.dialog.DialogLoadingProgress;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 这个是测试的登录界面
 *
 * @author Administrator
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    @BindView(R.id.et_user_name)
    AppCompatEditText etUserName;
    @BindView(R.id.et_user_pwd)
    AppCompatEditText etUserPwd;
    @BindView(R.id.tv_response)
    AppCompatTextView tvResponse;

    @OnClick({R.id.btn_login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                //调用登录方法
                mPresenter.login(Objects.requireNonNull(etUserName.getText()).toString(),
                        Objects.requireNonNull(etUserPwd.getText()).toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showLoading() {
        DialogLoadingProgress.getInstance(this).show();
    }

    @Override
    public void hideLoading() {
        DialogLoadingProgress.getInstance(this).dismiss();
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void setTvResponse(String value) {
        tvResponse.setText(value);
    }
}
