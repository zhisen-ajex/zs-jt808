package com.zs.jt808.server.netty.protocol.process.inverted;

import com.zs.jt808.server.constants.Jt808MessageType;
import com.zs.jt808.server.netty.protocol.AbstractProtocolProcess;
import com.zs.jt808.server.netty.request.Jt808Message;
import com.zs.jt808.server.utils.Jt808Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 通用应答解析，仅测试用，方便对照
 */
@Slf4j
@Component
public class ResponseCommonProcess extends AbstractProtocolProcess {

    @Override
    protected Jt808Message resolve(Jt808Message message) {
        byte[] data = message.getMsgBodyBytes();
        int flowId = Jt808Utils.parseIntFromBytes(data, 0, 2);
        Jt808MessageType messageType = Jt808MessageType.valueOf(Jt808Utils.parseIntFromBytes(data, 2, 2));
        byte replyCode = data[4];
        log.info("<<< 通用应答解析 flowId:{},messageType:{},replyCode:{}", flowId, messageType, replyCode);
        return message;
    }

    @Override
    protected Jt808Message process(Jt808Message message) {
        return message;
    }
}
