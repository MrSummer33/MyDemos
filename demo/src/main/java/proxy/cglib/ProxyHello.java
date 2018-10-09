package proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * Created by BaoCai on 2018/9/23. Description:
 */
public class ProxyHello {

    public static HelloCglib getInstance(MethodInterceptor methodInterceptor) {

        Enhancer enhancer = new Enhancer();
        //继承被代理类
        enhancer.setSuperclass(HelloCglib.class);
        //设置回调
        enhancer.setCallback(methodInterceptor);
        //生成代理类对象
        HelloCglib helloCglib = (HelloCglib) enhancer.create();

        return helloCglib;
    }

}
