package com.zs.jt808.server.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.crypto.SecureUtil;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;

/**
 * 私钥
 */
public class RsaKeyUtil {
    /**
     * 生成私钥文件
     */
    public static void main(String[] args) {
        KeyPair keyPair = SecureUtil.generateKeyPair("RSA", 512, "ajex".getBytes());
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        String privatePath = "keystore/auth-private.key";
        IoUtil.writeObjects(FileUtil.getOutputStream(privatePath), true, privateKey);
    }

    /**
     * Currently BSJ devices do not support encryption processing
     * @return
     */
    public static RSAPrivateKey getRsaPrivateKey() {
        RSAPrivateKey privateKey = IoUtil.readObj(RsaKeyUtil.class.getClassLoader().getResourceAsStream("keystore/dauth-private.key"));
        return privateKey;
    }


}
