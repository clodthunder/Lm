package com.lskj.lmgt.app.utils;

import com.lskj.lmgt.app.exception.MobileRuntimeException;

/**
 * 文档描述:    {}
 * 作者：     tongzhiwei
 * 创建时间：  2019/11/29
 */
public class ByteUtil {
    public ByteUtil() {
    }

    public static String bytes2hex(byte[] var0, boolean var1) {
        if (var0 == null) {
            throw new NullPointerException("要转换的 byte 数组不能为 null。");
        } else {
            StringBuilder var2 = new StringBuilder(var0.length);
            byte[] var6 = var0;
            int var5 = var0.length;

            for (int var4 = 0; var4 < var5; ++var4) {
                byte var3 = var6[var4];
                var2.append(String.format("%02x", var3));
            }

            return var1 ? var2.toString().toUpperCase() : var2.toString();
        }
    }

    public static byte[] hex2Bytes(String var0) {
        if (var0 == null) {
            throw new NullPointerException("要转换的十六进制字符串不能为 null。");
        } else {
            var0 = var0.trim();
            int var1 = var0.length();
            if (var1 == 0) {
                return null;
            } else if (var1 % 2 != 0) {
                throw new MobileRuntimeException("要转换的十六进制字符串必须为偶数位。");
            } else {
                byte[] var2 = new byte[var1 / 2];

                try {
                    for (int var3 = 0; var3 < var1; var3 += 2) {
                        var2[var3 / 2] = (byte) Integer.parseInt(var0.substring(var3, var3 + 2), 16);
                    }

                    return var2;
                } catch (Exception var4) {
                    throw new MobileRuntimeException("要转换的十六进制字符串格式不正确，请参考: " + var4);
                }
            }
        }
    }
}
