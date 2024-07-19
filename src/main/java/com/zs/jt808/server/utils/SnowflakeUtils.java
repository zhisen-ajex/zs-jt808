package com.zs.jt808.server.utils;

import cn.hutool.core.util.IdUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SnowflakeUtils {

    @Autowired
    private SnowflakeProperties snowflakeProperties;

    private static SnowflakeProperties staticSnowflakeProperties;

    @PostConstruct
    public void init(){
        staticSnowflakeProperties = this.snowflakeProperties;
    }

    public static long nextId(){
        return IdUtil.getSnowflake(staticSnowflakeProperties.getWorkerId(), staticSnowflakeProperties.getDatacenterId()).nextId();
    }

}
