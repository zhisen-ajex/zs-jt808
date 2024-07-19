package com.zs.jt808.server.entity;

import com.zs.jt808.server.netty.request.Jt808Message;
import lombok.Data;

import java.util.List;

@Data
public class LocationInfoBatch extends Jt808Message {

    private int dataNumber; //数据项个数  包含的位置汇报数据项个数，>0
    private int type;   //位置数据类型   0：正常位置批量汇报，1：盲区补报
    private int dataLength; //位置汇报数据体长度,位置汇报数据体总长度 N
    private byte[] locationData;  //位置汇报数据体
    private List<LocationInfo> locationInfos; //位置信息

    public LocationInfoBatch(Jt808Message message) {
        this.header = message.getHeader();
        this.checkSum = message.getCheckSum();
        this.msgBodyBytes = message.getMsgBodyBytes();
    }

}
