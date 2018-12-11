package pattern.factory;

import pattern.factory.Cpu.CpuA;
import pattern.factory.Cpu.CpuB;
import pattern.factory.Cpu.CpuC;

/**
 * Created by BaoCai on 2018/12/9. Description:
 */
public class SimpleFactory {

    public static Cpu createProduct(Character type) {
        if (type.equals('A')) {
            return new CpuA();
        } else if (type.equals('B')) {
            return new CpuB();
        } else if (type.equals('C')) {
            return new CpuC();
        } else {
            throw new UnsupportedOperationException("unsupported product type!");
        }
    }

    /**
     * optimize by flect
     */
    public static Cpu createProductByFlect(String clazzStr)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz = Class.forName(clazzStr);
        Cpu product = (Cpu) clazz.newInstance();

        return product;
    }

    public static void main(String[] args)
            throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Cpu product;

        product = createProduct('A');
        product.showMe();

        product = createProduct('B');
        product.showMe();

        product = createProduct('C');
        product.showMe();

        Cpu productFlect;

        productFlect = createProductByFlect(CpuA.class.getName());
        productFlect.showMe();

        productFlect = createProductByFlect(CpuB.class.getName());
        productFlect.showMe();

        productFlect = createProductByFlect(CpuC.class.getName());
        productFlect.showMe();
    }

}
