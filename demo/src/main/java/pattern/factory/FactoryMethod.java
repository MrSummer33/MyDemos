package pattern.factory;

/**
 * Created by BaoCai on 2018/12/9. Description:
 */
public class FactoryMethod {

    //抽象工厂
    public interface CpuFactory {

        Cpu create();
    }

    //具体工厂A
    public static class CpuFactoryA implements CpuFactory {

        @Override
        public Cpu create() {
            return new Cpu.CpuA();
        }
    }

    //具体工厂B
    public static class CpuFactoryB implements CpuFactory {

        @Override
        public Cpu create() {
            return new Cpu.CpuB();
        }
    }

    //具体工厂C
    public static class CpuFactoryC implements CpuFactory {

        @Override
        public Cpu create() {
            return new Cpu.CpuC();
        }
    }


    public static void main(String[] args) {
        Cpu cpu;
        CpuFactory cpuFactory;

        cpuFactory = new CpuFactoryA();
        cpu = cpuFactory.create();
        cpu.showMe();

        cpuFactory = new CpuFactoryB();
        cpu = cpuFactory.create();
        cpu.showMe();

        cpuFactory = new CpuFactoryC();
        cpu = cpuFactory.create();
        cpu.showMe();
    }

}
