package com.zs.jt808.server.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@Data
public class JT8300Request implements Serializable {

    @Schema(description = "One byte,Definition of text information flags bit：" +
            " [0] Emergency" +
            " [1~7] Reserved")
    private int flag;

    @Schema(description = "The maximum is 1024 bytes, ASCII encoding")
    private String content;

    public byte[] toByteArray() {
        // 将int转换为byte[]
        byte[] flagBytes = ByteBuffer.allocate(4).putInt(flag).array();

        // 将String转换为byte[]，这里假设使用ASCII编码
        byte[] contentBytes = content.getBytes(StandardCharsets.US_ASCII);

        // 创建一个新的byte[]来存储flag和content的字节
        byte[] result = new byte[flagBytes.length + contentBytes.length];

        // 复制flagBytes到result
        System.arraycopy(flagBytes, 0, result, 0, flagBytes.length);

        // 复制contentBytes到result，紧接着flagBytes之后
        System.arraycopy(contentBytes, 0, result, flagBytes.length, contentBytes.length);

        return result;
    }
}
