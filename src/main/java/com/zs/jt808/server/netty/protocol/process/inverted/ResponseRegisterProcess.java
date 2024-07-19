package com.zs.jt808.server.netty.protocol.process.inverted;

import com.zs.jt808.server.netty.protocol.AbstractProtocolProcess;
import com.zs.jt808.server.netty.request.Jt808Message;
import com.zs.jt808.server.utils.Jt808Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 终端注册应答包解析，仅测试用，方便对照
 */
@Slf4j
@Component
public class ResponseRegisterProcess extends AbstractProtocolProcess {
    @Override
    protected Jt808Message resolve(Jt808Message message) {
        byte[] data = message.getMsgBodyBytes();
        int flowId = Jt808Utils.parseIntFromBytes(data, 0, 2);
        /**
         * 0：成功；
         * 1：车辆已经注册；
         * 2：数据库中无该车辆；
         * 3：终端已经被注册；
         * 4：数据库中无该终端；
         */
        byte replyCode = data[2];
        String replayToken = Jt808Utils.parseStringFromBytes(data, 3, data.length - 3);
        log.info("<<< 终端注册应答包解析 flowId:{},replyCode:{},replayToken:{}", flowId, replyCode, replayToken);
        return message;
    }

    @Override
    protected Jt808Message process(Jt808Message message) {
        return message;
    }
}
