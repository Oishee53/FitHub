import java.io.IOException;

class EraserThread implements Runnable {
    private volatile boolean stop;

    public EraserThread(String prompt) {
        System.out.println(prompt);
    }

    public void run() {

        try {
            Thread.sleep(300); // Give the user some time to start typing
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!stop) {
            System.out.print("\010*"); // Overwrites last character with '*'
            try {
                Thread.sleep(50);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt(); // Restore interrupt status
                break;
            }
        }
    }

    public void stopMasking() {
        stop = true;
    }
}
