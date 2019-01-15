package netty.rpc.common.response;

import java.io.UnsupportedEncodingException;

/**
 * Created by BaoCai on 2018/12/22. Description:
 */
public interface Response<T> {

    int getStatus();

//    byte[] getBinaryBody();

    T getBody() throws UnsupportedEncodingException;

}
