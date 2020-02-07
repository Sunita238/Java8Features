package threads;

import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class Consumers implements Runnable{

        private BlockingQueue<Integer> sharBlockingQueue;

    public Consumers(BlockingQueue<Integer> sharBlockingQueue) {
        this.sharBlockingQueue = sharBlockingQueue;
    }

        @Override
        public void run() {
            try {
                while (true) {
                    Integer item = sharBlockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + " consumed " + item);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}
