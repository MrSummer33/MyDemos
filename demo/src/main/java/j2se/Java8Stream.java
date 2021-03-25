package j2se;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by BaoCai on 2021/2/19. Description:
 */
@Slf4j
public class Java8Stream {

    private static Gson GSON = new Gson();

    public static void main(String[] args){

        testStream2SetNPE();

    }

    private static void testStream2SetNPE(){
        List<String> strs = Lists.newArrayList("1","2","3",null);

        Set<String> setStrs = strs.stream()
                .collect(Collectors.toSet());

        log.warn(GSON.toJson(setStrs));
    }

}
