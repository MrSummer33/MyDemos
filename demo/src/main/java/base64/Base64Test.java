package base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.junit.jupiter.api.Test;

/**
 * Created by BaoCai on 2018/11/12. Description:
 */
public class Base64Test {


    @Test
    public void testBase64() throws UnsupportedEncodingException {

//        final Base64.Decoder decoder = Base64.getDecoder();
//        final Base64.Encoder encoder = Base64.getEncoder();
//        final String text = "字串文字";
//        final byte[] textByte = text.getBytes("UTF-8");
//
//        //编码
//        final String encodedText = encoder.encodeToString(textByte);
//        System.out.println(encodedText);
//        //解码
//        System.out.println(new String(decoder.decode("3Qqmcg9MyPmctO52hAtA5Q=="), "UTF-8"));


//        /uYgVAZ4UP39odLUNGp8pQ==
//        /uYgVAZ4UP39odLUNGp8pQ==

//                18298989898

        System.out.println(aes_encrypt("18298989898","eb1q2W3e$r5V"));



    }


    public static String aes_encrypt(String password, String strKey) {
        try {
            byte[] keyBytes = Arrays.copyOf(strKey.getBytes("ASCII"), 16);

            SecretKey key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] cleartext = password.getBytes("UTF-8");
            byte[] ciphertextBytes = cipher.doFinal(cleartext);

            return new String(Base64.getEncoder().encode(ciphertextBytes));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } return null;

    }

}
