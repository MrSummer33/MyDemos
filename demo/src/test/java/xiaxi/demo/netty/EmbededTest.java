package xiaxi.demo.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpResponse;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

/**
 * Created by BaoCai on 2018/12/2. Description:
 */
public class EmbededTest {

    @Test
    public void testWebSocket00Response() {
        byte[] data = ("HTTP/1.1 101 WebSocket Protocol Handshake\r\n" +
                "Upgrade: WebSocket\r\n" +
                "Connection: Upgrade\r\n" +
                "Sec-WebSocket-Origin: http://localhost:8080\r\n" +
                "Sec-WebSocket-Location: ws://localhost/some/path\r\n" +
                "\r\n" +
                "1234567812345678").getBytes();
        EmbeddedChannel ch = new EmbeddedChannel(new HttpClientCodec());
        assertTrue(ch.writeInbound(Unpooled.wrappedBuffer(data)));

        HttpResponse res = ch.readInbound();
    }

}
