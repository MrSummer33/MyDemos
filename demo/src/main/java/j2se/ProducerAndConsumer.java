package j2se;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by BaoCai on 2019/2/1. Description:
 */
public class ProducerAndConsumer {

    private class TaskContainer{
        private Queue<Object> queue = new ArrayBlockingQueue(10);


        public void producer(Object o){
            queue.offer(o);
        }

        public Object consumer(){
            return queue.poll();
        }
    }

    private class Producer implements Runnable{
        private TaskContainer taskContainer;

        public Producer(TaskContainer taskContainer ) {
            this.taskContainer = taskContainer;
        }

        @Override
        public void run() {
            taskContainer.producer(createTask());
        }

        public Object createTask(){
            // do something
            return null;
        }
    }

    private class Consumer implements Runnable{
        private TaskContainer taskContainer;

        public Consumer(TaskContainer taskContainer , Object task) {
            this.taskContainer = taskContainer;
        }

        @Override
        public void run() {
            Object task = taskContainer.consumer();
            // do something
        }
    }
}
