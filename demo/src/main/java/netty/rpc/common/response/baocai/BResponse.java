package netty.rpc.common.response.baocai;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import netty.rpc.common.response.Response;

/**
 * Created by BaoCai on 2018/12/22. Description:
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BResponse<T> implements Response<T> {

    private static final Gson GSON = new Gson();

    private T responseBody;

    private int responseStatus;

    public int getStatus() {
        return getResponseStatus();
    }

//    public byte[] getBinaryBody() {
//        return new byte[0];
//    }

    public T getBody() {
        return getResponseBody();
    }

}