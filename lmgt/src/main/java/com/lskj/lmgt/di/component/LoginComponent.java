package com.lskj.lmgt.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.lskj.lmgt.di.module.LoginModule;
import com.lskj.lmgt.mvp.ui.activity.LoginActivity;

import dagger.Component;

@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}