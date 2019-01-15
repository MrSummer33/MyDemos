package netty.rpc.common.request.baocai;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import netty.rpc.common.request.Request;

/**
 * Created by BaoCai on 2018/12/22. Description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BRequest<T> implements Request {

    private static final Gson GSON = new Gson();

    private T requestBody;

    private String routeUrl;

    public String getProtocol() {
        return "baocai://";
    }

    public String getHost() {
        return null;
    }

    public String getBody() {
        if (null == requestBody) {
            return GSON.toJson("");
        }
        return GSON.toJson(requestBody);
    }

    public String getRouteKey() {
        return routeUrl;
    }
}
