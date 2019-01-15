package netty.rpc.client;

import com.google.gson.Gson;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import netty.rpc.common.request.Request;
import netty.rpc.common.request.baocai.BRequest;

/**
 * Created by BaoCai on 2018/12/22. Description:
 */
public class ClientBootstrap {

    static final String URL = System.getProperty("url", "127.0.0.1:8080/");

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());

            // Make the connection attempt.
            Channel ch = b.connect("localhost", 8080).sync().channel();

            Request request1 = new BRequest("hello client222!", "hello");
            Request request2 = new BRequest("hello client222!", "hello");
            System.out.println(new Gson().toJson(request1));

            // Send the HTTP request.
            ch.writeAndFlush(request1);
//            ch.writeAndFlush(request2);
            // asyn 编程
            ChannelFuture closeFuture = ch.closeFuture().sync();
        } finally {
            // Shut down executor threads to exit.
            group.shutdownGracefully();
        }
    }

}
