package com.lskj.lmgt.app.utils;

import android.util.Base64;

import com.lskj.lmgt.app.exception.MobileRuntimeException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 文档描述:    {加密类}
 * 作者：     tongzhiwei
 * 创建时间：  2019/11/29
 */
public class EncryptionUtil {
    private static final String SUFFIX = "PIEncrypt";
    private static final byte[] KEY = new byte[]{84, 94, -60, 118, 67, -20, -55, -70};
    private static final byte[] IV = new byte[]{79, 76, -120, -36, -61, -72, 91, 50};

    private static final String getKey(String var0) {
        return EncryptionUtil.piDecrypt("2H1T5whE5Ius4unsFyMQ2VEYiSEcJZs/33+iaZHSpv548yQUt+C7fv/cjM3Rsa18JqdxeHA1zMc=PIEncrypt");
    }

    public static String encryStringParams(String param) {
        return EncryptionUtil.aesEncrypt(param, getKey(null));
    }

    public EncryptionUtil() {
    }

    public static String piEncrypt(String var0) {
        if (var0 == null) {
            throw new NullPointerException("要加密的字符串不能为 null。");
        } else {
            try {
                SecretKey var1 = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(KEY));
                Cipher var2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                var2.init(1, var1, new IvParameterSpec(IV));
                return Base64.encodeToString(var2.doFinal(var0.getBytes("UTF8")), 0).trim() + "PIEncrypt";
            } catch (Exception var3) {
                throw new MobileRuntimeException("使用“PIEncrypt”算法加密字符串时发生错误，请参考: ", var3);
            }
        }
    }

    public static String piDecrypt(String var0) {
        if (var0 == null) {
            throw new NullPointerException("要解密的字符串不能为 null。");
        } else {
            try {
                SecretKey var1 = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(KEY));
                Cipher var2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                var2.init(2, var1, new IvParameterSpec(IV));
                var0 = var0.substring(0, var0.length() - "PIEncrypt".length());
                return new String(var2.doFinal(Base64.decode(var0, 0)), "UTF8");
            } catch (Exception var3) {
                throw new MobileRuntimeException("使用“PIEncrypt”算法解密字符串时发生错误，请参考: ", var3);
            }
        }
    }

    public static String aesEncrypt(String var0, String var1) {
        if (var0 == null) {
            throw new NullPointerException("要加密的字符串不能为 null。");
        } else {
            try {
                SecretKeySpec var2 = new SecretKeySpec(md5EncryptToBytes(var1), "AES");
                Cipher var3 = Cipher.getInstance("AES");
                var3.init(1, var2);
                return ByteUtil.bytes2hex(var3.doFinal(var0.getBytes("UTF-8")), false);
            } catch (Exception var4) {
                throw new MobileRuntimeException("使用“AES”算法加密字符串时发生错误，请参考: ", var4);
            }
        }
    }

    public static String aesDecrypt(String var0, String var1) {
        if (var0 == null) {
            throw new NullPointerException("要加密的字符串不能为 null。");
        } else {
            try {
                SecretKeySpec var2 = new SecretKeySpec(md5EncryptToBytes(var1), "AES");
                Cipher var3 = Cipher.getInstance("AES");
                var3.init(2, var2);
                return new String(var3.doFinal(ByteUtil.hex2Bytes(var0)));
            } catch (Exception var4) {
                throw new MobileRuntimeException("使用“AES”算法加密字符串时发生错误，请参考: ", var4);
            }
        }
    }

    public static String md5EncryptToString(String var0) {
        return ByteUtil.bytes2hex(md5EncryptToBytes(var0), false);
    }

    public static byte[] md5EncryptToBytes(String var0) {
        if (var0 == null) {
            throw new NullPointerException("要加密的字符串不能为 null。");
        } else {
            try {
                MessageDigest var1 = MessageDigest.getInstance("MD5");
                var1.reset();
                var1.update(var0.getBytes("UTF-8"));
                return var1.digest();
            } catch (NoSuchAlgorithmException var2) {
                throw new MobileRuntimeException("当前设备不支持 MD5 哈希算法。");
            } catch (Exception var3) {
                throw new MobileRuntimeException("使用“MD5”算法哈希字符串时发生错误，请参考: ", var3);
            }
        }
    }
}
