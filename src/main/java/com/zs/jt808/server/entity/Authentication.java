package com.zs.jt808.server.entity;

import com.zs.jt808.server.annotation.Jt808Field;
import com.zs.jt808.server.annotation.Jt808Resolver;
import com.zs.jt808.server.netty.request.Jt808Message;
import lombok.Data;

/**
 * 终端鉴权
 **/
@Data
@Jt808Resolver
public class Authentication extends Jt808Message {


    @Jt808Field(index = 0, length = -1)
    private String authToken;

    public Authentication(Jt808Message message) {
        this.header = message.getHeader();
        this.checkSum = message.getCheckSum();
        this.msgBodyBytes = message.getMsgBodyBytes();
    }
}
