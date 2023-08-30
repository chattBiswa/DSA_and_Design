package interviewQuestions.multiThreadingQuestions;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerBQueue {
    public ProducerConsumerBQueue() {
    }

    public static void main(String[] args) {
        BlockingQueue sharedQueue = new LinkedBlockingQueue();
        Thread proT = new Thread(new Producer(sharedQueue));
        Thread conT = new Thread(new Consumer(sharedQueue));
        proT.start();
        conT.start();
    }
}

class Producer implements Runnable {
    private final BlockingQueue sharedQueue;

    public Producer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    public void run() {
        for(int i = 1; i < 10; ++i) {
            try {
                System.out.println("Produce = " + i);
                this.sharedQueue.put(i);
            } catch (InterruptedException var3) {
                throw new RuntimeException(var3);
            }
        }

    }
}

class Consumer implements Runnable {
    private final BlockingQueue sharedQueue;

    public Consumer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    public void run() {
        while(true) {
            try {
                System.out.println("Consume = " + this.sharedQueue.take());
            } catch (InterruptedException var2) {
                throw new RuntimeException(var2);
            }
        }
    }
}
