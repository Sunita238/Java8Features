package threads;

import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class Producers implements Runnable {
    private BlockingQueue<Integer> sharBlockingQueue;

    public Producers(BlockingQueue<Integer> sharBlockingQueue) {
        this.sharBlockingQueue = sharBlockingQueue;
    }

    @Override
    public void run() {
        IntStream.range(0,10).forEach(
                i -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + " Produced " + i);
                        sharBlockingQueue.put(i);
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        
    }
}
