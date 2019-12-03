package com.lskj.lmgt.mvp.model.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 文档描述:    {登录或者刷新token使用到}
 * 作者：      Administrator
 * 创建时间：  2019/11/29
 *
 * @author Administrator
 */
public class TokenRespose implements Parcelable {
    /**
     * 记录app 接口调用的token
     */
    private String token;
    private String refreshToken;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeString(this.refreshToken);
    }

    public TokenRespose() {
    }

    protected TokenRespose(Parcel in) {
        this.token = in.readString();
        this.refreshToken = in.readString();
    }

    public static final Creator<TokenRespose> CREATOR = new Creator<TokenRespose>() {
        @Override
        public TokenRespose createFromParcel(Parcel source) {
            return new TokenRespose(source);
        }

        @Override
        public TokenRespose[] newArray(int size) {
            return new TokenRespose[size];
        }
    };
}
