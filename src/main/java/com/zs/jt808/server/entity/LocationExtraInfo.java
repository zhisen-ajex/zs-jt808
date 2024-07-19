package com.zs.jt808.server.entity;

import lombok.Data;

/**
 * 位置附加信息
 **/
@Data
public class LocationExtraInfo {

    private Integer id;             //附加信息 ID(byte)
    private Integer length;         //附加信息长度 (byte)
    private byte[] bytesValue;      //附加信息 (不定长)

}
