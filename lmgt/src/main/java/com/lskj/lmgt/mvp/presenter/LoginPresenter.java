package com.lskj.lmgt.mvp.presenter;

import android.app.Application;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DataHelper;
import com.jess.arms.utils.DeviceUtils;
import com.jess.arms.utils.LogUtils;
import com.lskj.lmgt.R;
import com.lskj.lmgt.app.SpConstant;
import com.lskj.lmgt.mvp.contract.LoginContract;
import com.lskj.lmgt.mvp.model.response.BaseResponse;
import com.lskj.lmgt.mvp.model.response.TokenRespose;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView) {
        super(model, rootView);
    }

    /**
     * 登录方法
     *
     * @param userName
     * @param userPwd
     */
    public void login(String userName, String userPwd) {
        if (TextUtils.isEmpty(userName)) {
            mRootView.showMessage("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(userPwd)) {
            mRootView.showMessage("请输入密码");
            return;
        }
        if (!DeviceUtils.netIsConnected(mApplication)) {
            mRootView.showMessage(ArmsUtils.getString(mApplication.getApplicationContext(), R.string.not_network_connection));
            return;
        }
        try {
            mRootView.showLoading();
            mModel.requestLogin(userName, userPwd)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ErrorHandleSubscriber<BaseResponse<TokenRespose>>(mErrorHandler) {
                        @Override
                        public void onNext(BaseResponse<TokenRespose> response) {
                            //设置界面数据
                            mRootView.setTvResponse(new Gson().toJson(response));
                            if (response.getCode() == 200) {
                                //保存token 到本地
                                DataHelper.setStringSF(mApplication, SpConstant.SP_ACCESS_TOKEN, response.getData().getToken());
                                DataHelper.setStringSF(mApplication, SpConstant.SP_REFRESH_TOKEN, response.getData().getToken());
                                mRootView.showMessage("登录成功");
                            } else {
                                mRootView.showMessage("登录失败");
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            mErrorHandler.getHandlerFactory().handleError(e);
                            mRootView.hideLoading();
                        }

                        @Override
                        public void onComplete() {
                            mRootView.hideLoading();
                        }
                    });
        } catch (Exception e) {
            mErrorHandler.getHandlerFactory().handleError(e);
            mRootView.hideLoading();
            LogUtils.debugInfo(TAG, e.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
