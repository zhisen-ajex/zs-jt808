package com.zs.jt808.server.netty.protocol.process;

import com.zs.jt808.server.netty.protocol.AbstractProtocolProcess;
import com.zs.jt808.server.netty.request.Jt808Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 【0x0003】终端注销
 **/
@Slf4j
@Component
public class LogoutProcess extends AbstractProtocolProcess {
    @Override
    protected Jt808Message resolve(Jt808Message message) {
        return message;
    }

    @Override
    protected Jt808Message process(Jt808Message message) {
        log.debug(">>>终端注销：{}", message);
        return message;
    }
}
