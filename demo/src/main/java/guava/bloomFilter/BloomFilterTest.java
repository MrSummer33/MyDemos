package guava.bloomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by BaoCai on 2020/10/19. Description:
 */
@Slf4j
public class BloomFilterTest {

    public static void main(String[] args){
        BloomFilter<Long> bloomFilter = BloomFilter.create(Funnels.longFunnel(),10000000L);


        for(int i = 0 ; i < 1000000 ; i++){
            bloomFilter.put(Long.valueOf(i));
        }


        for(int i = 100000 ; i < 100100 ; i++){
            System.out.println(String.valueOf(bloomFilter.mightContain(Long.valueOf(i))));
        }
        System.out.println(String.valueOf(bloomFilter.mightContain(Long.valueOf(-1111))));

        Gson gson = new Gson();
        String jsonValue = gson.toJson(bloomFilter);
        System.out.println(jsonValue.length());


        BloomFilter<Long> bloomFilter1 = gson.fromJson(jsonValue,BloomFilter.class);
        for(int i = 100000 ; i < 100100 ; i++){
            System.out.println(String.valueOf(bloomFilter.mightContain(Long.valueOf(i))));
        }
        System.out.println(String.valueOf(bloomFilter.mightContain(Long.valueOf(-1111))));

    }

}
