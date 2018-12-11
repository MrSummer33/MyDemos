package pattern.factory;

/**
 * Created by BaoCai on 2018/12/9. Description:
 */
public interface Cpu {

    void showMe();

    class CpuA implements Cpu {

        @Override
        public void showMe() {
            System.out.println("i`m Cpu A !");
        }
    }

    class CpuB implements Cpu {

        @Override
        public void showMe() {
            System.out.println("i`m Cpu B !");
        }
    }

    class CpuC implements Cpu {

        @Override
        public void showMe() {
            System.out.println("i`m Cpu C !");
        }
    }

}
