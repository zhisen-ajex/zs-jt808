package com.zs.jt808.server.annotation;


import java.lang.annotation.*;

/**
 * 表示为 JT808需要解析的实体类
 * 作用于类上
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Jt808Resolver {

}
