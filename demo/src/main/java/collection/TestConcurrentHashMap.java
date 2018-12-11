package collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.Test;

/**
 * Created by BaoCai on 2018/10/18. Description:
 */
public class TestConcurrentHashMap {


    @Test
    public void testForEachWhileResize() {

        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap(2);
        map.put(1, 1);
        new Thread(new MyRunnable(1,map)).start();
        map.put(2, 2);
        new Thread(new MyRunnable(2,map)).start();
        map.put(3, 3);
        new Thread(new MyRunnable(3,map)).start();
        map.put(4, 4);
        new Thread(new MyRunnable(4,map)).start();
    }

    private class MyRunnable implements Runnable {


        Map map;
        int index ;

        MyRunnable(int index ,Map map) {
            this.map = map;
            this.index = index;
            System.out.println(index + "   " + map.size());
        }

        @Override
        public void run() {
            System.out.println(index + "   " + map);
        }
    }

}
