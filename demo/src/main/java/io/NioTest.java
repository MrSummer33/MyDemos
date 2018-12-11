package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

/**
 * Created by BaoCai on 2018/11/2. Description:
 */
public class NioTest {

    private static int port = 19999;

    @Test
    public void nioServer() throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ExecutorService executorService = new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(500));
        ServerSocket ss = serverSocketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        ss.bind(address);


        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {

            selector.select(3000);

            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) {
                    // 链接进来
                    System.out.println("接收到新请求！");
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = serverChannel.accept();

                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);

                } else if (key.isReadable()) {
                    // 可读
                    executorService.execute(() -> {
                        try {
                            handleHttpRead(key, true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                } else if (key.isWritable()){
                    // 可写
                } else if (key.isConnectable()){
                    //
                }
                keyIterator.remove();

            }
        }
    }


    public void handleHttpRead(SelectionKey key, boolean isServer) throws IOException {
        System.out.println("数据传输完毕！！！");
        System.out.println(Thread.currentThread().getName());
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
        SocketChannel sc = (SocketChannel) key.channel();
        buffer.clear();
        if (!sc.isOpen()) {
            return;
        }
        if (sc.read(buffer) == -1) {
            sc.close();
        } else {
            buffer.flip();
            String receiveString = Charset.forName("UTF-8").newDecoder().decode(buffer)
                    .toString();
            System.out.println("接受数据:" + receiveString);
            String[] requestMessage = receiveString.split("\r\n");
            for (String s : requestMessage) {
                if (s.isEmpty()) {
                    break;
                }
            }

            if (isServer) {
                StringBuilder sendString = new StringBuilder();

                StringBuilder bodyBuilder = new StringBuilder();
                bodyBuilder.append("<html><head><title>SHOW</title></head></body>");
                bodyBuilder.append("Received:<br/>");

                for (String s : requestMessage) {
                    bodyBuilder.append(s + "<br/>");
                }
                bodyBuilder.append("</body></html>");

                sendString.append("HTTP/1.1 200 OK\r\n");
                sendString.append("Content-Type:text/html;Charset=" + "UTF-8" + "\r\n");
                sendString.append("content-length:" + bodyBuilder.toString().getBytes().length
                        + "\r\n");
                sendString.append("\r\n");

                sendString.append(bodyBuilder);
                buffer = ByteBuffer.wrap(sendString.toString().getBytes("UTF-8"));
                sc.write(buffer);
                sc.close();
            }
        }
    }

    @Test
    public void clientSocket() throws IOException {

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("我是客户端啊啊啊啊啊啊".getBytes("UTF-8"));
        InetSocketAddress address = new InetSocketAddress(port);
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(address);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        byteBuffer.clear();

        //从服务端读取消息
        int readLength = socketChannel.read(byteBuffer);
        //读取模式
        byteBuffer.flip();
        byte[] bytes = new byte[readLength];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes, "UTF-8"));
    }

}
