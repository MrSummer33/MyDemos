package j2se;

import com.google.common.base.Objects;
import java.math.BigDecimal;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * Created by BaoCai on 2021/3/25. Description:
 */
public class UtilsTest {

    /**
     * 测试Objects.equals方法
     *
     * 坑点: obj1 和 obj2对象类型不一致场景，特别是针对数字型的类型比较
     *
     */
    @Test
    public void testObjects_Equals(){
        BigDecimal bigDecimal = BigDecimal.valueOf(3L);
        Integer integer = 3;

        boolean equals = Objects.equal(bigDecimal, integer);

        Assert.isTrue(equals , "test failed !");

    }

}
