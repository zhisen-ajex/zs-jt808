package com.zs.jt808.server.netty;

import com.zs.jt808.server.constants.Jt808Constants;
import com.zs.jt808.server.netty.codec.Jt808Decoder;
import com.zs.jt808.server.netty.codec.Jt808Encoder;
import com.zs.jt808.server.netty.codec.LoggingDecoder;
import com.zs.jt808.server.netty.handler.Jt808TransportHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
public class Jt808ChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Value("${jt808.read-timeout}")
    private int readTimeOut;
    @Value("${jt808.netty.biz_group_thread_count}")
    private int bizGroupThreadCount;

    @Autowired
    private Jt808TransportHandler jt808TransportHandler;

    private NioEventLoopGroup bizGroup;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("idleStateHandler", new IdleStateHandler(readTimeOut, 0, 0, TimeUnit.MINUTES));
        pipeline.addLast(new LoggingDecoder());//记录日志
        pipeline.addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer(new byte[]{Jt808Constants.PKG_DELIMITER}),
                Unpooled.copiedBuffer(new byte[]{Jt808Constants.PKG_DELIMITER, Jt808Constants.PKG_DELIMITER})));
        pipeline.addLast("decoder", new Jt808Decoder());
        pipeline.addLast("encoder", Jt808Encoder.INSTANCE);
        pipeline.addLast(bizGroup, jt808TransportHandler);
    }

    @PostConstruct
    public void init() {
        bizGroup = new NioEventLoopGroup(bizGroupThreadCount, new DefaultThreadFactory("biz-pool"));
    }

}
