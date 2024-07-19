package com.zs.jt808.server.netty.protocol.process;

import cn.hutool.core.date.DateUtil;
import com.zs.jt808.server.constants.Jt808MessageType;
import com.zs.jt808.server.netty.protocol.AbstractProtocolProcess;
import com.zs.jt808.server.netty.request.Jt808Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 【0x0F00】终端请求时间
 **/
@Slf4j
@Component
public class RequestTimeProcess extends AbstractProtocolProcess {

    @Override
    protected Jt808Message resolve(Jt808Message message) {
        return message;
    }

    @Override
    protected Jt808Message process(Jt808Message message) {
        message.setReplayType(Jt808MessageType.REQUEST_TIME_DOWN);
        message.setReplayTime(DateUtil.date());
//        log.debug(">>>终端请求时间：{}",message);
        return message;
    }
}
