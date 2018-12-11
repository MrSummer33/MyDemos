package pattern.factory;

/**
 * Created by BaoCai on 2018/12/9. Description:
 */
public interface Memory {

    void showMe();

    class MemoryA implements Memory {

        @Override
        public void showMe() {
            System.out.println("i`m memory A !");
        }
    }

    class MemoryB implements Memory {

        @Override
        public void showMe() {
            System.out.println("i`m memory B !");
        }
    }

    class MemoryC implements Memory {

        @Override
        public void showMe() {
            System.out.println("i`m memory C !");
        }
    }
}
