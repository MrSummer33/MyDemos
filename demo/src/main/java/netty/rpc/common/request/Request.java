package netty.rpc.common.request;

/**
 * Created by BaoCai on 2018/12/18. Description:
 */
public interface Request {

    String getProtocol();

    String getHost();

    String getBody();

    String getRouteKey();

}
