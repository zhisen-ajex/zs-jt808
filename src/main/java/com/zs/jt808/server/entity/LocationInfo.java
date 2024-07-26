package com.zs.jt808.server.entity;

import cn.hutool.core.util.NumberUtil;
import com.zs.jt808.server.annotation.Jt808Field;
import com.zs.jt808.server.annotation.Jt808Math;
import com.zs.jt808.server.netty.request.Jt808Message;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class LocationInfo extends Jt808Message {

    // byte[0-3] 报警标志(DWord(32))
    /**
     * bit0  Reserved, default is 0
     * bit1  1: Over-speed alarm(The flag is maintained until the alarm condition is released)
     * bit2-6 Reserved, default is 0
     * bit7  1: The terminal main power is under voltage(The flag is maintained until the alarm condition is released) //todo 多少v算低电压，低电压可以继续使用多久，设备会有告警声和量红灯吗?这个需要平台做什么呢
     * bit8   1: The terminal main power is power down(The flag is maintained until the alarm condition is released)  //todo 关机了还能发送数据?这个需要平台做什么呢
     * bit9-31 Reserved, default is 0
     */
    @Jt808Field(index = 0, length = 4)
    private int alarmFlag; //TODO 这个字段一般如何使用呢

    // byte[4-7] 状态(DWORD(32))
    /**
     * bit0  1： ACC 开 //todo 这个是自适应巡航？
     * bit1  0：未定位
     * bit2  0：北纬  //todo 这个和经纬度字段的区别在什么
     * bit3  0：东经 //todo 这个和经纬度字段的区别在什么
     * bit4  0：运营状态
     * bit5  0：经纬度未经保密插件加密
     * bit8-9 00：空车
     * bit10  0：车辆油路正常
     * bit11  0：车辆电路正常
     * bit12  0：车门解锁
     * bit13  0：门 1 关
     * bit14  0：门 2 关
     * bit15  0：门 3 关
     * bit16  0：门 4 关
     * bit17  0：门 5 关
     * bit18  0：未使用 GPS 卫星进行定位
     * bit19  0：未使用北斗卫星进行定位
     * bit20  0：未使用 GLONASS 卫星进行定位
     * bit21  0：未使用 Galileo 卫星进行定位
     * bit22  0：车辆处于停止状态
     */
    @Jt808Field(index = 4, length = 4)
    private int status;  //TODO 这个字段一般如何使用呢

    // byte[8-11] 纬度(DWORD(32)) 以度位单位的纬度值乘以10的6次方，精确到百万分之一度
    @Jt808Field(index = 8, length = 4)
    @Jt808Math(aClass = NumberUtil.class, method = "div", number = 100_0000)
    private double latitude;

    // byte[12-15] 经度(DWORD(32)) 以度位单位的纬度值乘以10的6次方，精确到百万分之一度
    @Jt808Field(index = 12, length = 4)
    @Jt808Math(aClass = NumberUtil.class, method = "div", number = 100_0000)
    private double longitude;

    // byte[16-17] 高程(WORD(16)) 海拔高度，单位为米（ m）
    @Jt808Field(index = 16, length = 2)
    private int altitude;

    // byte[18-19] 速度(WORD) 1/10km/h
    @Jt808Field(index = 18, length = 2)
    @Jt808Math(aClass = NumberUtil.class, method = "div", number = 10)
    private double speed;

    // byte[20-21] 方向(WORD) 0-359，正北为 0，顺时针
//    @Jt808Field(index = 20,length = 2)
    @Jt808Field(index = 20, length = 1)
    private int direction;

    // byte[22-x] 时间(BCD[6]) YY-MM-DD-hh-mm-ss (utc 时间，本标准中之后涉及的时间均采用此时区)
    @Jt808Field(index = 22, length = 6)
    private Date time;

    //里程，DWord，1/10km，对应车上的里程表读数
//    @Jt808Field(index = 30,length = 4)
//    @Jt808Math(aClass = NumberUtil.class, method = "div")
    private double mileage;

    //油量，Word，1/10L，对应车上油量表读数
//    @Jt808Field(index = 36,length = 2)
//    @Jt808Math(aClass = NumberUtil.class, method = "div")
    private double oilMass;

    //位置附加信息
//    @Jt808Field(index = 28,length = -1)
    private List<LocationExtraInfo> locationExtraInfos;

    public LocationInfo() {

    }

    public LocationInfo(Jt808Message message) {
        this();
        this.header = message.getHeader();
        this.checkSum = message.getCheckSum();
        this.msgBodyBytes = message.getMsgBodyBytes();
    }

}
