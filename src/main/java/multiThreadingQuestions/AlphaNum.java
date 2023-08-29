package multiThreadingQuestions;


public class AlphaNum {
    volatile int n = 1;
    volatile char c = 'a';
    volatile boolean state = true;

    public AlphaNum() {
    }

    public static void main(String[] args) {
        final AlphaNum m = new AlphaNum();
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 26; ++i) {
                m.printAlpha();
            }

        });
        t1.setName("Thread-1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for(int j = 0; j < 26; ++j) {
                    m.printDigits();
                }

            }
        });
        t1.setName("Thread-2");
        t1.start();
        t2.start();
    }

    public void printAlpha() {
        synchronized(this) {
            try {
                while(!this.state) {
                    this.wait();
                }
            } catch (InterruptedException var4) {
                var4.printStackTrace();
            }

            System.out.println(this.c);
            this.state = false;
            ++this.c;
            this.notifyAll();
        }
    }

    public void printDigits() {
        synchronized(this) {
            try {
                while(this.state) {
                    this.wait();
                }
            } catch (InterruptedException var4) {
                var4.printStackTrace();
            }

            System.out.println(this.n);
            this.state = true;
            ++this.n;
            this.notifyAll();
        }
    }
}

