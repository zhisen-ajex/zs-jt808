package com.zs.jt808.server.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties("snowflake")
public class SnowflakeProperties {

    private long workerId;
    private long datacenterId;

}
