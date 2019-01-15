package netty.rpc.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import netty.rpc.common.encoder.baocai.BServerDecoder;
import netty.rpc.common.encoder.baocai.BServerEncoder;

/**
 * Created by BaoCai on 2018/12/22. Description:
 */
public class ServerChannelInitializer extends ChannelInitializer {

    private ServerHandler serverHandler = new ServerHandler();

    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        p.addLast(new BServerDecoder());
        p.addLast(new BServerEncoder());
        p.addLast(serverHandler);
    }
}
