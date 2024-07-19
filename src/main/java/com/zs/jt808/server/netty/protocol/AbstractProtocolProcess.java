package com.zs.jt808.server.netty.protocol;

import com.zs.jt808.server.aop.ResolveMethodInterceptor;
import com.zs.jt808.server.netty.request.Jt808Message;
import io.netty.channel.Channel;
import org.springframework.cglib.proxy.Enhancer;

/**
 * 协议处理抽象类
 **/
public abstract class AbstractProtocolProcess implements ProtocolProcess {

    public void messageHandler(Channel channel, Jt808Message message) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new ResolveMethodInterceptor(message));
        enhancer.setSuperclass(this.getClass());
        AbstractProtocolProcess o = (AbstractProtocolProcess) enhancer.create();
        Jt808Message obj = o.resolve(message);
        obj = this.process(obj);
        this.send(channel, obj);
    }

    /**
     * 解析数据
     *
     * @param message
     * @return
     */
    protected abstract Jt808Message resolve(Jt808Message message);

    /**
     * 处理逻辑
     *
     * @param message
     * @return
     */
    protected abstract Jt808Message process(Jt808Message message);

    /**
     * 回应客户端
     *
     * @param channel
     * @param message
     */
    protected void send(Channel channel, Jt808Message message) {
        channel.writeAndFlush(message);
    }

}
