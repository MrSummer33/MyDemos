package netty.http.websocketx.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler.HandshakeComplete;
import java.util.Locale;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by BaoCai on 2018/12/13. Description:
 */
@Getter
@Setter
@Slf4j
public class ChatWebSocketHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    private ChannelGroup group;

    public ChatWebSocketHandler(ChannelGroup group) {
        this.group = group;
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof HandshakeComplete) {
            group.add(ctx.channel());
        }
        super.userEventTriggered(ctx, evt);
    }

    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        // ping and pong frames already handled

        if (frame instanceof TextWebSocketFrame) {
            // Send the uppercase string back.
            String request =
                    ctx.channel().id().asShortText() + ":" + ((TextWebSocketFrame) frame).text();
            log.info("{} received {}", ctx.channel(), request);
            group.writeAndFlush(new TextWebSocketFrame(request.toUpperCase(Locale.US)));
        } else {
            String message = "unsupported frame type: " + frame.getClass().getName();
            throw new UnsupportedOperationException(message);
        }
    }
}
