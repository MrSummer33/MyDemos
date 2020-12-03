package gc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BaoCai on 2020/12/1. Description:
 */
public class MyG1Test {


    /**
     * jvm options:
     *  -Xmx100M
     *  -Xms100M
     *  -XX:+UseG1GC
     *  -XX:MaxGCPauseMillis=200
     *  -XX:InitiatingHeapOccupancyPercent=15
     *  -XX:+PrintAdaptiveSizePolicy
     *  -XX:+PrintCommandLineFlags
     *  -XX:+PrintGCDateStamps
     *  -XX:+PrintGCDetails
     *  -XX:+PrintGCApplicationStoppedTime
     *  -XX:+PrintGCCause
     *  -XX:+PrintPromotionFailure
     *  -XX:+PrintTenuringDistribution
     *  -XX:+PrintHeapAtGC
     *  -XX:+UnlockDiagnosticVMOptions
     *  -XX:+G1PrintRegionLivenessInfo
     *  -XX:ParallelGCThreads=2
     *  -XX:G1HeapRegionSize=3M
     *  -XX:G1HeapWastePercent=0.1
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        List<Byte[]> lists = new ArrayList(10000);

        int index = 0;

        while (true){

            Byte[] bytes = new Byte[100];

            if((index++ % 1) == 0){
                lists.add(bytes);
            }

            if(index % 10 == 0){
                Byte[] bytes1 = new Byte[1024*1024];
            }
        }

    }

}
