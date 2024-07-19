package com.zs.jt808.server.entity;

import com.zs.jt808.server.annotation.Jt808Field;
import com.zs.jt808.server.annotation.Jt808Resolver;
import com.zs.jt808.server.netty.request.Jt808Message;
import lombok.Data;

import java.util.Date;

/**
 * 终端主动上发文本消息，平台收到后须回复平台通用应答
 **/
@Data
@Jt808Resolver
public class TextReport extends Jt808Message {

    //Text message encoding method: 0x00 ASCII encoding;  0x01 UNICODE encoding method
    @Jt808Field(index = 0, length = 1)
    private int encodingMethod;
    @Jt808Field(index = 1, length = -1)
    private String textContent;

    public TextReport(Jt808Message message) {
        this.header = message.getHeader();
        this.checkSum = message.getCheckSum();
        this.msgBodyBytes = message.getMsgBodyBytes();
    }
}
