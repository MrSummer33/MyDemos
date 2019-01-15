package netty.rpc.common.msg;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by BaoCai on 2019/1/7. Description:
 */
@Getter
@Setter
public class MsgDto {

    private Long fromUserId;

    private Long toUserId;

    private String content;

}
