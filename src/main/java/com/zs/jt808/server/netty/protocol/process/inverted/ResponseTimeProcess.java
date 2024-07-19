package com.zs.jt808.server.netty.protocol.process.inverted;

import com.zs.jt808.server.netty.protocol.AbstractProtocolProcess;
import com.zs.jt808.server.netty.request.Jt808Message;
import com.zs.jt808.server.utils.Jt808Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 响应时间包解析，仅测试用，方便对照
 */
@Slf4j
@Component
public class ResponseTimeProcess extends AbstractProtocolProcess {

    @Override
    protected Jt808Message resolve(Jt808Message message) {
        byte[] data = message.getMsgBodyBytes();
        Date date = Jt808Utils.generateDate(data, 0, data.length);
        message.setReplayTime(date);
        log.info("<<< 响应时间包解析 replayTime:{}", date);
        return message;
    }

    @Override
    protected Jt808Message process(Jt808Message message) {
        return message;
    }

}
