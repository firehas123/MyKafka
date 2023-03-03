public class TestingThread implements Runnable{

    public void run() {
        try {
            shugal();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void shugal() throws Exception {
        while(true) {
            Thread.sleep(5000);
            System.out.println("Hi thread is active now going back to sleep");
        }
    }
}
