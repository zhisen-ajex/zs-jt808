package com.zs.jt808.server.entity;

import lombok.Data;

/**
 * 位置附加信息
 * Location Additional Information Item Format
 *
 * Field                   Data Type   Descriptions and Requirements
 * ----------------------  ----------  --------------------------------------------------------
 * id                        BYTE       1-255, Length of additional information
 * length                    BYTE       Defines the length of the additional information
 * additionalContent          -         Definition of additional information is shown in follow
 *

 *
 * Additional Information ID   Length (Bytes)  Descriptions and Requirements
 * -------------------------  --------------  --------------------------------------------------
 * 0x01                        4 (DWORD)       Mileage, 1/10km, corresponding to the odometer reading on the car
 * 0x30                         1               Signal strength of wireless communication network
 * 0x31                         1               GNSS positioning satellites
 * 0xEB                         n               Custom additional information extension command.Format: [Length 1+Command 1+Content 1]+...+[Length N+Command N+Content N]
 */


/**实际案例分析
 * 原始的16进制附加数据01040000000030011C310100EB4F000C00B28986049701217007311000060089FFFFFFFF000600C5FFFFFFE70003010204000400CE0179000B00D801CC0025540D89B14A0004002D0EA0001100D5383635373330303736313136363836
 *
 * 第一段附加信息010400000000
 * 0x01(里程)  04附加长度 00000000[里程(1/10km):0]
 * 第二段附加信息30011C
 * 0x30(无线通信网络信号强度)      01附加长度   1C[信号强度28,27-255 网络信号优 （todo 大于小于这个范围呢）]
 *
 * 第三段附加信息310100
 * 0x31(GNSS定位卫星数)      01附加长度   00[卫星数：0, todo 这有一个什么范围呢]
 *
 * #######################以下属于扩展附加内容######################################33
 * 第四段附加信息EB4F000C 00B289860497012170073110 00060089FFFFFFFF 000600C5FFFFFFE7 0003010204   000400CE0179 000B00D801CC0025540D89B14A 0004002D0EA0 001100D5383635373330303736313136363836
 * [EB]EB扩展  [4F]EB扩展长度
 *
 * 第4.1段 （格式：长度+命令+内容）00B289860497012170073110
 * [000C]信息长度 [00B2]EB扩展信息ID(命令)  [89860497012170073110]ICCID号:89860497012170073110	(todo ICCID号是用来干嘛的)
 * 第4.2段 （格式：长度+命令+内容）00060089FFFFFFFF
 * [0006]信息长度 [0089]EB扩展信息ID(命令)  [FFFFFFFF]0089扩展的报警状态位(todo 报警啥呢):[正常]
 * 第4.3段 （格式：长度+命令+内容）000600C5FFFFFFE7
 * [0006]信息长度 [00C5]EB扩展信息ID(命令)  [FFFFFFE7]00C5扩展报警2状态位:[不定位]
 * 第4.4段 （格式：长度+命令+内容）0003010204
 * [0003]信息长度 [0102]EB扩展信息ID(命令)  [04]上传GSM信号强度等级:04（todo 这是啥意思呢，都有哪些值）
 * 第4.5段 （格式：长度+命令+内容）000400CE0179
 * [0004]信息长度 [00CE]EB扩展信息ID(命令)  [0179]外置电压值上传:3.77V(todo 什么是外置电压以及是377/100?)
 * 第4.6段 （格式：长度+命令+内容）000B00D801CC0025540D89B14A
 * [000B]信息长度 [00D8]EB扩展信息ID(命令)  [01CC0025540D89B14A]单基站上传（4G）(todo 这个值如何分析和使用，都有哪些数值)
 * 第4.7段 （格式：长度+命令+内容）0004002D0EA0
 * [0004]信息长度 [002D]EB扩展信息ID(命令)  [0EA0]内置电压值上传:3.744V（4G）(todo 什么是内置电压)
 * 第4.8段 （格式：长度+命令+内容）001100D5383635373330303736313136363836
 * [0011]信息长度 [00D5]EB扩展信息ID(命令)  [383635373330303736313136363836]IMEI上传:865730076116686(todo 和终端id有什么区别，终端id,imei,终端手机号)
 */
@Data
public class LocationExtraInfo {
    //附加信息 ID(byte)  0x01=1,0x30=48,0x31=49,
    private Integer id;
    //附加信息长度 (byte)
    private Integer length;
    //附加信息 (不定长)
    private byte[] bytesValue;
    private Object additionalContent;

}
