package com.zs.jt808.server.config;

import io.swagger.v3.oas.models.servers.Server;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Data
@ConfigurationProperties(prefix = "openapi")
public class OpenAPIProperties {

    private String title;

    private String description;

    private String version;

    private List<Server> servers;

}
