package netty.rpc.client;

import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.rpc.common.response.Response;

/**
 * Created by BaoCai on 2018/12/22. Description:
 */
public class ClientHandler extends SimpleChannelInboundHandler<Response> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Response msg) throws Exception {
        System.out.println(new Gson().toJson(msg));
    }
}
