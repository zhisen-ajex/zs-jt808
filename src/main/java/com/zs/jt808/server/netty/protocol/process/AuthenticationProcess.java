package com.zs.jt808.server.netty.protocol.process;

import cn.hutool.core.util.StrUtil;
import com.zs.jt808.server.constants.Jt808Constants;
import com.zs.jt808.server.entity.Authentication;
import com.zs.jt808.server.netty.protocol.AbstractProtocolProcess;
import com.zs.jt808.server.netty.request.Jt808Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 【0x0102】终端鉴权
 **/
@Slf4j
@Component
public class AuthenticationProcess extends AbstractProtocolProcess {

    @Override
    protected Jt808Message resolve(Jt808Message message) {
        return new Authentication(message);
    }

    @Override
    protected Jt808Message process(Jt808Message message) {
        if (message.getReplyCode() != Jt808Constants.RESP_SUCCESS) return message;
        Authentication msg = (Authentication) message;
        log.debug(">>>终端鉴权：{}", msg);
   /*     RSAPrivateKey privateKey = RsaKeyUtil.getRsaPrivateKey();
        RSA rsa = new RSA(privateKey, null);
        if (!StrUtil.equals(msg.getAuthToken(), rsa.encryptBcd(message.getHeader().getTerminalPhone(), KeyType.PrivateKey))) {
            msg.setReplyCode(Jt808Constants.RESP_FAILURE);
            log.error("终端鉴权失败，鉴权验证码：{}", msg.getAuthToken());
        }*/
        if (!StrUtil.equals(msg.getAuthToken(), "bsjgps")) {
            msg.setReplyCode(Jt808Constants.RESP_FAILURE);
            log.error("终端鉴权失败，鉴权验证码：{}", msg.getAuthToken());
        }
        return message;
    }
}
