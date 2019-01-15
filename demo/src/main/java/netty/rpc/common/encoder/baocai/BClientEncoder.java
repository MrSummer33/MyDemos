package netty.rpc.common.encoder.baocai;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageCodec;
import java.util.List;
import netty.rpc.common.request.Request;
import netty.rpc.common.response.Response;

/**
 * Created by BaoCai on 2018/12/22. Description:
 */
public class BClientEncoder extends MessageToByteEncoder {

    private static final Gson GSON = new Gson();

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (null != msg) {
            String json = GSON.toJson(msg);
            byte[] bytes = json.getBytes("UTF-8");
            out = ctx.alloc().buffer(bytes.length + 10);
            out.writeBytes(bytes);
            System.out.println(json);
        }
        ctx.writeAndFlush(out);
    }
}
