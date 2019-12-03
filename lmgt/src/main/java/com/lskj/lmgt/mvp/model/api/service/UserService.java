package com.lskj.lmgt.mvp.model.api.service;

import com.lskj.lmgt.mvp.model.response.BaseResponse;
import com.lskj.lmgt.mvp.model.response.TokenRespose;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 文档描述:    {用户模块的请求接口}
 * 作者：      Administrator
 * 创建时间：  2019/10/17
 * @author Administrator
 */
public interface UserService {
    @FormUrlEncoded
    @POST("authz/login")
    Observable<BaseResponse<TokenRespose>> requestLogin(@Field("account") String userName, @Field("password") String pwd);
}
