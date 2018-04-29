package Week12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.*;

/*
 * Apply SPMD (Single Program, Multiple Data) design pattern for concurrent programming to parallelize the program which 
 * approximates $\pi$ by integrating the following formula $4/(1+x^2 )$. Hint: In the SPMD design pattern, all threads 
 * run the same program, operating on different data.
 */
public class Exercise1 {

    public static void main(String[] args) throws Exception {
        int NTHREADS = 10;
        ExecutorService exec = Executors.newFixedThreadPool(NTHREADS - 1);
        // todo: complete the program by writing your code below.
        double output = 0;
        for (int i = 0; i < NTHREADS; i++) {
            double a = i * (1.0 / NTHREADS);
            double b = (i+1) * (1.0 / NTHREADS);

            Future<Double> intermediate = exec.submit(() -> integrate(a, b));
            System.out.println("Intermediate: " + intermediate.get());
            output += intermediate.get();
        }
        System.out.println(output);
        exec.shutdown();
    }

    public static double f(double x) {
        return 4.0 / (1 + x * x);
    }

    // the following does numerical integration using Trapezoidal rule.
    public static double integrate(double a, double b) {
        int N = 10000; // preciseness parameter
        double h = (b - a) / (N - 1); // step size
        double sum = 1.0 / 2.0 * (f(a) + f(b)); // 1/2 terms

        for (int i = 1; i < N - 1; i++) {
            double x = a + h * i;
            sum += f(x);
        }

        return sum * h;
    }
}
