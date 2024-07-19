package com.zs.jt808.server.entity;

import com.zs.jt808.server.annotation.Jt808Field;
import com.zs.jt808.server.annotation.Jt808Resolver;
import lombok.Data;

import java.util.Date;

/**
 * OBD故障码
 **/
@Data
@Jt808Resolver
public class FaultCode extends DataTransmission {

    //1. 上传时间 BCD[0-5]
    @Jt808Field(index = 0, length = 6)
    private Date time;

    //2. 上传类型 byte[6] BYTE 0-主动上传；1-查询上传
    @Jt808Field(index = 6, length = 1)
    private int uploadType;

    // 3. 故障码数量 byte[7-10] WORD
    @Jt808Field(index = 7, length = 4)
    private int num;

    // 4. byte[11-x] 故障码记录集
    @Jt808Field(index = 11, length = -1)
    private String recordSet;

    public FaultCode(DataTransmission message) {
        super(message);
        this.messageType = message.getMessageType();
    }
}
