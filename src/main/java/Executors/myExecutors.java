package Executors;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class myExecutors {
    public static List<String> list = new ArrayList<>
            (Arrays.asList("Aaa", "Rrr", "rtt", "assww", "as"));

    public static class MyRunable implements Runnable {
        @Override
        public void run() {
            try {
                list.stream()
                        .filter((a) -> a.toLowerCase().startsWith("a"))
                        .forEach(s -> System.out.println("Method run " + s + " "));
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        public static class MyCallable implements Callable<String> {
            @Override
            public String call() throws Exception {
                String max = list.stream()
                        .max((a1, a2) -> a1.length() - a2.length())
                        .orElse("0");

                return max;
            }
        }

        public static void main(String[] args) throws ExecutionException, InterruptedException {
            ExecutorService executorService = Executors.newFixedThreadPool(2);//fixed pool

            executorService.submit(new MyRunable());//метод сабмит добавляет поток в тред пул
            System.out.println("Method call()"
                    + executorService.submit(new MyCallable()).get());// получения рузультата call() методом get()
            executorService.shutdown();// закрыть экзекуторСервис

        }
    }
}
//        Method call()assww
//        Method run Aaa
//        Method run assww
//        Method run as