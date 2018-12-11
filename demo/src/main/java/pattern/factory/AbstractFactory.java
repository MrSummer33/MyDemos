package pattern.factory;

import pattern.factory.Cpu.CpuA;
import pattern.factory.Cpu.CpuB;
import pattern.factory.Cpu.CpuC;
import pattern.factory.Memory.MemoryA;
import pattern.factory.Memory.MemoryB;
import pattern.factory.Memory.MemoryC;

/**
 * Created by BaoCai on 2018/12/9. Description:
 */
public class AbstractFactory {

    //抽象工厂
    interface Factory {

        Cpu createCpu();

        Memory createMemory();
    }

    //具体工厂A
    public static class FactoryA implements Factory {

        @Override
        public Cpu createCpu() {
            return new CpuA();
        }

        @Override
        public Memory createMemory() {
            return new MemoryA();
        }
    }

    //具体工厂B
    public static class FactoryB implements Factory {

        @Override
        public Cpu createCpu() {
            return new CpuB();
        }

        @Override
        public Memory createMemory() {
            return new MemoryB();
        }
    }

    //具体工厂C
    public static class FactoryC implements Factory {

        @Override
        public Cpu createCpu() {
            return new CpuC();
        }

        @Override
        public Memory createMemory() {
            return new MemoryC();
        }
    }


    public static void main(String[] args) {
        Cpu cpu;
        Memory memory;
        Factory factory;

        factory = new FactoryA();
        cpu = factory.createCpu();
        memory = factory.createMemory();
        cpu.showMe();
        memory.showMe();

        factory = new FactoryB();
        cpu = factory.createCpu();
        memory = factory.createMemory();
        cpu.showMe();
        memory.showMe();

        factory = new FactoryC();
        cpu = factory.createCpu();
        memory = factory.createMemory();
        cpu.showMe();
        memory.showMe();

    }

}
