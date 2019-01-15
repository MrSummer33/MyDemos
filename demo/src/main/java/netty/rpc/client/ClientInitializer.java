package netty.rpc.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import netty.rpc.common.encoder.baocai.BClientDecoder;
import netty.rpc.common.encoder.baocai.BClientEncoder;

/**
 * Created by BaoCai on 2018/12/22. Description:
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        p.addLast(new BClientEncoder());
        p.addLast(new BClientDecoder());
        p.addLast(new ClientHandler());
    }

}
