
package com.zs.jt808.server.netty.protocol.process;

import com.zs.jt808.server.entity.TextReport;
import com.zs.jt808.server.netty.protocol.AbstractProtocolProcess;
import com.zs.jt808.server.netty.request.Jt808Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 【0x6006】设备上报文本消息
 **/
@Slf4j
@Component
public class ReportTextProcess extends AbstractProtocolProcess {

    @Override
    protected Jt808Message resolve(Jt808Message message) {
        return new TextReport(message);
    }

    @Override
    protected Jt808Message process(Jt808Message message) {
        log.debug(">>>设备上报文本消息：{}", message);
        return message;
    }

}
