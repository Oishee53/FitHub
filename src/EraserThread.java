class EraserThread implements Runnable {
    private volatile boolean stop; // Correct flag usage
    private final String prompt;

    public EraserThread(String prompt) {
        this.prompt = prompt;
    }

    public void run() {
        System.out.print(prompt); // Print prompt without a new line

        try {
            Thread.sleep(300); // Small delay to allow typing
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        while (!stop) {
            System.out.print("*"); // Print '*' for each keypress
            try {
                Thread.sleep(100); // Adjusted delay
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void stopMasking() {
        stop = true;
    }
}
