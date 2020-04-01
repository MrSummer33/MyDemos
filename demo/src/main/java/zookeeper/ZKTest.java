package zookeeper;

import com.google.gson.Gson;
import java.nio.file.WatchEvent;
import java.util.List;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by BaoCai on 2019/1/22. Description:
 */
public class ZKTest {

    //    private static final String connectString = "mimi1:2181,mimi2:2181,mini3:2181";
    private static final String connectString = "127.0.0.1:2181";
    private static final int sessionTimeout = 2000;

    ZooKeeper zkClient = null;

    @Before
    public void init() throws Exception {
        zkClient = new ZooKeeper(connectString, sessionTimeout, event -> {
            try {
                List<String> children = zkClient.getChildren("/NET_HOS", true);
                System.out.println(children);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(event.getType() + "::::::" + event.getPath());

        });
    }

    /**
     * 数据的增删改查
     */
    //创建数据节点到zk中
    @Test
    public void testCreate() throws Exception {
        //参数1，要创建的节点的路径 参数2：节点数据 参数3：节点的权限 参数4：节点的类型
        String node = zkClient
            .create("/imNode/app42", "hellozk".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);
        Thread.sleep(1000);

        List<String> children = zkClient.getChildren("/imNode", event -> {
            System.out.println(new Gson().toJson(event));
            System.out.println("eventType ----> " + event.getType());
        });

        children.forEach(child -> {
            try {
                byte[] data = zkClient.getData("/imNode/" + child, false, null);
                System.out.println(new String(data));
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread.sleep(5000);
        zkClient.setData("/imNode/app42", "testSet".getBytes(), -1);

        Thread.sleep(5000);
        zkClient.setData("/imNode/app42", "testSet222222".getBytes(), -1);

        while (true) {

        }
    }

    @Test
    public void watch() {
        while (true) {
            ;
        }
    }

    //判断znode是否存在
    @Test
    public void testExist() throws Exception {
        //节点元数据
        Stat stat = zkClient.exists("/idea", false);
        System.out.println(stat == null ? "not exist" : "exist");
    }

    //获取子节点
    @Test
    public void getChildren() throws Exception {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }
//        Thread.sleep(Long.MAX_VALUE);//真正运行时可以注释
    }

    //获取znode的数据
    @Test
    public void getData() throws Exception {
        byte[] data = zkClient.getData("/idea", false, null);
        System.out.println(new String(data));
    }

    //删除znode的数据
    @Test
    public void deleteZnode() throws Exception {
        //参数2：指定要删除的版本，-1表示删除所有版本
        zkClient.delete("/idea", -1);
    }

    //设置znode
    @Test
    public void setData() throws Exception {
        zkClient.setData("/app1", "hello".getBytes(), -1);
        byte[] data = zkClient.getData("/app1", false, null);
        System.out.println(new String(data));
    }

    @Test
    public void children() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
            byte[] data = zkClient.getData("/" + child, false, null);
            System.out.println(new String(data));
        }
    }


}
