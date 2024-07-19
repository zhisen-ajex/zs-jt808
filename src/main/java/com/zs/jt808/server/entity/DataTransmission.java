package com.zs.jt808.server.entity;

import com.zs.jt808.server.constants.Jt808MessageType;
import com.zs.jt808.server.netty.request.Jt808Message;
import lombok.Data;

/**
 * 数据上行透传
 **/
@Data
public class DataTransmission extends Jt808Message {

    protected Jt808MessageType messageType;

    public DataTransmission(Jt808Message message) {
        this.header = message.getHeader();
        this.checkSum = message.getCheckSum();
        this.msgBodyBytes = message.getMsgBodyBytes();
        this.replayType = Jt808MessageType.DATA_TRANSMISSION_DOWN;
    }

}
