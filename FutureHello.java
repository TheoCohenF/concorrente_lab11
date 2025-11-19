import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyCallable implements Callable<Long> {
    MyCallable() {}

    @Override
    public Long call() throws Exception {
        long soma = 0;
        for (long i = 1; i <= 100; i++) {
            soma++;
        }
        return soma;
    }
}

class PrimoCallable implements Callable<Long> {
    private final long numero;

    public PrimoCallable(long numero) {
        this.numero = numero;
    }

    @Override
    public Long call() {
        return ehPrimo(numero) ? 1L : 0L;
    }

    private boolean ehPrimo(long n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        long limite = (long) Math.sqrt(n);
        for (long i = 3; i <= limite; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}

public class FutureHello {
    private static final int N = 30;
    private static final int NTHREADS = 10;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
        List<Future<Long>> resultados = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            Future<Long> ref = executor.submit(new MyCallable());
            resultados.add(ref);
        }

        for (int i = 0; i < N; i++) {
            Future<Long> ref = executor.submit(new PrimoCallable(i));
            resultados.add(ref);
        }

        System.out.println(resultados.size());

        long acumulado = 0;
        long primos = 0;

        for (int i = 0; i < N; i++) {
            try {
                acumulado += resultados.get(i).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        for (int i = N; i < 2 * N; i++) {
            try {
                primos += resultados.get(i).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Resultado MyCallable em %d execuções: %d%n", N, acumulado);
        System.out.printf("Total de primos entre 1 e %d: %d%n", N, primos);

        executor.shutdown();
    }
}
