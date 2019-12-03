package com.lskj.lmgt.app.exception;

/**
 * 文档描述:    {}
 * 作者：     tongzhiwei
 * 创建时间：  2019/11/29
 */
public class MobileRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -2303393439745844216L;

    public MobileRuntimeException() {
    }

    public MobileRuntimeException(String var1) {
        super(var1);
    }

    public MobileRuntimeException(Throwable var1) {
        super(var1);
    }

    public MobileRuntimeException(String var1, Throwable var2) {
        super(var1, var2);
    }
}
