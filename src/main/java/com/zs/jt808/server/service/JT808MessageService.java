package com.zs.jt808.server.service;

import cn.hutool.core.util.ArrayUtil;
import com.ajex.core.web.dto.Response;
import com.zs.jt808.server.constants.Jt808MessageType;
import com.zs.jt808.server.netty.codec.Jt808Encoder;
import com.zs.jt808.server.netty.request.Jt808FixedHeader;
import com.zs.jt808.server.netty.request.Jt808Message;
import com.zs.jt808.server.netty.session.SessionStore;
import com.zs.jt808.server.netty.session.SessionStoreService;
import com.zs.jt808.server.utils.HexStringUtils;
import com.zs.jt808.server.web.request.JT8300Request;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;


@Component
@Slf4j
public class JT808MessageService {


    private static final Mono<Void> NEVER = Mono.never();
    private static final String OFFLINE_EXCEPTION = "离线的客户端（请检查设备是否注册或者鉴权）";
    private static final String OFFLINE_RESULT = "离线的客户端（请检查设备是否注册或者鉴权）";


    @Autowired
    private SessionStoreService sessionStoreService;

    public Mono<Response> requestR(String clientId, @RequestBody JT8300Request request) {

        SessionStore sessionStore = sessionStoreService.get(clientId);
        Channel channel = sessionStore.getChannel();
        Jt808Encoder encoder = Jt808Encoder.INSTANCE;

        Jt808Message jt808Message = new Jt808Message();
        Jt808FixedHeader header = new Jt808FixedHeader();
        header.setMessageType(Jt808MessageType.SEND_TEXT_INFO_DOWN);
        header.setTerminalPhone(sessionStore.getClientId());
        jt808Message.setHeader(header);
        jt808Message.setReplayType(Jt808MessageType.SEND_TEXT_INFO_DOWN); //Charset.forName("GB2312"
        byte[] bytes = ArrayUtil.addAll(
                HexStringUtils.integerTo1Bytes(request.getFlag())
                , request.getContent().getBytes(Charset.forName("GBK"))
        );

        jt808Message.setMsgBodyBytes(bytes);
        ByteBuf byteBuf = encoder.doEncode(channel, jt808Message);

        return Mono.create((sink) -> {
            this.sessionStoreService.get(clientId).getChannel().writeAndFlush(byteBuf).addListener((future) -> {
                if (future.isSuccess()) {
                    sink.success();
                } else {
                    sink.error(future.cause());
                }

            });
        });
    }
}