package j2se;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by BaoCai on 2021/3/26. Description:
 */
@Slf4j
public class CollectionSort {

    /**
     * Comparison method violates its general contract
     */
    @Test
    public void sortFailCaseTest() {
        List<Integer> nums = Lists.newArrayList(
                1, 2, 3, 2, 2, 3, 2, 3, 2, 2, 3, 2, 3, 3, null, 2, 2, 2, 2, 2, 2, 3, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, null, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1);

//        List<Integer> nums = Lists.newArrayList(
//                1, 2, 3, null, 2, 3,1, null, 1, 1, 1);

        nums.sort((o1, o2) -> {
            if (o1 != null && o2 != null) {
                return o1.compareTo(o2);
            }
            return o1 == null ? 1 : -1;
        });
        for (Integer num : nums) {
            log.info("结果 ：{}", num);
        }
    }


    @Test
    public void collectionJoinTest(){
        List<String> lists = Lists.newArrayList("111","222","33");

        String str = lists.stream()
                .collect(Collectors.joining(""));

        System.out.println(str);
    }

}
