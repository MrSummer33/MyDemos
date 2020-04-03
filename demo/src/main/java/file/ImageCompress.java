package file;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import org.junit.Test;

/**
 * Created by BaoCai on 2020/4/3. Description:
 */
public class ImageCompress {

    @Test
    public void imageCompress() throws IOException {
        InputStream in = new FileInputStream("/Users/xiaxi/Pictures/picture/半山/IMG_5904.JPG");
        int size = in.available();
        byte[] inBytes = new byte[size];
        in.read(inBytes);

        byte[] outBytes = compressPicByQuality(inBytes,0.4f);

        OutputStream out =  new FileOutputStream("/Users/xiaxi/Desktop/aaa.JPG");
        out.write(outBytes);
        out.flush();
    }



    public static byte[] compressPicByQuality(byte[] imgByte, float quality) {
        byte[] inByte = null;
        try {
            ByteArrayInputStream byteInput = new ByteArrayInputStream(imgByte);
            BufferedImage image = ImageIO.read(byteInput);

            // 如果图片空，返回空
            if (image == null) {
                return null;
            }
            // 得到指定Format图片的writer
            Iterator<ImageWriter> iter = ImageIO
                    .getImageWritersByFormatName("jpeg");// 得到迭代器
            ImageWriter writer = iter.next(); // 得到writer

            // 得到指定writer的输出参数设置(ImageWriteParam )
            ImageWriteParam iwp = writer.getDefaultWriteParam();
            iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // 设置可否压缩
            iwp.setCompressionQuality(quality); // 设置压缩质量参数
            iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED);

            ColorModel colorModel = ColorModel.getRGBdefault();
            // 指定压缩时使用的色彩模式
            iwp.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel,
                    colorModel.createCompatibleSampleModel(16, 16)));

            // 开始打包图片，写入byte[]
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流
            IIOImage iIamge = new IIOImage(image, null, null);

            // 此处因为ImageWriter中用来接收write信息的output要求必须是ImageOutput
            // 通过ImageIo中的静态方法，得到byteArrayOutputStream的ImageOutput
            writer.setOutput(ImageIO
                    .createImageOutputStream(byteArrayOutputStream));
            writer.write(null, iIamge, iwp);
            inByte = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            System.out.println("write errro");
            e.printStackTrace();
        }
        return inByte;
    }

}
