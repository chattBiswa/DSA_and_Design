package multiThreadingQuestions;

public class SequenceExec {
    public static void main(String[] args) {
        ParallelTask p1 = new ParallelTask();
        ParallelTask p2 = new ParallelTask();
        ParallelTask p3 = new ParallelTask();
        Thread t1 = new Thread(p1, "Thread-1");
        Thread t2 = new Thread(p2, "Thread-2");
        Thread t3 = new Thread(p3, "Thread-3");
        p2.setPredecessor(t1);
        p3.setPredecessor(t2);
        t1.start();
        t2.start();
        t3.start();
    }

    private static class ParallelTask implements Runnable {
        Thread predecessor;

        private ParallelTask() {
        }

        void setPredecessor(Thread t) {
            this.predecessor = t;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + " started");
            if (this.predecessor != null) {
                try {
                    this.predecessor.join();
                } catch (InterruptedException var2) {
                    var2.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() + " finished");
        }
    }
}