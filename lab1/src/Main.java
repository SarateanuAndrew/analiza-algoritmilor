public class Main {
    public static void main(String[] args) {
        int n = 10;
        System.out.println("N = " + n + "\n");
        //Solution 1
        System.out.print("1. Recursive solution result = ");
        long start1 = System.nanoTime();
        System.out.println(solution1(n));
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: " + (end1 - start1));

        //Solution 2
        System.out.println();
        System.out.print("2. Dynamic Programming result = ");
        long start2 = System.nanoTime();
        System.out.println(solution2(n));
        long end2 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: " + (end2 - start2));

        //Solution 3
        System.out.println();
        System.out.print("3. Dynamic Programming with Space Optimization result = ");
        long start3 = System.nanoTime();
        System.out.println(solution3(n));
        long end3 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: " + (end3 - start3));

        //Solution 4
        System.out.println();
        System.out.print("4. Divide and Conquer result = ");
        long start4 = System.nanoTime();
        System.out.println(solution4(n));
        long end4 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: " + (end4 - start4));

        //Solution 5
        System.out.println();
        System.out.print("5. Divide and Conquer(With Optimized Power Function) result = ");
        long start5 = System.nanoTime();
        System.out.println(solution5(n));
        long end5 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: " + (end5 - start5));

        //Solution 6
        System.out.println();
        System.out.print("6. Iterative Solution result = ");
        long start6 = System.nanoTime();
        System.out.println(solution6(n));
        long end6 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: " + (end6 - start6));
    }

    public static void power1(int[][] F, int n) {
        if (n == 0 || n == 1)
            return;
        int[][] M = new int[][]{{1, 1}, {1, 0}};

        power1(F, n / 2);
        multiply(F, F);

        if (n % 2 != 0)
            multiply(F, M);
    }

    public static void multiply(int[][] F, int[][] M) {
        int x = F[0][0] * M[0][0] + F[0][1] * M[1][0];
        int y = F[0][0] * M[0][1] + F[0][1] * M[1][1];
        int z = F[1][0] * M[0][0] + F[1][1] * M[1][0];
        int w = F[1][0] * M[0][1] + F[1][1] * M[1][1];
        F[0][0] = x;
        F[0][1] = y;
        F[1][0] = z;
        F[1][1] = w;
    }

    public static void power(int[][] F, int n) {
        int i;
        int[][] M = new int[][]{{1, 1}, {1, 0}};

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

    public static int solution4(int n) {
        int[][] F = new int[][]{{1, 1}, {1, 0}};
        if (n == 0)
            return 0;
        power(F, n - 1);

        return F[0][0];
    }

    public static int solution5(int n) {
        int[][] F = new int[][]{{1, 1}, {1, 0}};
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