package com.zs.jt808.server.annotation;


import java.lang.annotation.*;

/**
 * Indicates the entity class that needs to be parsed by JT808
 * Acts on the class
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Jt808Resolver {

}
