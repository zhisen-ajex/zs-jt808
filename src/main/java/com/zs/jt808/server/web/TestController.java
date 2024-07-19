package com.zs.jt808.server.web;

import com.zs.jt808.server.constants.Jt808MessageType;
import com.zs.jt808.server.netty.codec.Jt808Encoder;
import com.zs.jt808.server.netty.request.Jt808FixedHeader;
import com.zs.jt808.server.netty.request.Jt808Message;
import com.zs.jt808.server.netty.session.SessionStore;
import com.zs.jt808.server.netty.session.SessionStoreService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private SessionStoreService sessionStoreService;

    /**
     * type  1:查询位置信息  2:查询终端属性
     *
     * @param type
     * @param no
     * @return
     */
    @RequestMapping("/search/{type}/{no}")
    public String searchLocation(@PathVariable("type") Integer type, @PathVariable("no") String no) {
        try {
            SessionStore sessionStore = sessionStoreService.get(no);
            if (sessionStore == null) {
                return "终端未连接！！！";
            }
            Jt808MessageType messageType;
            if (type == 1) {
                messageType = Jt808MessageType.CLIENT_LOCATION_INFO_DOWN;
            } else if (type == 2) {
                messageType = Jt808MessageType.CLIENT_PROPERTY_DOWN;
            } else {
                return "不支持的类型";
            }
            Channel channel = sessionStore.getChannel();
            Jt808Encoder encoder = Jt808Encoder.INSTANCE;

            Jt808Message jt808Message = new Jt808Message();
            jt808Message.setReplayType(messageType);

            Jt808FixedHeader header = new Jt808FixedHeader();
            header.setMessageType(messageType);
            header.setTerminalPhone(sessionStore.getClientId());
            jt808Message.setHeader(header);

            ByteBuf byteBuf = encoder.doEncode(channel, jt808Message);
            channel.writeAndFlush(byteBuf);
        } catch (Exception e) {
            log.error(e.toString(), e);
            return e.toString();
        }
        return "OK";
    }

}
