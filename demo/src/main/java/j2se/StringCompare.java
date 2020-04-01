package j2se;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * Created by BaoCai on 2019/2/1. Description:
 */
public class StringCompare {

    private List<String> list;

    @Before
    public void init() {
        this.list = new ArrayList();
        list.add("IM_NODE0000000003");
        list.add("IM_NODE0000000004");
        list.add("IM_NODE0000000011");
        list.add("IM_NODE0000000005");
        list.add("IM_NODE0000000006");
    }

    @Test
    public void compare() {
        String currentNode = "IM_NODE000000001";

        Assert.isTrue(isMaster(list, currentNode), "算法错误！");
    }

    @Test
    public void checkMaster() {
        System.out.println(master(list));
    }

    @Test
    public void smallerNode() {
        String leaderStr = master(list);

        String leader = leaderStr.substring(7);

        long leaderNum = Long.valueOf(leader);

        for(int i = (int)leaderNum -1 ; i > 0 ; i--){
            System.out.println(convertToZkNode(i));
        }
    }

    public String convertToZkNode(Integer nodeNum) {
        String numStr = nodeNum.toString();

        while (numStr.length() < 10) {
            numStr = "0" + numStr;
        }

        return numStr;
    }

    private boolean isMaster(List<String> children, String currentNode) {

        for (String node : children) {
            if (currentNode.compareToIgnoreCase(node) > 0) {
                return false;
            }
        }

        return true;
    }

    private String master(List<String> children) {
        Assert.notEmpty(children, "children can`t be null!");
        String master = children.get(0);

        for (String node : children) {
            if (master.compareToIgnoreCase(node) > 0) {
                master = node;
            }
        }

        return master;

    }

}
