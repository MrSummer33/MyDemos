package proxy.cglib;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * Created by BaoCai on 2018/9/23. Description:
 */
public class CglibMain {

    public static void main(String[] args) {

//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/xiaxi/Desktop/myproxy");

        MethodInterceptor methodInterceptor = (object, method, argArray, methodProxy) -> {
            System.out.println("do something before!");
            Object o = methodProxy.invokeSuper(object, argArray);
            System.out.println("do something after!");
            return o;
        };

        HelloCglib helloCglib = ProxyHello.getInstance(methodInterceptor);

        helloCglib.hello("hello");
        helloCglib.equals(null);


    }

}
