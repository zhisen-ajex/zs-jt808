package com.zs.jt808.server.netty.protocol.process;

import com.zs.jt808.server.netty.protocol.AbstractProtocolProcess;
import com.zs.jt808.server.netty.request.Jt808Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 【0x0002】链路心跳
 **/
@Slf4j
@Component
public class LinkHeartbeatProcess extends AbstractProtocolProcess {

    @Override
    protected Jt808Message resolve(Jt808Message message) {
        return message;
    }

    @Override
    protected Jt808Message process(Jt808Message message) {
        log.debug(">>>链路心跳：{}", message);
        return message;
    }
}
