import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>(List.of(501, 631, 794, 1000, 1259, 1585, 1995, 2512, 3162, 3981, 5012,
                6310, 7943, 10000, 12589, 15849));

        List<Integer> integersRecursive = new ArrayList<>(List.of(5, 7, 10, 12, 15, 17, 20, 22, 25, 27, 30, 32, 35, 37, 40, 42, 45));
        System.out.print("Recursive solution result = ");
        for (Integer integer : integersRecursive) {
            System.out.println();
            System.out.print("Recursive solution for n = " + integer + " result = ");
            long start1 = System.nanoTime();
            System.out.println(solution1(integer));
            long end1 = System.nanoTime();
            System.out.println("Elapsed Time in nano seconds: " + (end1 - start1));
        }

        for (Integer integer : integers) {
            System.out.println();
            System.out.print("Dynamic Programming for n = " + integer + " result = ");
            long start2 = System.nanoTime();
            System.out.println(solution2(integer));
            long end2 = System.nanoTime();
            System.out.println("Elapsed Time in nano seconds: " + (end2 - start2));
        }

        for (Integer integer : integers) {
            System.out.println();
            System.out.print("Dynamic Programming with Space Optimization for n = " + integer + " result = ");
            long start3 = System.nanoTime();
            System.out.println(solution3(integer));
            long end3 = System.nanoTime();
            System.out.println("Elapsed Time in nano seconds: " + (end3 - start3));
        }

        for (Integer integer : integers) {
            System.out.println();
            System.out.print("Using power of the matrix for n = " + integer + " result = ");
            long start4 = System.nanoTime();
            System.out.println(solution4(integer));
            long end4 = System.nanoTime();
            System.out.println("Elapsed Time in nano seconds: " + (end4 - start4));
        }

        for (Integer integer : integers) {
            System.out.println();
            System.out.print("Divide and Conquer(With Optimized Power Function) for n = " + integer + " result = ");
            long start5 = System.nanoTime();
            System.out.println(solution5(integer));
            long end5 = System.nanoTime();
            System.out.println("Elapsed Time in nano seconds: " + (end5 - start5));
        }

        for (Integer integer : integers) {
            //Solution 6
            System.out.println();
            System.out.print("Iterative Solution for n = " + integer + " result = ");
            long start6 = System.nanoTime();
            System.out.println(solution6(integer));
            long end6 = System.nanoTime();
            System.out.println("Elapsed Time in nano seconds: " + (end6 - start6));
        }
    }

    public static void power1(long[][] F, int n) {
        if (n == 0 || n == 1)
            return;
        long[][] M = new long[][]{{1, 1}, {1, 0}};

        power1(F, n / 2);
        multiply(F, F);

        if (n % 2 != 0)
            multiply(F, M);
    }

    public static void multiply(long[][] F, long[][] M) {
        long x = F[0][0] * M[0][0] + F[0][1] * M[1][0];
        long y = F[0][0] * M[0][1] + F[0][1] * M[1][1];
        long z = F[1][0] * M[0][0] + F[1][1] * M[1][0];
        long w = F[1][0] * M[0][1] + F[1][1] * M[1][1];
        F[0][0] = x;
        F[0][1] = y;
        F[1][0] = z;
        F[1][1] = w;
    }

    public static void power(long[][] F, int n) {
        int i;
        long[][] M = new long[][]{{1, 1}, {1, 0}};

        for (i = 2; i <= n; i++)
            multiply(F, M);
    }

    public static int solution1(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return solution1(n - 1) + solution1(n - 2);
    }

    public static int solution2(int n) {
        int[] f = new int[n + 1];
        int i;

        f[0] = 0;

        if (n > 0) {
            f[1] = 1;

            for (i = 2; i <= n; i++) {
                f[i] = f[i - 1] + f[i - 2];
            }
        }

        return f[n];
    }

    public static int solution3(int n) {
        int a = 0, b = 1, c;
        if (n == 0)
            return a;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    public static Long solution4(int n) {
        long[][] F = new long[][]{{1, 1}, {1, 0}};
        if (n == 0)
            return 0L;
        power(F, n - 1);

        return (long) F[0][0];
    }

    public static long solution5(int n) {
        long[][] F = new long[][]{{1, 1}, {1, 0}};
        if (n == 0)
            return 0;
        power1(F, n - 1);

        return F[0][0];
    }

    public static int solution6(int n) {
        int current = 0;
        int next = 1;
        for (int i = 0; i < n; i++) {
            int a = next + current;
            current = next;
            next = a;
        }
        return current;
    }
}