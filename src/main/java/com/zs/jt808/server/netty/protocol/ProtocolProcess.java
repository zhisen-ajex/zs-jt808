package com.zs.jt808.server.netty.protocol;

import com.zs.jt808.server.netty.request.Jt808Message;
import io.netty.channel.Channel;


public interface ProtocolProcess {

    void messageHandler(Channel channel, Jt808Message message);

}
