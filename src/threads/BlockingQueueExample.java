package threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueExample {

    public static void main(String[] args) {
        BlockingQueue<Integer> sharedQ = new LinkedBlockingQueue<Integer>();

        Producers p = new Producers(sharedQ);
        Consumers c = new Consumers(sharedQ);

        Thread t = new Thread(p, "PRODUCERS");
        Thread t1 = new Thread(c, "CONSUMERS");
        t.start();
        t1.start();
    }
}
