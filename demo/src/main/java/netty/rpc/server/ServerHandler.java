package netty.rpc.server;

import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import netty.rpc.common.msg.MsgDto;
import netty.rpc.common.request.Request;
import netty.rpc.common.response.Response;
import netty.rpc.common.response.baocai.BResponse;

/**
 * Created by BaoCai on 2018/12/22. Description:
 */
@Sharable
public class ServerHandler extends SimpleChannelInboundHandler<Request> {


    private static Gson GSON = new Gson();
    private Map<Long, Channel> onlineUsers = new ConcurrentHashMap();

    protected void channelRead0(ChannelHandlerContext ctx, Request msg) throws Exception {
        Response response = null;
        if (msg.getRouteKey().equalsIgnoreCase("hello")) {
            response = new BResponse("hello client", 0);
        } else if (msg.getRouteKey().equalsIgnoreCase("getSomeThing")) {
            response = new BResponse("back some thing", 0);
        } else if (msg.getRouteKey().equalsIgnoreCase("login")) {
            Long userId = Double.valueOf(msg.getBody()).longValue();
            onlineUsers.put(userId, ctx.channel());
        } else if (msg.getRouteKey().equalsIgnoreCase("chat")) {
            String body = msg.getBody();
            MsgDto msgDto = GSON.fromJson(body, MsgDto.class);
            Response finalResponse = new BResponse(msgDto, 0);
            onlineUsers.entrySet().stream()
                    .filter(entry -> !msgDto.getFromUserId().equals(entry.getKey())
                            || msgDto.getFromUserId() != entry.getKey())
                    .forEach(entry -> entry.getValue().writeAndFlush(finalResponse));
        } else {
            response = new BResponse("can`t find source!", 404);
        }

        if (null != response) {
            ChannelFuture writeFuture = ctx.write(response);
        }

//        writeFuture.addListener(future -> {
//            if (future.isSuccess()) {
//                ctx.channel().close();
//            }
//        });
    }
}
