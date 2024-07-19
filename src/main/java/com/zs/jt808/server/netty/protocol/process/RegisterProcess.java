package com.zs.jt808.server.netty.protocol.process;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.zs.jt808.server.constants.Jt808Constants;
import com.zs.jt808.server.constants.Jt808MessageType;
import com.zs.jt808.server.netty.protocol.AbstractProtocolProcess;
import com.zs.jt808.server.netty.request.Jt808Message;
import com.zs.jt808.server.entity.Register;
import com.zs.jt808.server.utils.RsaKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;

/**
 * 【0x0100】终端注册
 **/
@Slf4j
@Component
public class RegisterProcess extends AbstractProtocolProcess {

    @Override
    protected Jt808Message resolve(Jt808Message message) {
        return new Register(message);
    }

    @Override
    protected Jt808Message process(Jt808Message message) {
        if (message.getReplyCode() != Jt808Constants.RESP_SUCCESS) return message;
        Register msg = (Register) message;
//        RSAPrivateKey privateKey = RsaKeyUtil.getRsaPrivateKey();
//        RSA rsa = new RSA(privateKey, null);
        //String token = rsa.encryptBcd(message.getHeader().getTerminalPhone(), KeyType.PrivateKey);
        msg.setReplayToken("bsjjps");
        msg.setReplayType(Jt808MessageType.REGISTER_DOWN);
        return msg;
    }

}
