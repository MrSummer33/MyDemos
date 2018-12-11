package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Created by BaoCai on 2018/11/2. Description:
 */
public class IoTest {

    private static final String IN_FILE_NAME = "/Users/xiaxi/Desktop/test IO";
    private static final String MAX_FILE_NAME = "/Users/xiaxi/Downloads/Tencent_AILab_ChineseEmbedding/Tencent_AILab_ChineseEmbedding.txt";


    // InputStream
    @Test
    public void testInputStream() throws IOException {
        InputStream in = new FileInputStream(IN_FILE_NAME);
        byte[] bytes = new byte[3];
        int n;
        while ((n = in.read(bytes)) != -1) {
            String str = new String(bytes, 0, bytes.length);
            System.out.println(str);
        }
        System.out.println(new String(bytes));
    }

    // OutputStream
    @Test
    public void testOutputStream() throws IOException {
        OutputStream out = new FileOutputStream(IN_FILE_NAME, true);
        String str = "\nappend";
        out.write(str.getBytes());
    }

    // Reader
    @Test
    public void testReader() throws IOException {
        Reader reader = new FileReader(IN_FILE_NAME);
        char[] chars = new char[1];

        while (-1 != reader.read(chars, 0, chars.length)) {
            System.out.println(chars);
        }
    }

    // Writer
    @Test
    public void testWriter() throws IOException {
        Writer writer = new FileWriter(IN_FILE_NAME, true);
        writer.append("\nwriter append");
        writer.flush();
    }

    @Test
    public void testCharExchangeToByte() throws UnsupportedEncodingException {
        String baocai = "包菜";
        byte[] bytes = baocai.getBytes("UTF-8");
        System.out.println(Arrays.toString(bytes));

        byte[] baoBytes = {-27, -116, -123};
        String bao = new String(baoBytes,"UTF-8");
        System.out.println(bao);

        byte[] caiBytes = {-24, -113, -100};
        String cai = new String(caiBytes,"UTF-8");
        System.out.println(cai);
    }
}