package com.zs.jt808.server.annotation;


import java.lang.annotation.*;

/**
 * 表示JT808 需要解析的字段
 * 作用于字段上
 * Indicates the fields that need to be parsed in JT808
 * Acts on the fields
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Jt808Field {

    /**
     * 起始字节位置
     * Starting byte position
     */
    int index();

    /**
     * 字节长度, -1 表示从 index 开始到 data数组的最后 即[index,data.length-index]
     * Byte length, -1 means from index to the end of data array, i.e. [index, data.length-index]
     */
    int length();

}
