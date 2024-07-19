package com.zs.jt808.server.web;

import com.ajex.core.web.dto.Response;
import com.zs.jt808.server.service.JT808MessageService;
import com.zs.jt808.server.web.request.JT8300Request;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Tag(name = "Device", description = "API for interacting with terminal devices")
@RequestMapping("device")
public class JT808DeviceController {

    @Autowired
    private JT808MessageService messageService;


    @Operation(summary = "Send text information to terminal devices through platform.[8300 protocol]")
    @PostMapping("/{clientId}/8300")
    public Mono<Response> T8300(@Parameter(description = "Terminal mobile phone number") @PathVariable("clientId") String clientId, @RequestBody JT8300Request request) {
        return messageService.requestR(clientId, request);

    }

}