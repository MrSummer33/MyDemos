package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by BaoCai on 2018/10/9. Description:
 */
public class IoCpuState {

    private static volatile boolean finished = false;

    public static void main(String[] args) {
        Thread thread = new Thread(new MyIoThread());
        thread.start();

        while (!finished) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("IO线程状态" + thread.getState() + System.currentTimeMillis());
        }
    }

    private static class MyIoThread implements Runnable {

        public void run() {

            File file = new File("/Users/xiaxi/Desktop/【高清电影】b能说的秘密.2007.台湾.国语.1280x720.rmvb");
            try {
                FileInputStream fis = new FileInputStream(file);
                System.out.println(
                        "IO前" + Thread.currentThread().getState() + System.currentTimeMillis());
                fis.read(new byte[1024 * 1024 * 1024]);
                System.out.println(
                        "IO后" + Thread.currentThread().getState() + System.currentTimeMillis());
            } catch (IOException e) {
                e.printStackTrace();
            }
            finished = true;
        }
    }

}
