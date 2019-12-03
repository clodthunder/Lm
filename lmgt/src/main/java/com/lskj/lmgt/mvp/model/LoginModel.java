package com.lskj.lmgt.mvp.model;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.lskj.lmgt.app.utils.EncryptionUtil;
import com.lskj.lmgt.mvp.contract.LoginContract;
import com.lskj.lmgt.mvp.model.api.service.UserService;
import com.lskj.lmgt.mvp.model.response.BaseResponse;
import com.lskj.lmgt.mvp.model.response.TokenRespose;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;


@ActivityScope
public class LoginModel extends BaseModel implements LoginContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public LoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Timber.d("LoginModel onCreate()");
    }

    /**
     * 请求用户登录信息
     *
     * @return
     */
    @Override
    public Observable<BaseResponse<TokenRespose>> requestLogin(String userName, String pwd) throws Exception {
        return mRepositoryManager.obtainRetrofitService(UserService.class)
                .requestLogin(EncryptionUtil.encryStringParams(userName), EncryptionUtil.encryStringParams(pwd));
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        Timber.d("Release Resource LoginModel onPause()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}