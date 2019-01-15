package netty.rpc.common.encoder.baocai;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import netty.rpc.common.response.Response;
import netty.rpc.common.response.baocai.BResponse;

/**
 * Created by BaoCai on 2018/12/22. Description:
 */
public class BClientDecoder extends ByteToMessageDecoder {

    private static final Gson GSON = new Gson();

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        int size = in.readableBytes();
        byte[] bytes = new byte[size];
        in.readBytes(bytes);
        String json = new String(bytes);
        Response request = GSON.fromJson(json, BResponse.class);
        out.add(request);
    }

}
