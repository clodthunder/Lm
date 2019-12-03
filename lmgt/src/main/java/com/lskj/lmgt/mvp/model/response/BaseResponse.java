package com.lskj.lmgt.mvp.model.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 文档描述:    {统一返回}
 * 作者：      Administrator
 * 创建时间：  2019/11/29
 * @author Administrator
 */
public class BaseResponse<T extends Parcelable> implements Parcelable {
    /**
     * 错误码
     **/
    private int code;
    /**
     * 提示信息
     **/
    private String msg;
    /**
     * 具体的内容
     **/
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    protected BaseResponse(Parcel in) {
        code = in.readInt();
        msg = in.readString();
        String dataName = in.readString();
        try {
            this.data = in.readParcelable(Class.forName(dataName).getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static final Creator<BaseResponse> CREATOR = new Creator<BaseResponse>() {
        @Override
        public BaseResponse createFromParcel(Parcel in) {
            return new BaseResponse(in);
        }

        @Override
        public BaseResponse[] newArray(int size) {
            return new BaseResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.msg);
        dest.writeParcelable(this.data, flags);
    }
}
